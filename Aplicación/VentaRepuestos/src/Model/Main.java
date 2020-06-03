
package Model;

import Controller.MainMenuController;

public class Main {
    
    public static void main(String[] args){
            ConnectionManager.logIn("192.168.1.10", "sa", "2019064588");
//            ConnectionManager.logIn("192.168.100.45", "sa", "12345");
            MainMenuController controller = MainMenuController.getInstance();
            controller.makeVisible(true);           
    } 
}
