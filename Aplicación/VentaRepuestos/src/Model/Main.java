
package Model;

import Controller.MainMenuController;
import View.MainMenuDisplay;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    
    public static void main(String[] args){
        try {
            ConnectionManager.logIn("192.168.100.45", "sa", "12345");
            ConnectionManager.connect();
            MainMenuController controller = new MainMenuController(new MainMenuDisplay());
            
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
}
