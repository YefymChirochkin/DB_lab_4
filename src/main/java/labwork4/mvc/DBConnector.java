package labwork4.mvc;

import java.sql.Connection;
import java.sql.DriverManager;


public class DBConnector {

    private static final String DB_URL = "jdbc:mysql://localhost/mydb?allowPublicKeyRetrieval=true&serverTimezone=Europe/Moscow&useSSL=false";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Password123!";
    private static Connection connection;

    public DBConnector() {
    }

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            if (connection == null || connection.isClosed()) {
                try {
                    connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

}
