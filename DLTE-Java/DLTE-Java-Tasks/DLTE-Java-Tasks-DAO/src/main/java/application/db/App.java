package application.db;


import application.db.Entities.Customer;
import application.db.Exception.UserNotFoundException;
import application.db.Middlewares.DatabaseTarget;
import application.db.Middlewares.UserInfoDatabaseRepository;
import application.db.Remotes.StorageTarget;
import application.db.Remotes.UserInfoRepository;
import application.db.Services.UserInfoServices;

import java.sql.SQLException;
import java.util.*;
import java.util.logging.Level;

/**
 * Hello world! -
 *
 */
//ADDD MENU AND OTHER LOGGERS ,ALSO HANDLE USER DEFINED EXCEPTIONS HERE PLEASE, IF YOU WANT TRY ADDING MORE USERS IN THE TABLE IF ANY PROBLEM CALL ME,
 //UN COMMENT LINES 26-31 FOR ADDING NEW DATA CHANGE THE NAME OF CUSTOMER EACH TIME FOR ADDING IT IS PRIMARY KEY
public class App {
    public static void main(String[] args) throws SQLException {
        StorageTarget storageTarget = new DatabaseTarget();
        UserInfoServices userInfoServices = new UserInfoServices(storageTarget);
//        StringBuilder builder = new StringBuilder("Deposit,0");
//        builder.append("," + new Date());
//        ArrayList<StringBuilder> transactionOne = new ArrayList<>();
//        transactionOne.add(builder);
//        Customer customer=new Customer("Prashanth", "prashanth123", "Mangalore", "prashanth@gmail", 987455335L, 1000L, transactionOne);
//        userInfoServices.callAddInformation(customer);
        Scanner scanner = new Scanner(System.in);
        String username, password;
        Customer newCustomer = null;
        boolean flag=false;
        //userInfoServices.callDepositAmountInto(fsa,4);
       // username = scanner.next();
        // newCustomer=userInfoServices.callValidateUser(username);
        // App myApp = new App();
        do {
            System.out.println("Enter your username");
            username = scanner.next();
            try {
                flag =userInfoServices.callValidateUser(username);
            } catch (UserNotFoundException exp) {
                System.out.println(exp);
            }
        } while (flag == false);
        if (flag == true) {
            System.out.println("Enter your password");
            password = scanner.next();
            boolean check = userInfoServices.callPasswordValidate(username, password);
            int attempt = 1;
            int count = 3;
            if (!check) {
                for (attempt = 2; attempt <= 3; attempt++) {
                    System.out.println("");
                    count--;
                    System.out.println(ResourceBundle.getBundle("information").getString("user.password"));
                    System.out.println(ResourceBundle.getBundle("information").getString("user.passinfo") + " " + count);
                    password = scanner.next();
                    check = userInfoServices.callPasswordValidate(username, password);
                    if (check) {

                        attempt = 4;
                    }
                }
            }
                while (check) {
                    System.out.println("menu");
                  //  System.out.println(ResourceBundle.getBundle("information").getString("app.menu"));
                    int choice;
                    //System.out.println(ResourceBundle.getBundle("information").getString("app.choice"));
                    choice = scanner.nextInt();
                    switch (choice) {
                        case 1:
                            System.out.println("Enter the amount to be deposited");
                            Long amount = scanner.nextLong();
                            try {
                                userInfoServices.callDepositAmountInto(username, amount);
                            } catch (UserNotFoundException expection) {
                                System.out.println(expection);
                            }

                            catch (InputMismatchException expection){
                                System.out.println("You have entered the wrong input try again "+expection);
                                //   Scanner scanner = new Scanner(System.in);
                            }
                            break;

//                        case 4:bankServices.callCheck();
//                            break;
                        default: return;
                    }
                }

                //System.out.println(ResourceBundle.getBundle("information").getString("account.suspended"));
                // logger.log(Level.INFO,ResourceBundle.getBundle("information").getString("log.suspension"));
            }
        }
    }

