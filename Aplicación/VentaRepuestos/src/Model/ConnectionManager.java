
package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ConnectionManager {
    private static Connection connection = null;
    private static Statement statement = null;
    private static String IP = "localhost";
    private static String USERNAME = "sa";
    private static String PASSWORD = "12345";
    
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
    
    public static void insert(String table, ArrayList<String> columns, ArrayList<String> values){
        
    }
    
    public static ArrayList<ArrayList<String>> select(ArrayList<String> values, String table, ArrayList<String> conditions){
        ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
        return result;
    }
    
    public static void update(String table, ArrayList<String> values, ArrayList<String> conditions){
        
    }
    
    public static void delete(String table, ArrayList<String> conditions){
        
    }
    
}
