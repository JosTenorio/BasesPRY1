
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
    
    public static void deletePart(){
        
    }
    
    public static void asociatePartProvider(){
        
    }
    
    public static void asociatePartCar(){
        
    }
    
    public static void modifyPartProviderPrice(){
        
    }
    
    
    
}
