package technical.review;

import oracle.jdbc.driver.OracleDriver;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DatabaseTarget {
    static ResourceBundle resourceBundle = ResourceBundle.getBundle("employee");
    private static Connection connection;
    public static Connection DatabaseTarget() throws SQLException {
         Driver driver = new OracleDriver();
         DriverManager.registerDriver(driver);
         connection = DriverManager.getConnection(resourceBundle.getString("db.url"),resourceBundle.getString("db.user"),resourceBundle.getString("db.pass"))
         return connection;
    }

    public static void main(String[] args) throws SQLException {
        DatabaseTarget databaseTarget = new DatabaseTarget();
        System.out.println("Connection establish successfully");

    }
}
