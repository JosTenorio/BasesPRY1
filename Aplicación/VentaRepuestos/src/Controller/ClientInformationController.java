
package Controller;

import Model.ClientQuery;
import Model.ConsultQuery;
import View.ClientInformationDisplay;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JFrame;

public class ClientInformationController implements ActionListener{
    
    private static final ClientInformationDisplay display = new ClientInformationDisplay();
    private static ClientInformationController firstInstance = null;
    private boolean newClient;
    
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
    
    public void makeVisible(boolean visible, boolean newClient){
        display.setVisible(visible);
        this.newClient = newClient;
        if (visible == true)
            updateComboBoxData();
    }      

    public void updateComboBoxData(){
        display.comboBoxModel.removeAllElements();
        ArrayList<String> statusList = ConsultQuery.listStatus();
        for (String status : statusList)
            display.comboBoxModel.addElement(status);
        display.jComboBox_Status.setModel(display.comboBoxModel);
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(display.jButton_Accept)){
            if (newClient)
                addNewClient();
            ClientMenuController.getInstance().updateTableData();
            display.setVisible(false);
        }
    }
    
    private void addNewClient(){
        boolean organization = display.jCheckBox_Org.isSelected();
        String status = String.valueOf(display.jComboBox_Status.getSelectedItem());
        ArrayList<String> info = new ArrayList<>(){
            {
                add(display.jTextField_Id.getText());
                add(display.jTextField_Name.getText());
                add(display.jTextField_Addres.getText());
                add(display.jTextField_City.getText());
                if (organization){
                    add(display.jTextField_ContactName.getText());
                    add(display.jTextField_ContactCharge.getText());
                }
            }
        };
        ArrayList<String> telephones = new ArrayList<>(){
            {
                if (!display.jTextField_Phone1.getText().equals(""))
                    add(display.jTextField_Phone1.getText());
                if (!display.jTextField_Phone2.getText().equals(""))
                    add(display.jTextField_Phone2.getText());
            }
        };
        ClientQuery.insertClient(status, info, telephones, organization);
    }
}
