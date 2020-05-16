
package Client.Model;

import Client.Controller.MainMenuController;
import ComunicationPackages.IPackage;
import Server.Model.Server;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CommunicationThread extends Thread{
    
    private Socket clientSocket;
    private MainMenuController controller;
    private boolean running;
    
    public CommunicationThread(Socket client, MainMenuController controller){
        this.clientSocket = client;
        this.controller = controller;
        this.running = true;
    }
    
    public void run(){
        while (running) {
            try {
                ObjectInputStream input = new ObjectInputStream(clientSocket.getInputStream());
                IPackage inputPackage = (IPackage) input.readObject();
                switch(inputPackage.getType()){
                    //evaluate all input packages here
                }
            } catch (IOException | ClassNotFoundException ex) {
                System.out.println(ex); 
                Logger.getLogger(CommunicationThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void sendPackage(IPackage pack){
        try {
            ObjectOutputStream output = new ObjectOutputStream(clientSocket.getOutputStream());
            output.writeObject(pack);
    }   catch (IOException ex) {
            Logger.getLogger(CommunicationThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
