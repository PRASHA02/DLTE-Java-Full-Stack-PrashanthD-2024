package org.example.Services;

import org.example.Entities.Customer;
import org.example.Exception.UserNotFoundException;
import org.example.Middleware.UserInformationFileRepository;
import org.example.Remotes.UserInfoRepository;

import java.util.ArrayList;
import java.util.Date;

public class MyBankServices {
    UserInfoRepository userInfoRepository;

    public MyBankServices() {
        userInfoRepository = new UserInformationFileRepository();
    }

    public void Intialize() {
        StringBuilder builder = new StringBuilder("Deposit,0");
        builder.append("," + new Date());
        ArrayList<StringBuilder> transactionOne = new ArrayList<>();
        transactionOne.add(builder);
        Customer user1 = new Customer("prash02", "prash321", "karkala", "prash@gmail.com", 9777777888L, 1000L, transactionOne);
       Customer user2 = new Customer("Akshira", "akshira123", "Kannur", "akash@gmail", 987455545L, 4444L, transactionOne);
       Customer user3 = new Customer("Ajay", "ajay123", "Mangalore", "ajay@gmail", 9888888888L, 500L, transactionOne);
       Customer user4 = new Customer("Akshay", "akshay123", "Dubai", "akshay@gmail", 9777777888L, 1000L, transactionOne);
     
        UserInformationFileRepository userinfo = new UserInformationFileRepository();
       userinfo.addInformation(user1);
       userinfo.addInformation(user2);
        userinfo.addInformation(user3);
        userinfo.addInformation(user4);
        System.out.println("User created");

    }

    public void callDeposit(String username, Long amount) {
            userInfoRepository.DepositAmountInto(username, amount);

    }

    public Customer callValidateUser(String username) {
        try {
           Customer customer= userInfoRepository.validateUser(username);
           return customer;
        }
        catch (UserNotFoundException exception) {
            System.out.println(exception);
        }
        return null;
    }

    public void callCheck(){
        UserInformationFileRepository userinfo = new UserInformationFileRepository();
        userinfo.check();
    }
}

