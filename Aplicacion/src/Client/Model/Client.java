
package Client.Model;

import Client.Controller.MainMenuController;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client {
    
    private MainMenuController controller;
    
    public Client(MainMenuController controller){
        this.controller = controller;
    }
    
    public void connect(){
        try {
            Socket clientSocket = new Socket("localhost", 5000);
            CommunicationThread comunicationThread = new CommunicationThread(clientSocket, controller);
            comunicationThread.start();
            controller.setVisible(true);
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
