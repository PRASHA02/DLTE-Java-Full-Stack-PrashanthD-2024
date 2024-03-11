package application.db.Services;

import application.db.Exception.UserNotFoundException;
import application.db.Remotes.StorageTarget;
import application.db.Remotes.UserInfoRepository;
import org.example.Entities.Customer;

import java.util.ResourceBundle;

public class UserInfoServices {
    UserInfoRepository userInfoRepository;
    private ResourceBundle resourceBundle = ResourceBundle.getBundle("information");

    public UserInfoServices(StorageTarget storageTarget){
        userInfoRepository = storageTarget.getUserInfoRepository();
    }

    public Customer callValidateUser(String userName){
        try{
            return userInfoRepository.validateUser(userName);
        }catch(UserNotFoundException userNotFoundException){
            throw userNotFoundException;
        }
    }

    public void callDepositAmountInto(String userName,Long amount){
        try{

            userInfoRepository.DepositAmountInto(userName,amount);
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
