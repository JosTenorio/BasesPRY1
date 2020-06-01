
package Model;

import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConsultQuery {  
    
   public static ArrayList<String[]> listClients(){
       ArrayList<String[]> clientList = new ArrayList<>();
       try {
           ArrayList<String> columnsPerson = new ArrayList<>(){
               {
                   add("NOMBRE");
                   add("CEDULA");
                   add("ID_CLIENTE");
               }
           };
           ResultSet rsPerson = ConnectionManager.select(columnsPerson, "PERSONA");
           while(rsPerson.next()){
               String[] person = new String[4];
               for (int i = 1; i <= columnsPerson.size(); i++)
                   person[i-1] = String.valueOf(rsPerson.getObject(i));
               person[3] = "FALSE";
               clientList.add(person);
           }
           ArrayList<String> columnsOrg = new ArrayList<>(){
               {
                   add("NOMBRE");
                   add("CEDULA_JUR");
                   add("ID_CLIENTE");
               }
           };
           ResultSet rsOrg = ConnectionManager.select(columnsOrg, "ORGANIZACION");
           while(rsOrg.next()){
               String[] org = new String[4];
               for (int i = 1; i <= columnsOrg.size(); i++)
                   org[i-1] = String.valueOf(rsOrg.getObject(i));
               org[3] = "TRUE";
               clientList.add(org);
           }
           for (String[] people : clientList){
               ResultSet rsClient = ConnectionManager.select("TIPO", "ESTADO", "ID = (SELECT ID_ESTADO FROM CLIENTE WHERE ID = " + people[2] + ")");
               rsClient.next();
               people[2] = rsClient.getString("TIPO");
           }
       } catch (SQLException ex) {
           Logger.getLogger(ConsultQuery.class.getName()).log(Level.SEVERE, null, ex);
       }
       return clientList;
   }
   
   public static ArrayList<String> listStatus(){
       ArrayList<String> statusList = new ArrayList<>();
       try {
           ResultSet rs = ConnectionManager.select("TIPO", "ESTADO");
           while(rs.next()){
               statusList.add(rs.getString("TIPO"));
           }
       } catch (SQLException ex) {
           Logger.getLogger(ConsultQuery.class.getName()).log(Level.SEVERE, null, ex);
       }
       return statusList;
   }
   
   public static String[] getClient(String[] clientInfo){
       String[] client = new String[10];
       client[0] = clientInfo[0];
       client[1] = clientInfo[1];
       client[2] = clientInfo[2];
       client[9] = clientInfo[3];
       for (int i = 3; i < 9; i++)
           client[i] = "";
       boolean organization = ("TRUE".equals(clientInfo[3]));
       try{
           ArrayList<String> columns = new ArrayList<>(){
               {
                   add("DIRECCION");
                   add("CIUDAD");
                   if (organization){
                       add("NOMBRE_CONTACTO");
                       add("CARGO_CONTACTO");
                   }
               }
           };
           if (organization){
               ResultSet rs = ConnectionManager.select(columns, "ORGANIZACION", "CEDULA_JUR = " + clientInfo[1]);
               rs.next();
               client[3] = rs.getString("DIRECCION");
               client[4] = rs.getString("CIUDAD");
               client[5] = rs.getString("NOMBRE_CONTACTO");
               client[6] = rs.getString("CARGO_CONTACTO");
               rs.close();
               rs = ConnectionManager.select("TELEFONO", "TELEFONOS_ORGANIZACION", "CEDULA_ORGANIZACION = " + clientInfo[1]);
               int i = 7;
               while(rs.next() && i < 9){
                   client[i] = rs.getString("TELEFONO");
                   i++;
               }
           }
           else{
               ResultSet rs = ConnectionManager.select(columns, "PERSONA", "CEDULA = " + clientInfo[1]);
               rs.next();
               client[3] = rs.getString("DIRECCION");
               client[4] = rs.getString("CIUDAD");
               rs.close();
               rs = ConnectionManager.select("TELEFONO", "TELEFONOS_PERSONA", "CEDULA_PERSONA = " + clientInfo[1]);
               int i = 7;
               while(rs.next() && i < 9){
                   client[i] = rs.getString("TELEFONO");
                   i++;
               }
           }
       } catch (SQLException ex) {
           Logger.getLogger(ConsultQuery.class.getName()).log(Level.SEVERE, null, ex);
       }
       return client;
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
