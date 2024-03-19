package client.services;

import web.Customer;
import web.SoapAccount;
import web.SoapAccountService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    static Scanner scanner = new Scanner(System.in);
    public static void main( String[] args )
    {
        SoapAccountService soapAccountService = new SoapAccountService();
        SoapAccount soapAccount = soapAccountService.getSoapAccountPort();
        System.out.println("--Welcome to My Bank--");
        System.out.println("1->Create a user\n2->Find By UserName\n3->TransactionUpdate");
        System.out.println("Enter your Choice");
        int choice = new Scanner(System.in).nextInt();
        switch (choice){
            case 1:String message = soapAccount.findAll();
                System.out.println(message);
                break;
            case 2: String user = "prash02";
                List<Customer> cards = soapAccount.findUser(user).getCustomerList();
                if(cards.get(0).getUsername()!=null){
                    for(Customer each:cards){
                        System.out.println("Username: "+each.getUsername()+" Password: "+each.getPassword()+" Address: "+ each.getAddress()+" Email ID: "+each.getEmail()+" Contact: "+each.getContact()+" Initial Balance: "+each.getInitialBalace());
                    }
                }else{
                    System.out.println("User Not Found");
                }

                break;
            case 3:
                String Username = "prash02";
                String Date = "13-03-2024";
                String message1 = soapAccount.findByDateUserName(Username,Date);
                if(message1!=null){
                    System.out.println(message1);
                }else{
                    System.out.println("user Not found");
                }

                break;

            default:
                System.out.println("Invalid Choice");
                return;
        }

    }
}
