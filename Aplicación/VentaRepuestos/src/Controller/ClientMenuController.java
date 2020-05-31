
package Controller;

import Model.ConsultQuery;
import View.ClientMenuDisplay;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ClientMenuController implements ActionListener{
    
    private static final ClientMenuDisplay display = new ClientMenuDisplay();
    private static ClientMenuController firstInstance = null;
    private ArrayList<ArrayList<String>> clientList;
    
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
        display.jButton_Back.addActionListener(this);
        display.setResizable(false);
        display.setLocationRelativeTo(null);
    }
    
    public void makeVisible(boolean visible){
        display.setVisible(visible);
        if (visible == true)
            updateTableData();
    }
    
    public void updateTableData(){
        display.tableModel.setRowCount(0);
        clientList = ConsultQuery.listClients();
        for (ArrayList<String> list : clientList){
            String[] row = {list.get(1), list.get(2), list.get(list.size()-1)};
            display.tableModel.addRow(row);
        }
        display.jTable_Clients.setModel(display.tableModel);
    }
            

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(display.jButton_Back)){
            MainMenuController.getInstance().makeVisible(true);
            display.setVisible(false);
        }
        if (e.getSource().equals(display.jButton_NewClient)){
            ClientInformationController.getInstance().makeVisible(true, true);
        }
    }
}
