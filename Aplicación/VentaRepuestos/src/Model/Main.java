
package Model;

import Controller.MainMenuController;

public class Main {
    
    public static void main(String[] args){
            //ConnectionManager.logIn("192.168.1.10", "sa", "2019064588");
            ConnectionManager.logIn("192.168.100.45", "sa", "12345");
            MainMenuController controller = MainMenuController.getInstance();
            controller.makeVisible(true);

            //EJEMPLO INSERTAR CLIENTE
//            ArrayList<String> info = new ArrayList<>(){
//                {
//                    add("1234567890");
//                    add("Estructuras S.A.");
//                    add("Colindando Recope");
//                    add("Ochomogo");
//                    add("Javier Munoz");
//                    add("Gerente");
//                }
//            };
//            ArrayList<String> phones = new ArrayList<>(){
//                {
//                    add("83215553");
//                }
//            };
//            ClientQuery.insertClient("ACTIVO", info, phones, true);

            //EJEMPLO MODIFICAR CLIENTE
//            ArrayList<String> info = new ArrayList<>(){
//                {
//                    add("Estructuras S.A.");
//                    add("Colindando Recope");
//                    add("Cartago");
//                    add("Javier Munoz");
//                    add("CEO");
//                }
//            };
//            ArrayList<String> phones = new ArrayList<>(){
//                {
//                    add("22732576");
//                }
//            };
//            ClientQuery.modifyClient("1234567890", "INACTIVO", info, phones, true);

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
