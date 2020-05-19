
package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionManager {
    private static Connection connection = null;
    private static Statement statement = null;
    private static final String CONNECTION_URL = "jdbc:sqlserver://192.168.1.10:1433;databaseName=BasesPRY1;user=sa;password=2019064588";
    
    private static void setConnection () throws SQLException {
       connection = DriverManager.getConnection(CONNECTION_URL);
       statement = connection.createStatement ();
    }
    
    private static void executeQuery (String query) throws SQLException {
        if (connection == null)
            setConnection();
        statement.execute(query);
    }
    
    private static ResultSet executeDataQuery (String query) throws SQLException {
        if (connection == null)
            setConnection();
        ResultSet rs = statement.executeQuery(query);
        return rs;
    }
    
    public static void main(String[] args){
        ResultSet rs;
        try {
             ConnectionManager.executeQuery("DELETE FROM ESTADO WHERE TIPO = 'RECHAZADO'");
             rs = ConnectionManager.executeDataQuery("SELECT TIPO FROM ESTADO");
             while (rs.next()){
                System.out.println(rs.getString("TIPO"));
             }
        } catch (SQLException ex) {
            System.out.print(ex.getMessage());
        }
    } 
}
