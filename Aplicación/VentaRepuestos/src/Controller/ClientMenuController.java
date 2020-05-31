
package Controller;

import Model.ConsultQuery;
import View.ClientMenuDisplay;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

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
            setTableData();
    }
    
    private void setTableData(){
        clientList = ConsultQuery.listClients();
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Nombre");
        model.addColumn("Cedula");
        model.addColumn("Estado");
        for (ArrayList<String> list : clientList){
            String[] row = {list.get(1), list.get(2), list.get(list.size()-1)};
            System.out.println("Row:" + row[0] + row[1] + row[2]);
            model.addRow(row);
        }
        display.jTable_Clients.setModel(model);
    }
            

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(display.jButton_Back))
            MainMenuController.getInstance().makeVisible(true);
        display.setVisible(false);
    }
}
