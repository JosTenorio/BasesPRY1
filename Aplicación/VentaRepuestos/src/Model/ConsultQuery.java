
package Model;

import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConsultQuery {  
    
   public static ArrayList<String[]> listClientsEssentials(){
       ArrayList<String[]> clientList = new ArrayList<>();
       try {
           ArrayList<String> columnsPerson = new ArrayList<>(){
               {
                   add("ID_CLIENTE");
                   add("NOMBRE");
                   add("CEDULA");
               }
           };
           ResultSet rsPerson = ConnectionManager.select(columnsPerson, "PERSONA");
           while(rsPerson.next()){
               String[] person = new String[5];
               for (int i = 1; i <= columnsPerson.size(); i++)
                   person[i-1] = String.valueOf(rsPerson.getObject(i));
               person[4] = "FALSE";
               clientList.add(person);
           }
           ArrayList<String> columnsOrg = new ArrayList<>(){
               {
                   add("ID_CLIENTE");
                   add("NOMBRE");
                   add("CEDULA_JUR");
               }
           };
           ResultSet rsOrg = ConnectionManager.select(columnsOrg, "ORGANIZACION");
           while(rsOrg.next()){
               String[] org = new String[5];
               for (int i = 1; i <= columnsOrg.size(); i++)
                   org[i-1] = String.valueOf(rsOrg.getObject(i));
               org[4] = "TRUE";
               clientList.add(org);
           }
           for (String[] people : clientList){
               ResultSet rsClient = ConnectionManager.select("TIPO", "ESTADO", "ID = (SELECT ID_ESTADO FROM CLIENTE WHERE ID = " + people[0] + ")");
               rsClient.next();
               people[3] = rsClient.getString("TIPO");
           }
       } catch (SQLException ex) {
           Logger.getLogger(ConsultQuery.class.getName()).log(Level.SEVERE, null, ex);
       }
       return clientList;
   }
   
   public static ArrayList<String> listStatusTypes(){
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
   
   public static String[] listClient(String[] clientInfo){
       String[] client = new String[10];
       client[0] = clientInfo[1];
       client[1] = clientInfo[2];
       client[2] = clientInfo[3];
       client[9] = clientInfo[4];
       for (int i = 3; i < 9; i++)
           client[i] = "";
       String clientCed = clientInfo[2];
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
               ResultSet rs = ConnectionManager.select(columns, "ORGANIZACION", "CEDULA_JUR = " + clientCed);
               rs.next();
               client[3] = rs.getString("DIRECCION");
               client[4] = rs.getString("CIUDAD");
               client[5] = rs.getString("NOMBRE_CONTACTO");
               client[6] = rs.getString("CARGO_CONTACTO");
               rs.close();
               rs = ConnectionManager.select("TELEFONO", "TELEFONOS_ORGANIZACION", "CEDULA_ORGANIZACION = " + clientCed);
               int i = 7;
               while(rs.next() && i < 9){
                   client[i] = rs.getString("TELEFONO");
                   i++;
               }
           }
           else{
               ResultSet rs = ConnectionManager.select(columns, "PERSONA", "CEDULA = " + clientCed);
               rs.next();
               client[3] = rs.getString("DIRECCION");
               client[4] = rs.getString("CIUDAD");
               rs.close();
               rs = ConnectionManager.select("TELEFONO", "TELEFONOS_PERSONA", "CEDULA_PERSONA = " + clientCed);
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
   
   public static ArrayList<String[]> listParts(){
       ArrayList<String[]> partList = new ArrayList<>();
       try {
           ArrayList<String> columnsPart = new ArrayList<>(){
               {
                   add("ID");
                   add("NOMBRE");
                   add("ID_FABRICANTE");
                   add("ID_MARCA");
               }
           };
           ResultSet rsPart = ConnectionManager.select(columnsPart, "PARTE");
           while(rsPart.next()){
               String[] part = new String[4];
               for (int i = 1; i <= columnsPart.size(); i++)
                   part[i-1] = String.valueOf(rsPart.getObject(i));
               partList.add(part);
           }
           for (String[] part : partList){
               ResultSet rs = ConnectionManager.select("NOMBRE", "FAB_PARTES", "ID = " + part[2]);
               rs.next();
               part[2] = rs.getString("NOMBRE");
               rs.close();
               rs = ConnectionManager.select("NOMBRE", "MARCA", "ID = " + part[3]);
               rs.next();
               part[3] = rs.getString("NOMBRE");
               rs.close();
           }
       } catch (SQLException ex) {
           Logger.getLogger(ConsultQuery.class.getName()).log(Level.SEVERE, null, ex);
       }
       return partList;
   }
   
   public static ArrayList<String> listFabPartsNames(){
       ArrayList<String> fabPartsList = new ArrayList<>();
       try {
           ResultSet rs = ConnectionManager.select("NOMBRE", "FAB_PARTES");
           while(rs.next()){
               fabPartsList.add(rs.getString("NOMBRE"));
           }
       } catch (SQLException ex) {
           Logger.getLogger(ConsultQuery.class.getName()).log(Level.SEVERE, null, ex);
       }
       return fabPartsList;
   }
   
   public static ArrayList<String> listBrandsNames(){
       ArrayList<String> brandsList = new ArrayList<>();
       try {
           ResultSet rs = ConnectionManager.select("NOMBRE", "MARCA");
           while(rs.next()){
               brandsList.add(rs.getString("NOMBRE"));
           }
       } catch (SQLException ex) {
           Logger.getLogger(ConsultQuery.class.getName()).log(Level.SEVERE, null, ex);
       }
       return brandsList;
   }
}
