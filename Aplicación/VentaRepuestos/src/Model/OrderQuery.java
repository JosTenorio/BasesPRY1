
package Model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.ResultSet;

public class OrderQuery {
    
    public static void insertOrder(String clientCed, String day, String month, String year, boolean organization){
        try {
            ArrayList<String> columns = new ArrayList<String>() {
                {
                    add("FECHA");
                    add("ID_CLIENTE");
                }
            };
            ArrayList<String> values = new ArrayList<String>() {
                {
                    add("'" + year + "-" + month + "-" + day + "'");
                    if (organization)
                        add("(SELECT ID_CLIENTE FROM ORGANIZACION WHERE CEDULA_JUR = '" + clientCed + "')");
                    else
                        add("(SELECT ID_CLIENTE FROM PERSONA WHERE CEDULA = '" + clientCed + "')");
                }
            };
            String idClient = values.get(1);
            ResultSet rs = ConnectionManager.select("TIPO", "ESTADO", "ID = (SELECT ID_ESTADO FROM CLIENTE WHERE ID = " + idClient + ")");
            if (rs.next() == false){
                throw new SQLException ("NULL into column 'ID_CLIENTE'");
            } 
            if (rs.getString("TIPO").equals("SUSPENDIDO")){
                throw new SQLException ("Client suspended");
            }
            if (rs.getString("TIPO").equals("INACTIVO")){
                ConnectionManager.update("CLIENTE", "ID_ESTADO", "(SELECT ID FROM ESTADO WHERE TIPO = 'ACTIVO')", "ID = " + idClient);
            }
            ConnectionManager.insert("ORDEN", columns, values);
        } catch (SQLException ex) {
            ErrorManager.orderInsertError(ex, organization);
        }
    }
    
    public static void addDetail(String orderId, String partId, String amount, String provider){
        try {
            try {
                ArrayList<String> columns = new ArrayList<String>() {
                    {
                        add("ID");
                        add("ID_ORDEN");
                        add("ID_PARTE");
                        add("CANTIDAD");
                        add("ID_PROVEEDOR");
                        add("PRECIO");
                    }
                };
                ArrayList<String> values = new ArrayList<String>() {
                    {
                        if (ConnectionManager.select("ID", "DETALLE", "ID_ORDEN = " + orderId).next() == false)
                            add("1");
                        else
                            add("(SELECT MAX(ID) + 1 FROM DETALLE WHERE ID_ORDEN = " + orderId + ")");
                        add(orderId);
                        add(partId);
                        add(amount);
                        add("(SELECT ID FROM PROVEEDOR WHERE NOMBRE = '"  + provider + "')");
                        add("(SELECT PRECIO_PUBLICO * " + amount + " FROM PROVISION WHERE ID_PARTE = " + partId + " AND ID_PROVEEDOR = (SELECT ID FROM PROVEEDOR WHERE NOMBRE = '" + provider + "'))");
                    }
                };
                ConnectionManager.insert("DETALLE", columns, values);
            }catch (SQLException ex) {
                ErrorManager.detailInsertError(ex);
                throw new PreviousSQLException (ex.getMessage());
            }
            try {
                ResultSet rs = ConnectionManager.select("MONTO_BASE", "ORDEN", "ID = " + orderId);
                rs.next();
                String oldCost = String.valueOf(rs.getFloat("MONTO_BASE"));
                rs.close();
                rs = ConnectionManager.select("PRECIO", "DETALLE", "ID_ORDEN = " + orderId + " AND ID = (SELECT MAX(ID) FROM DETALLE WHERE ID_ORDEN = " + orderId + ")");
                rs.next();
                String currentCost = String.valueOf(rs.getFloat("PRECIO"));
                ConnectionManager.update("ORDEN", "MONTO_BASE", oldCost + " + " + currentCost, "ID = " + orderId);
            }catch (SQLException ex){
                ErrorManager.orderUpdateError (ex);
                throw new PreviousSQLException (ex.getMessage());
            }
        } catch (PreviousSQLException ex) {
        }
    }
}
