
package Server.Controller;

import Server.Model.Server;
import Server.View.ServerDisplay;
import javax.swing.text.DefaultCaret;

public class ConnectionController {
    
    private Server server;
    private ServerDisplay display;
    
    public ConnectionController(){
        this.server = new Server(this);
        this.display = new ServerDisplay();
        this.init();
    }
    
    private void init(){
        DefaultCaret caret = (DefaultCaret) display.jTextArea_Status.getCaret();
        caret.setUpdatePolicy(DefaultCaret.OUT_BOTTOM);
        display.setLocationRelativeTo(null);
        display.setVisible(true);
    }
    
    public void addStatus(String message){
        display.jTextArea_Status.append(message + "\n");
    }
    
}
