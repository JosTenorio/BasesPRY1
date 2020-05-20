
package Model;

import Controller.MainMenuController;
import View.MainMenuDisplay;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    
    public static void main(String[] args){
        try {
            ConnectionManager.connect();
            MainMenuController controller = new MainMenuController(new MainMenuDisplay());
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
}
