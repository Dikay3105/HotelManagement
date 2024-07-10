package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {

    public static Connection getConnection() throws SQLException {

        try {
            Connection cons = null;
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            cons = DriverManager.getConnection("jdbc:sqlserver://DESKTOP-GO5GHDC;databaseName=QLKHACHSAN;portNumber=1433;encrypt=true;trustServerCertificate=true;user=sa;password=khoa3105;");
            return cons;
            
        } catch (ClassNotFoundException ex) {
        }
        return null;
    }
    
    public static void main(String[] args) throws SQLException {
        java.sql.Connection cons = getConnection();
        
    }

}
