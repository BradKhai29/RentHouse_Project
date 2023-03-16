package database;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {

    public static Connection getConnection() {
        Connection connection = null;
        String driverName = DatabaseInformation.getDriverName();
        String url = DatabaseInformation.getUrl();
        String user = DatabaseInformation.getUser();
        String password = DatabaseInformation.getPassword();
        
        try {
            Class.forName(driverName);
            connection = DriverManager.getConnection(url, user, password);
//            System.out.println(connection);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return connection;
    }
}
