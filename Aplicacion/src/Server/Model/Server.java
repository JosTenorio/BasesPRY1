
package Server.Model;

import Server.Controller.ConnectionController;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server {
    
    private ConnectionController controller;
    
    public Server(ConnectionController controller) {
        this.controller = controller;
        this.connect();
    }
    
    public void connect(){
        try {
            ServerSocket serverSocket = new ServerSocket(5000);
            controller.addStatus("Server Active: Waiting for Conections...");
            Socket clientSocket = serverSocket.accept();
            CommunicationThread comunicationThread = new CommunicationThread(clientSocket, controller);
            comunicationThread.start();
            controller.addStatus("Client Connected");
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
