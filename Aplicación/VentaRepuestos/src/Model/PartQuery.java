
package Model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PartQuery {
    
    public static void insertPart(String name, String brand, String fabricator){
        ArrayList<String> columns = new ArrayList<String>() { 
            { 
                add("NOMBRE"); 
                add("ID_MARCA");
                add("ID_FABRICANTE");
            } 
        }; 
        ArrayList<String> values = new ArrayList<String>() { 
            {
                add("'" + name + "'");
                add("(SELECT ID FROM MARCA WHERE NOMBRE = '" + brand + "')");
                add("(SELECT ID FROM FAB_PARTES WHERE NOMBRE = '" + fabricator + "')");
            } 
        };
        try {
            ConnectionManager.insert("PARTE", columns, values);
        } catch (SQLException ex) {
            Logger.getLogger(PartQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void deletePart(String partId){
        try {
            ConnectionManager.delete("PARTE", "ID = " + partId);
        } catch (SQLException ex) {
            Logger.getLogger(PartQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void asociatePartProvider(String partId, String provider, String providerPrice, String gainPercent){
        ArrayList<String> columns = new ArrayList<String>() { 
            { 
                add("ID_PARTE"); 
                add("ID_PROVEEDOR");
                add("PRECIO_PROVEEDOR");
                add("POR_GANANCIA");
                add("PRECIO_PUBLICO");
            } 
        }; 
        ArrayList<String> values = new ArrayList<String>() { 
            {
                add(partId);
                add("(SELECT ID FROM PROVEEDOR WHERE NOMBRE = '" + provider + "')");
                add(providerPrice);
                add(gainPercent);
                add(gainPercent  + "/100.0 * " + providerPrice + " + " + providerPrice);
            } 
        };
        try {
            ConnectionManager.insert("PROVISION", columns, values);
        } catch (SQLException ex) {
            Logger.getLogger(PartQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void asociatePartCar(String partId, String model, String year){
        ArrayList<String> columns = new ArrayList<String>() { 
            { 
                add("ID_PARTE"); 
                add("ID_AUTOMOVIL");
            } 
        }; 
        ArrayList<String> values = new ArrayList<String>() { 
            {
                add(partId);
                add("(SELECT ID FROM AUTOMOVIL WHERE MODELo = '" + model + "' AND ANO = '" + year + "')");
            } 
        };
        try {
            ConnectionManager.insert("CORRESPONDENCIA", columns, values);
        } catch (SQLException ex) {
            Logger.getLogger(PartQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void modifyPartProviderPrice(){
        
    }
    
    
    
}
