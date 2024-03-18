package application.db.Middlewares;

import application.db.Remotes.StorageTarget;
import application.db.Remotes.UserInfoRepository;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;
import oracle.jdbc.OracleDriver;

public class DatabaseTarget implements StorageTarget {

    ResourceBundle resourceBundle = ResourceBundle.getBundle("information");
    private Connection connection;
   public DatabaseTarget() throws SQLException {
       Driver driver = new OracleDriver();
       DriverManager.registerDriver(driver);
       connection = DriverManager.getConnection(resourceBundle.getString("db.url"),resourceBundle.getString("db.user"),resourceBundle.getString("db.pass"));
   }
    @Override
    public UserInfoRepository getUserInfoRepository() {
        return new UserInfoDatabaseRepository(connection);
    }
}
