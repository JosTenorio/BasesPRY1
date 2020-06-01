
package Model;

import Controller.MainMenuController;

public class Main {
    
    public static void main(String[] args){
            ConnectionManager.logIn("192.168.1.10", "sa", "2019064588");
            //ConnectionManager.logIn("192.168.100.45", "sa", "12345");
            MainMenuController controller = MainMenuController.getInstance();
            controller.makeVisible(true);

            //EJEMPLO ASOCIAR PARTE-PROVEEDOR
//            PartQuery.asociatePartProvider("1", "1", "100", "20");

            //EJEMPLO ASOCIAR PARTE-CARRO
//            PartQuery.asociatePartCar("1", "Yaris", "2008");

            //EJEMPLO MODIFICAR PRECIOS DE PROVISION
//           PartQuery.modifyPartProvider("1", "Super Repuestos", "100", "20");
            
            //EJEMPLO INSERTAR ORDEN
            OrderQuery.insertOrder("01", "06", "2020", "117310466", false);

            //EJEMPLO INSERTAR DETALLE
//            OrderQuery.addDetail("1", "1", "5", "Super Repuestos");
            
    } 
}
