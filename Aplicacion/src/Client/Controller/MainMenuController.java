
package Client.Controller;

import Client.Model.Client;
import Client.View.MainMenuDisplay;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuController implements ActionListener{
    
    private Client client;
    private MainMenuDisplay display;
    
    public MainMenuController() {
        this.client = new Client(this);
        this.display = new MainMenuDisplay();
        this.init();
    }
    
    public void init(){
        display.setResizable(false);
        display.setLocationRelativeTo(null);
        //add all action listeners here
    }
    
    public void setVisible(boolean visible){
        display.setVisible(visible);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //handle all window actions here
    }
    
}
