package database;

public class DatabaseInformation {

    //Driver process
    private static String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static String port = "50988";
    private static String databaseName = "RentHouse_PRJ301";

    //Login process
    private static String user = "sa";
    private static String password = "123123";
    private static String encodingProperty = "useUnicode=true;characterEncoding=UTF-8;";
    //Get Connection process
    private static String url = "jdbc:sqlserver://localhost:" + port + ";databaseName=" + databaseName + ";encrypt=true;trustServerCertificate=true;" + encodingProperty;

    public static String getDriverName() {
        return driverName;
    }

    public static String getPort() {
        return port;
    }

    public static String getDatabaseName() {
        return databaseName;
    }

    public static String getUser() {
        return user;
    }

    public static String getPassword() {
        return password;
    }

    public static String getUrl() {
        return url;
    }
}
