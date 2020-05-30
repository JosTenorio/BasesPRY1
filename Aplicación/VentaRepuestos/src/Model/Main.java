
package Model;

import Controller.MainMenuController;
import View.MainMenuDisplay;
import java.util.ArrayList;

public class Main {
    
    public static void main(String[] args){
            //ConnectionManager.logIn("192.168.1.10", "sa", "2019064588");
            ConnectionManager.logIn("192.168.100.45", "sa", "12345");
            MainMenuController controller = new MainMenuController(new MainMenuDisplay()); 

            //EJEMPLO MODIFICAR CLIENTE
//            ArrayList<String> info = new ArrayList<>(){
//                {
//                    add("Jose Pablo Muñoz");
//                    add("Colindando Protecto");
//                    add("Cartago");
//                }
//                
//            };
//            ArrayList<String> phones = new ArrayList<>(){
//                {
//                    add("22732576");
//                    add("785496325");
//                    add("89230091");
//                }
//            };
//            ClientQuery.modifyClient("117910466", "INACTIVO", info, phones, false);

            //EJEMPLO INSERTAR PARTE
//            PartQuery.insertPart("Llanta A02", "Walker", "Auto Spare Parts");

            //EJEMPLO ASOCIAR PARTE-PROVEEDOR
//            PartQuery.asociatePartProvider("1", "Super Repuestos", "100", "20");

            //EJEMPLO ASOCIAR PARTE-CARRO
//            PartQuery.asociatePartCar("1", "Yaris", "2008");

            //EJEMPLO BORRAR PARTE
//            PartQuery.deletePart("1");

            //EJEMPLO MODIFICAR PRECIOS DE PROVISION
//           PartQuery.modifyPartProvider("1", "Super Repuestos", "100", "20");
            
            //EJEMPLO INSERTAR ORDEN
//            OrderQuery.insertOrder("25", "05", "2020", "1234567890", true);

            //EJEMPLO INSERTAR DETALLE
//            OrderQuery.addDetail("1", "1", "5", "Super Repuestos");
            
    } 
}
