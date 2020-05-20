
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
    private static final String CONNECTION_URL = "jdbc:sqlserver://192.168.1.10:1433;databaseName=BasesPRY1;user=sa;password=2019064588";
    
    public static void connect() throws SQLException {
       connection = DriverManager.getConnection(CONNECTION_URL);
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
        
    }
    
    public static void update(String table, ArrayList<String> values, ArrayList<String> conditions){
        
    }
    
    public static void delete(String table, ArrayList<String> conditions){
        
    }
    
}
