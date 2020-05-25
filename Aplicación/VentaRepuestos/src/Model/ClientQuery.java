
package Model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientQuery {
    
    public static void insertClient(String status, ArrayList<String> information, ArrayList<String> telephones, boolean organization){
        ArrayList<String> columns = new ArrayList<String>() { 
            { 
                add("ID_ESTADO"); 
            } 
        }; 
        ArrayList<String> values = new ArrayList<String>() { 
            { 
                add("(SELECT ID FROM ESTADO WHERE TIPO = '" + status + "')");
            } 
        };
        try {
            ConnectionManager.insert("CLIENTE", columns, values);
        } catch (SQLException ex) {
            Logger.getLogger(ClientQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
        insertClientAux(information, organization);
        insertPhone(information.get(0), telephones, organization);
    }
    
    private static void insertClientAux(ArrayList<String> information, boolean organization){
        ArrayList<String> columns = new ArrayList<>(){
            {
                if (organization){
                    add("CEDULA_JUR");
                    add("NOMBRE"); 
                    add("DIRECCION"); 
                    add("CIUDAD");
                    add("NOMBRE_CONTACTO");
                    add("CARGO_CONTACTO");
                }
                else{
                    add("CEDULA");
                    add("NOMBRE"); 
                    add("DIRECCION"); 
                    add("CIUDAD");
                }
                add("ID_CLIENTE");
            }
        };
        for (int i = 1; i < information.size(); i++)
            information.set(i, "'" + information.get(i) + "'");
        information.add("(SELECT MAX(ID) FROM CLIENTE)");
        if (organization)
        {
            try {
                ConnectionManager.insert("ORGANIZACION", columns, information);
            } catch (SQLException ex) {
                Logger.getLogger(ClientQuery.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else
        {
            try {
                ConnectionManager.insert("PERSONA", columns, information);
            } catch (SQLException ex) {
                Logger.getLogger(ClientQuery.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private static void insertPhone(String key, ArrayList<String> telephones, boolean organization){
        ArrayList<String> columns = new ArrayList<>(){
            {
                if (organization){
                    add("CEDULA_ORGANIZACION");
                }
                else{
                    add("CEDULA_PERSONA");
                }
                add("TELEFONO");  
            }
        };
        ArrayList<String> values = new ArrayList<>() { 
            { 
                add(key); 
                add("");
            } 
        };
        if (organization)
        {
            for (String telephone : telephones){
                values.set(1, telephone);
                try {
                    ConnectionManager.insert("TELEFONOS_ORGANIZACION", columns, values);
                } catch (SQLException ex) {
                    Logger.getLogger(ClientQuery.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        else
        {
            for (String telephone : telephones){
                values.set(1, telephone);
                try {
                    ConnectionManager.insert("TELEFONOS_PERSONA", columns, values);
                } catch (SQLException ex) {
                    Logger.getLogger(ClientQuery.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    public static void modifyClient(){
        
    }
    
}
