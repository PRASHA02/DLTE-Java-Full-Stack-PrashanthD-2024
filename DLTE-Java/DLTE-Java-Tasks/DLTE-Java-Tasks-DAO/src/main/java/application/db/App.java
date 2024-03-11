package application.db;

import application.db.Middlewares.DatabaseTarget;
import application.db.Remotes.StorageTarget;
import application.db.Services.UserInfoServices;
import com.sun.org.apache.xpath.internal.operations.String;

import java.sql.SQLException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws SQLException {
        StorageTarget storageTarget = new DatabaseTarget();
        UserInfoServices userInfoServices = new UserInfoServices(storageTarget);
    }
}
