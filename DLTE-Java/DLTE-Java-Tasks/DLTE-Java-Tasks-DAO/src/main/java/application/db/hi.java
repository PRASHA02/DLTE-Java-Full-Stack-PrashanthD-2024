package application.db;

import application.db.Entities.Customer;
import application.db.Middlewares.DatabaseTarget;
import application.db.Remotes.StorageTarget;
import application.db.Services.UserInfoServices;


import java.sql.SQLException;

public class hi {
    public static void main(String[] args) throws SQLException {
        StorageTarget storageTarget = new DatabaseTarget();
        UserInfoServices userInfoServices = new UserInfoServices(storageTarget);
        Customer customer = new Customer();
//        customer.setUsername("prash02");
//        customer.setPassword("prash21");
//        customer.setAddress("karkala");
//        customer.setEmail("prash@gmail.com");
//        customer.setContact(7892675177L);
//        customer.setInitialBalance(265251L);
//        userInfoServices.callAddInformation(customer);
          Customer customer1 = userInfoServices.callValidateUser("prash02","prash21");
//          System.out.println("username: "+customer1.getUsername()+" password:  "+customer1.getPassword());
        if (customer1 != null) {
            System.out.println("username: " + customer1.getUsername() + " password: " + customer1.getPassword());
        } else {
            System.out.println("User validation failed. Invalid credentials.");
        }
    }
}
