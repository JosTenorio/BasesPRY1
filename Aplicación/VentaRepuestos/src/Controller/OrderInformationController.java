
package Controller;

import Model.ConsultQuery;
import View.OrderInformationDisplay;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JFrame;

public class OrderInformationController implements ActionListener{
    
    private static final OrderInformationDisplay display = new OrderInformationDisplay();
    private static OrderInformationController firstInstance = null;
    private ArrayList<String[]> clientList;
    
    private OrderInformationController(){
        init();
    }
    
    public static OrderInformationController getInstance(){
        if (firstInstance == null)
            firstInstance = new OrderInformationController();
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
        clearInfo();
        if (visible == true)
            updateComboBoxData();
    }      

    public void updateComboBoxData(){
        display.comboBoxModelClient.removeAllElements();
        clientList = ConsultQuery.listClientsDropdown();
        for (String[] client : clientList)
            display.comboBoxModelClient.addElement(client[0] + " (" + client[1] + ")");
        display.jComboBox_Client.setModel(display.comboBoxModelClient);
    }
    
    public void clearInfo(){
        display.jTextField_Day.setText("");
        display.jTextField_Month.setText("");
        display.jTextField_Year.setText("");
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(display.jButton_Accept)){
            addNewOrder();
            OrderMenuController.getInstance().updateTableData();
            display.setVisible(false);
        }
    }
    
    private void addNewOrder(){
        //handle new
    }
}
