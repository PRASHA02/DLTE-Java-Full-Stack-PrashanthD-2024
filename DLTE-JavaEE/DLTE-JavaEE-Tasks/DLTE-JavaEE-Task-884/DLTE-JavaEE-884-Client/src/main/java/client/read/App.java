package client.read;

import web.AccountSoap;
import web.AccountSoapService;
import web.Customer;
import web.GroupAccount;

import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        AccountSoapService service=new AccountSoapService();
        AccountSoap accountSoap = service.getAccountSoapPort();
        List<Customer> cards = accountSoap.findAllUsername("prash02").getCustomerList();
        for(Customer each:cards){
            System.out.println(each.getUsername()+" "+each.getPassword()+" "+each.getInitialBalace());
        }


    }
}
