package db.connection;

import oracle.jdbc.driver.OracleDriver;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Database {
    private Connection connection;
    public  Database() throws SQLException {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("application");
        Driver driver = new OracleDriver();
        DriverManager.registerDriver(driver);
        connection = DriverManager.getConnection(resourceBundle.getString("db.url"),resourceBundle.getString("db.user"),resourceBundle.getString("db.pass"));
    }

    public  Connection getConnection(){
        return connection;
    }
}
