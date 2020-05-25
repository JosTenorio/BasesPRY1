
package Model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OrderQuery {
    
    public static void insertOrder(String day, String month, String year, String client, boolean organization){
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
                    add("(SELECT ID_CLIENTE FROM ORGANIZACION WHERE CEDULA_JUR = '" + client + "')");
                else
                    add("(SELECT ID_CLIENTE FROM PERSONA WHERE CEDULA = '" + client + "')");
            } 
        };
        try {
            ConnectionManager.insert("ORDEN", columns, values);
        } catch (SQLException ex) {
            Logger.getLogger(OrderQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void addDetail(){
        
    }
    
    
    
}
