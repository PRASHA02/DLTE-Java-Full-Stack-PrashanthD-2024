package debit.cards.dao.database;



import oracle.jdbc.driver.OracleDriver;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

//Connecting the database connection
public class Database {
    public static Connection connection;
    public Database() throws SQLException {
        try{
            ResourceBundle resourceBundle = ResourceBundle.getBundle("application");
            Driver driver = new OracleDriver();
            DriverManager.registerDriver(driver);
            connection = DriverManager.getConnection(resourceBundle.getString("db.url"),resourceBundle.getString("db.user"),resourceBundle.getString("db.pass"));
        }catch(SQLException e){
            throw new SQLException();
        }

    }

    public static Connection getConnection(){
        return connection;
    }
}
