
package Controller;

import Model.ConsultQuery;
import View.ClientInformationDisplay;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JFrame;

public class ClientInformationController implements ActionListener{
    
    private static final ClientInformationDisplay display = new ClientInformationDisplay();
    private static ClientInformationController firstInstance = null;
    
    private ClientInformationController(){
        init();
    }
    
    public static ClientInformationController getInstance(){
        if (firstInstance == null)
            firstInstance = new ClientInformationController();
        return firstInstance;
    }
    
    private void init(){
        display.jButton_Accept.addActionListener(this);
        display.setResizable(false);
        display.setLocationRelativeTo(null);
        display.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    
    public void makeVisible(boolean visible){
        display.setVisible(visible);
        if (visible == true)
            setComboBoxData();
    }      

    private void setComboBoxData(){
        ArrayList<String> statusList = ConsultQuery.listStatus();
        for (String status : statusList)
            display.comboBoxModel.addElement(status);
        display.jComboBox_Status.setModel(display.comboBoxModel);
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
    }
}
