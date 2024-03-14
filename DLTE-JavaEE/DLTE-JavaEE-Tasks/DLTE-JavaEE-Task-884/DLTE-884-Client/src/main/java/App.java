import web.AccountSoap;
import web.AccountSoapService;
import web.Customer;

public class App {
    public static void main(String[] args) {
        AccountSoapService service=new AccountSoapService();
        AccountSoap accountSoap = service.getAccountSoapPort();
        Customer customer = accountSoap.getUsername("prash02");
        System.out.println("Username: "+customer.getUsername()+" Balance: "+ customer.getInitialBalace());

    }
}
