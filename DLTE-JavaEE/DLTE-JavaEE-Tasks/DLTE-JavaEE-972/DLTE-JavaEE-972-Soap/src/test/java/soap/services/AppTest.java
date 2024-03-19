package soap.services;


import application.db.Entities.Customer;
import application.db.Services.UserInfoServices;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import web.GroupAccount;
import web.SoapAccount;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AppTest {

    @Mock
    private UserInfoServices userInfoServices;

    private SoapAccount soapAccount;

    @Before
    public void setUp() throws Exception {
        soapAccount = new SoapAccount();
        soapAccount.userInfoServices = userInfoServices;
    }

    @Test
    public void testFindAll() {
        List<Customer> customers = generateCustomerList();
        when(userInfoServices.callFindAll()).thenReturn(customers);

        String result = soapAccount.FindAll();

        verify(userInfoServices).callFindAll();
        assertEquals("Expected JSON message", generateJson(customers), result);
    }

    @Test
    public void testFindUser() {
        String username = "prasha02";
        Customer customer = new Customer("prasha02", "prash321", "karkala", "prash@gmail.com", 789267177L, 1000000L, new ArrayList<>());
        when(userInfoServices.callFindusername(username)).thenReturn(customer);

        GroupAccount result = soapAccount.findUser(username);

        verify(userInfoServices).callFindusername(username);
        assertEquals("Expected single customer in GroupAccount", 1, result.getCustomerList().size());
        assertEquals("Expected customer in GroupAccount", customer, result.getCustomerList().get(0));
    }

    @Test
    public void testFindByDateUserName() {
        String username = "prasha02";
        String date = "18-03-2024";
        List<Customer> transactions = generateCustomerList();
        when(userInfoServices.callTransactionByDate(username, date)).thenReturn(transactions);

        String result = soapAccount.findByDateUserName(username, date);

        verify(userInfoServices).callTransactionByDate(username, date);
        assertEquals("Expected JSON message", generateJson(transactions), result);
    }

    private List<Customer> generateCustomerList() {
        List<Customer> customers = new ArrayList<>();
        List<StringBuilder> transactionOne = new ArrayList<>();
        transactionOne.add(new StringBuilder("Deposit,0,18-03-2024"));
        customers.add(new Customer("prasha02", "prash321", "karkala", "prash@gmail.com", 789267177L, 1000000L));
        customers.add(new Customer("rakesh", "rak123", "Mangalore", "rak@gmail", 987455335L, 1000L));
        customers.add(new Customer("prash02", "prash321", "Mangalore", "rak@gmail", 987455335L, 1000L));
        return customers;
    }

    private String generateJson(List<Customer> customers) {
        StringBuilder json = new StringBuilder("[");
        for (Customer customer : customers) {
            json.append(customerToJson(customer)).append(",");
        }
        json.deleteCharAt(json.length() - 1); 
        json.append("]");
        return json.toString();
    }

    private String customerToJson(Customer customer) {
        return String.format("{\"username\":\"%s\",\"password\":\"%s\",\"address\":\"%s\",\"email\":\"%s\",\"contact\":%d,\"initialBalace\":%d}",
                customer.getUsername(), customer.getPassword(), customer.getAddress(), customer.getEmail(), customer.getContact(), customer.getInitialBalace());
    }
}
