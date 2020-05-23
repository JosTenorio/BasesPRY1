
package Model;

import Controller.MainMenuController;
import View.MainMenuDisplay;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;

public class Main {
    
    public static void main(String[] args){
        try {
            ConnectionManager.logIn("192.168.1.10", "sa", "2019064588");
            ArrayList<String> columns = new ArrayList<String>() { 
                { 
                    add("ID_ESTADO"); 
                } 
            }; 
            ArrayList<String> values = new ArrayList<String>() { 
                { 
                    add("(SELECT ID FROM ESTADO WHERE TIPO = 'INACTIVO')");
                } 
            };
            ConnectionManager.insert("CLIENTE", columns, values);
            ArrayList<String> columns2 = new ArrayList<String>() { 
                { 
                    add("CEDULA");
                    add("NOMBRE"); 
                    add("DIRECCION"); 
                    add("CIUDAD");
                    add("ID_CLIENTE"); 
                } 
            }; 
            ArrayList<String> values2 = new ArrayList<String>() { 
                { 
                    add("117910466");
                    add("'Joseph Tenorio'");
                    add("'Casa color rojo'");
                    add("'San Jose'");
                    add("(SELECT MAX(ID) FROM CLIENTE)");
                } 
            };
            ConnectionManager.insert("PERSONA", columns2, values2);
            MainMenuController controller = new MainMenuController(new MainMenuDisplay()); 
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
}
