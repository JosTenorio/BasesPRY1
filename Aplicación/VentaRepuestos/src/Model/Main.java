
package Model;

import Controller.MainMenuController;
import View.MainMenuDisplay;
import java.util.ArrayList;

public class Main {
    
    public static void main(String[] args){
            ConnectionManager.logIn("192.168.1.10", "sa", "2019064588");
            //ConnectionManager.logIn("192.168.100.45", "sa", "12345");
            //MainMenuController controller = new MainMenuController(new MainMenuDisplay()); 

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
//            PartQuery.insertPart("Motor V8", "Walker", "AJS Auto Parts");

            //EJEMPLO ASOCIAR PARTE-PROVEEDOR
//            PartQuery.asociatePartProvider("9", "Megapartes Martínez", "1500", "20");

            //EJEMPLO ASOCIAR PARTE-CARRO
//            PartQuery.asociatePartCar("", "Yaris", "2008");

            //EJEMPLO BORRAR PARTE
//           PartQuery.deletePart("1");

            //EJEMPLO MODIFICAR PRECIOS DE PROVISION
//           PartQuery.modifyPartProvider("9", "Megapartes Martínez", "1500", "20");
            
            //EJEMPLO INSERTAR ORDEN
//           OrderQuery.insertOrder("30", "05", "2020", "**asdasd*asd*asd48{ñ", false);

            //EJEMPLO INSERTAR DETALLE
//            OrderQuery.addDetail("1", "-8954", "5", "Super Repuestos");
            
    } 
}
