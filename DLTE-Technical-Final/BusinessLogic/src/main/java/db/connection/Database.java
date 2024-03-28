package db.connection;

import backend.exceptions.ConnectionFailureException;
import oracle.jdbc.driver.OracleDriver;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

//Connecting the database connection
public class Database {
    private Connection connection;
    public  Database() throws SQLException {
        try{
            ResourceBundle resourceBundle = ResourceBundle.getBundle("application");
            Driver driver = new OracleDriver();
            DriverManager.registerDriver(driver);
            connection = DriverManager.getConnection(resourceBundle.getString("db.url"),resourceBundle.getString("db.user"),resourceBundle.getString("db.pass"));
        }catch(SQLException e){
            throw new ConnectionFailureException();
        }

    }

    public  Connection getConnection(){
        return connection;
    }
}
