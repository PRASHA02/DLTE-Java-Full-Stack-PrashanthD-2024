package application.db;

import application.db.Entities.Customer;
import application.db.Middlewares.DatabaseTarget;
import application.db.Remotes.StorageTarget;
import application.db.Services.UserInfoServices;

import java.sql.SQLException;


public class App 
{
    public static void main( String[] args ) throws SQLException {
        StorageTarget storageTarget = new DatabaseTarget();
        UserInfoServices userInfoServices = new UserInfoServices(storageTarget);
        Customer customer = new Customer();
        customer.setUsername("prash02");
        customer.setPassword("prash21");
        customer.setAddress("karkala");
        customer.setEmail("prash@gmail.com");
        customer.setContact(7892675177L);
        customer.setInitialBalance(265251L);
        userInfoServices.callAddInformation(customer);
    }
}
