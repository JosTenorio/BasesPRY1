
package Server.Model;

import Server.Controller.ConnectionController;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import ComunicationPackages.Package;

public class CommunicationThread extends Thread{
    
    private Socket clientSocket;
    private ConnectionController controller;
    private boolean running;
    
    public CommunicationThread(Socket client, ConnectionController controller){
        this.clientSocket = client;
        this.controller = controller;
        this.running = true;
    }
    
    @Override
    public void run(){
        while(running){
            try {
                ObjectInputStream input = new ObjectInputStream(clientSocket.getInputStream());
                Package inputPackage = (Package) input.readObject();
                switch(inputPackage.getAction()){
                    //evaluate all input packages here
                }
            } catch (IOException | ClassNotFoundException ex) {
                System.out.println(ex); 
                Logger.getLogger(CommunicationThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void sendPackage(Package pack) {
        try {
            ObjectOutputStream output = new ObjectOutputStream(clientSocket.getOutputStream());
            output.writeObject(pack);
        } catch (IOException ex) {
            Logger.getLogger(CommunicationThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
