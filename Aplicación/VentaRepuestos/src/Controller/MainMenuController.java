
package Controller;

import View.MainMenuDisplay;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuController implements ActionListener{
    
    private MainMenuDisplay display;
    
    public MainMenuController(MainMenuDisplay display){
        this.display = display;
        this.init();
    }
    
    private void init(){
        //add all action listeners here
        display.setResizable(false);
        display.setLocationRelativeTo(null);
        display.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //handle all window actions here
    }
    
}
