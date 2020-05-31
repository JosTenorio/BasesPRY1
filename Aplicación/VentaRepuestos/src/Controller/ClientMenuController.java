
package Controller;

import View.ClientMenuDisplay;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientMenuController implements ActionListener{
    
    private static final ClientMenuDisplay display = new ClientMenuDisplay();
    private static ClientMenuController firstInstance = null;
    
    private ClientMenuController(){
        init();
    }
    
    public static ClientMenuController getInstance(){
        if (firstInstance == null)
            firstInstance = new ClientMenuController();
        return firstInstance;
    }
    
    private void init(){
        display.jButton_NewClient.addActionListener(this);
        display.jButton_ModifyClient.addActionListener(this);
        display.jButton_ViewClients.addActionListener(this);
        display.setResizable(false);
        display.setLocationRelativeTo(null);
    }
    
    public void makeVisible(boolean visible){
        display.setVisible(visible);
    }
            

    @Override
    public void actionPerformed(ActionEvent e) {

        display.setVisible(false);
    }
}
