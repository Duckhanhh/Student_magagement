package database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCUtil {
    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://DESKTOP-DUCKKK\\SQLEXPRESS:1433;databaseName=Student_management;trustServerCertificate=true";
            String userName = "sa";
            String passWord = "123456789";
            connection = DriverManager.getConnection(url, userName, passWord);
            System.out.println("Connected to the Server");
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return connection;
    }
    public  static void closeConnection(Connection connection){
        try {
            if(connection != null) {
                connection.close();
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
