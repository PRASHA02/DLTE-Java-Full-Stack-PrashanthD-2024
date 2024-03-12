package application.db.Services;

import application.db.Entities.Customer;
import application.db.Exception.UserNotFoundException;
import application.db.Remotes.StorageTarget;
import application.db.Remotes.UserInfoRepository;

import java.sql.SQLException;
import java.util.ResourceBundle;

public class UserInfoServices {
    UserInfoRepository userInfoRepository;
    private ResourceBundle resourceBundle = ResourceBundle.getBundle("information");

    public UserInfoServices(StorageTarget storageTarget){
        userInfoRepository = storageTarget.getUserInfoRepository();
    }

    public Customer callValidateUser(String userName,String password) throws SQLException {
        try{
            return userInfoRepository.validateUser(userName,password);
        }catch(UserNotFoundException | SQLException userNotFoundException){
            throw userNotFoundException;
        }
    }

    public void callDepositAmountInto(String userName,String password,Long amount){
        try{

            userInfoRepository.DepositAmountInto(userName,password,amount);
        }catch(UserNotFoundException userNotFoundException){
            throw userNotFoundException;
        }
    }

    public void callAddInformation(Customer customer){
        try{
            userInfoRepository.addInformation(customer);
        }catch(UserNotFoundException userNotFoundException){
            throw userNotFoundException;
        }
    }
}
