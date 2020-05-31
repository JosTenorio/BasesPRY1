
package Model;

import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConsultQuery {  
    
   public static ArrayList<ArrayList<String>> listClients(){
       ArrayList<ArrayList<String>> clientList = new ArrayList<>();
       try {
           ArrayList<String> columnsPerson = new ArrayList<>(){
               {
                   add("ID_CLIENTE");
                   add("NOMBRE");
                   add("CEDULA");
                   add("DIRECCION");
                   add("CIUDAD");
               }
           };
           ResultSet rsPerson = ConnectionManager.select(columnsPerson, "PERSONA");
           while(rsPerson.next()){
               ArrayList<String> person = new ArrayList<>();
               for (int i = 1; i <= columnsPerson.size()-1; i++)
                   person.add(String.valueOf(rsPerson.getObject(i)));
               clientList.add(person);
           }
           ArrayList<String> columnsOrg = new ArrayList<>(){
               {
                   add("ID_CLIENTE");
                   add("NOMBRE");
                   add("CEDULA_JUR");
                   add("DIRECCION");
                   add("CIUDAD");
                   add("NOMBRE_CONTACTO");
                   add("CARGO_CONTACTO");
               }
           };
           ResultSet rsOrg = ConnectionManager.select(columnsOrg, "ORGANIZACION");
           while(rsOrg.next()){
               ArrayList<String> org = new ArrayList<>();
               for (int i = 1; i <+ columnsOrg.size()-1; i++)
                   org.add(String.valueOf(rsOrg.getObject(i)));
               clientList.add(org);
           }
           for (ArrayList<String> people : clientList){
               ResultSet rsClient = ConnectionManager.select("TIPO", "ESTADO", "ID = (SELECT ID_ESTADO FROM CLIENTE WHERE ID = " + people.get(0) + ")");
               rsClient.next();
               people.add(rsClient.getString("TIPO"));
           }
       } catch (SQLException ex) {
           Logger.getLogger(ConsultQuery.class.getName()).log(Level.SEVERE, null, ex);
       }
       return clientList;
   }
   
   public static ArrayList<ArrayList<String>> listPartsForCar(){
       ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
       return result;
   }
   
   public static ArrayList<ArrayList<String>> listProvidersForPart(){
       ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
       return result;
   }
}
