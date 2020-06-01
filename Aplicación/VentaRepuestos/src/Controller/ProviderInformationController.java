
package Controller;

import Model.ConsultQuery;
import Model.PartQuery;
import View.ProviderInformationDisplay;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JFrame;

public class ProviderInformationController implements ActionListener{
    
    private static final ProviderInformationDisplay display = new ProviderInformationDisplay();
    private static ProviderInformationController firstInstance = null;
    ArrayList<String[]> partList;
    ArrayList<String[]> provList;
    
    private ProviderInformationController(){
        init();
    }
    
    public static ProviderInformationController getInstance(){
        if (firstInstance == null)
            firstInstance = new ProviderInformationController();
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
        display.comboBoxModelPart.removeAllElements();
        display.comboBoxModelProv.removeAllElements();
        partList = ConsultQuery.listPartsNameId();
        for (String[] part : partList)
            display.comboBoxModelPart.addElement(part[1]);
        provList = ConsultQuery.listProvidersNameId();
        for (String[] prov : provList)
            display.comboBoxModelProv.addElement(prov[1]);
        display.jComboBox_Part.setModel(display.comboBoxModelPart);
        display.jComboBox_Provider.setModel(display.comboBoxModelProv);
    }
    
    public void clearInfo(){
        display.jTextField_PriceProv.setText("");
        display.jTextField_Gain.setText("");
        display.jTextField_PricePub.setText("");
        display.jTextField_PricePub.setEditable(false);
    }
    
    public void setInfo(String[] partProv){
        //add info to textbox and selected combo boxes
        display.jComboBox_Part.setEnabled(false);
        display.jComboBox_Provider.setEnabled(false);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(display.jButton_Accept)){
            addNewPartProvider();
            ProviderMenuController.getInstance().updateTableData();
            display.setVisible(false);
        }
    }
    
    private void addNewPartProvider(){
        String partId = partList.get(display.jComboBox_Part.getSelectedIndex())[0];
        String providerId = provList.get(display.jComboBox_Provider.getSelectedIndex())[0];
        String providerPrice = display.jTextField_PriceProv.getText();
        String gain = display.jTextField_Gain.getText();
        PartQuery.asociatePartProvider(partId, providerId, providerPrice, gain);
    }
    
}
