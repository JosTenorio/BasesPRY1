
package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;

public class ConnectionManager {
    private static Connection connection = null;
    private static Statement statement = null;
    private static String IP = "localhost";
    private static String USERNAME = "sa";
    private static String PASSWORD = "2019064588";
    
    public static void logIn(String ip, String username, String password){
        IP = ip;
        USERNAME = username;
        PASSWORD = password;
    }
    
    public static void connect() throws SQLException {
        String url = "jdbc:sqlserver://" + IP  + ":1433;databaseName=BasesPRY1;user=" + USERNAME + ";password=" + PASSWORD;
        connection = DriverManager.getConnection(url);
        statement = connection.createStatement ();
    }
    
    private static void executeActionQuery(String query) throws SQLException {
        if (connection == null)
            connect();
        statement.execute(query);
    }
    
    private static ResultSet executeConsultQuery(String query) throws SQLException {
        if (connection == null)
            connect();
        ResultSet rs = statement.executeQuery(query);
        return rs;
    }
    
    public static void insert(String table, ArrayList<String> columns, ArrayList<String> values) throws SQLException{
        String query = "INSERT INTO " + table + "(";
        Iterator i = columns.iterator();
        while (i.hasNext())
        {
            query += i.next() + ",";
        }
        query = query.substring(0,query.length()-1) + ") VALUES (";
        i = values.iterator ();
        while (i.hasNext())
        {
            query += i.next() + ",";
        }
        query = query.substring(0,query.length()-1) + ");";
        executeActionQuery(query);
    }
    
    public static ArrayList<ArrayList<Object>> select(ArrayList<String> columns, ArrayList <String> tables) throws SQLException{
        return select (columns,tables, "");
    }
    
    public static ArrayList<ArrayList<Object>> select(ArrayList<String> columns, ArrayList <String> tables, String conditions) throws SQLException{
        String query = "SELECT ";
        Iterator i = columns.iterator();
        while (i.hasNext())
        {
            query += i.next() + ",";
        }
        query = query.substring(0,query.length()-1) + " FROM ";
        i = tables.iterator();
        while (i.hasNext())
        {
            query += i.next() + ",";
        }
        query = query.substring(0,query.length()-1);
        if (!"".equals(conditions)){
                query += " WHERE " + conditions;
        }
        System.out.println(query);
        ResultSet rs = executeConsultQuery (query);
        ArrayList<ArrayList<Object>> result = new ArrayList<>();
        while (rs.next()) 
        {
            ArrayList<Object> row = new ArrayList<>();
            for (String columnLabel : columns) 
            {
                row.add(rs.getObject (columnLabel));
            }
            result.add (row);
        }
        return result;
    }
    public static void update(String table, ArrayList<String> columns, ArrayList<String> values) throws SQLException{
        update (table,columns,values,"");
    }
    
    public static void update(String table, ArrayList<String> columns, ArrayList<String> values, String conditions) throws SQLException{
        String query = "UPDATE " + table + " VALUES ";
        Iterator it1 = columns.iterator();
        Iterator it2 = values.iterator();
        while (it1.hasNext() && it2.hasNext()) 
        {
            query += it1.next() + " = " + it2.next() + ",";
        }
        query = query.substring(0,query.length()-1);
        if (!"".equals(conditions)){
                query += " WHERE " + conditions;
        }
        executeActionQuery(query);
    }
    
    public static void deleteTable(String table) throws SQLException{
        delete (table,"","");
    }
    
    public static void deleteWithConditions(String table, String conditions) throws SQLException{
        delete (table,"", conditions);
    }
    
    public static void deleteWithSelection(String table, String selection) throws SQLException{
        delete (table,selection,"");
    }
    
    public static void delete(String table, String selection,String conditions) throws SQLException{
        String query = "DELETE " + selection +" FROM " + table;
        if (!"".equals(conditions)){
                query += " WHERE " + conditions;
        }
        executeActionQuery(query);
    }
    
}
