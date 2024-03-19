package servlets;

import application.db.Entities.Customer;
import application.db.Services.UserInfoServices;
import basics.FindAll;
import basics.FindAllUserName;
import basics.FindByDateUserName;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.internal.matchers.Equals;
import org.mockito.junit.MockitoJUnitRunner;
import org.slf4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class AppTest {
    @Mock
    private UserInfoServices services;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private StringWriter stringWriter;
    @Mock
    private PrintWriter printWriter;
    @Mock
    private Logger logger;
    @Mock
    private ResourceBundle resourceBundle;

    @Before
    public void initiate() throws IOException {
        stringWriter = new StringWriter();
        printWriter = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(printWriter);
    }

    @Test
    public void findAllTest() throws ServletException, IOException {
        FindAll findAll  = new FindAll();
        findAll.userInfoServices = services;
        StringBuilder builder = new StringBuilder("Deposit,0,");

        builder.append(new SimpleDateFormat("dd-MM-yyyy").format(new Date()));
        ArrayList<StringBuilder> transactionOne = new ArrayList<>();
        transactionOne.add(builder);

        Customer customer1 = new Customer("prasha02","prash321","karkala","prash@gmail.com",789267177L,1000000L,transactionOne);
        Customer customer2=new Customer("rakesh", "rak123", "Mangalore", "rak@gmail", 987455335L, 1000L,transactionOne);
        Customer customer3=new Customer("prash02", "prash321", "Mangalore", "rak@gmail", 987455335L, 1000L,transactionOne);
        List<Customer> customerList = Stream.of(customer1,customer2,customer3).collect(Collectors.toList());
        when(services.callFindAll()).thenReturn(customerList);
        findAll.doGet(request,response);
        verify(response).setContentType("application/json");
        verify(services).callFindAll();
        assertEquals("expected: ","[{\"username\":\"prasha02\",\"password\":\"prash321\",\"address\":\"karkala\",\"email\":\"prash@gmail.com\",\"contact\":789267177,\"initialBalace\":1000000,\"transactionDetails\":[\"Deposit,0,18-03-2024\"]},{\"username\":\"rakesh\",\"password\":\"rak123\",\"address\":\"Mangalore\",\"email\":\"rak@gmail\",\"contact\":987455335,\"initialBalace\":1000,\"transactionDetails\":[\"Deposit,0,18-03-2024\"]},{\"username\":\"prash02\",\"password\":\"prash321\",\"address\":\"Mangalore\",\"email\":\"rak@gmail\",\"contact\":987455335,\"initialBalace\":1000,\"transactionDetails\":[\"Deposit,0,18-03-2024\"]}]",stringWriter.toString().trim());

    }
    @Test
    public void findUsername() throws ServletException, IOException {
        FindAllUserName findAllUserName = new FindAllUserName();
        findAllUserName.userInfoServices = services;
        StringBuilder builder = new StringBuilder("Deposit,0");
        builder.append("," + new SimpleDateFormat("dd-MM-yyyy").format(new Date()));
        ArrayList<StringBuilder> transactionOne = new ArrayList<>();
        transactionOne.add(builder);
        Customer customer1 = new Customer("prasha02","prash321","karkala","prash@gmail.com",789267177L,1000000L,transactionOne);
        Customer customer2=new Customer("rakesh", "rak123", "Mangalore", "rak@gmail", 987455335L, 1000L,transactionOne);
        Customer customer3=new Customer("prash02", "prash321", "Mangalore", "rak@gmail", 987455335L, 1000L,transactionOne);


        when(request.getParameter("username")).thenReturn("prasha02");

        when(services.callFindusername(anyString())).thenReturn(customer1);


        findAllUserName.doGet(request,response);

        verify(response).setContentType("application/json");

        verify(services).callOneUserTransact(anyString());
        assertNotEquals("expected: ","[{\"username\":\"prasha02\",\"password\":\"prash321\",\"address\":\"karkala\",\"email\":\"prash@gmail.com\",\"contact\":789267177,\"initialBalace\":1000000,\"transactionDetails\":[\"Deposit\",0,\"18-03-2024\"]},{\"username\":\"rakesh\",\"password\":\"rak123\",\"address\":\"Mangalore\",\"email\":\"rak@gmail\",\"contact\":987455335,\"initialBalace\":1000,\"transactionDetails\":[\"Deposit\",0,\"18-03-2024\"]},{\"username\":\"prash02\",\"password\":\"prash321\",\"address\":\"Mangalore\",\"email\":\"rak@gmail\",\"contact\":987455335,\"initialBalace\":1000,\"transactionDetails\":[\"Deposit\",0,\"18-03-2024\"]}]",stringWriter.toString().trim());

    }

    @Test
    public void findByDateUserName() throws ServletException, IOException {
       FindByDateUserName findByDateUserName = new FindByDateUserName();
        findByDateUserName.userInfoServices = services;
        StringBuilder builder = new StringBuilder("Deposit,0");
        builder.append("," + new SimpleDateFormat("dd-MM-yyyy").format(new Date()));
        ArrayList<StringBuilder> transactionOne = new ArrayList<>();
        transactionOne.add(builder);
        Customer customer1 = new Customer("prasha02","prash321","karkala","prash@gmail.com",789267177L,1000000L,transactionOne);
        Customer customer2=new Customer("rakesh", "rak123", "Mangalore", "rak@gmail", 987455335L, 1000L,transactionOne);
        String username = "prasha02";
        String date = "18-03-2024";

        when(request.getParameter("username")).thenReturn(username);
        when(request.getParameter("date")).thenReturn(date);

        when(services.callTransactionByDate(username, date)).thenReturn(Collections.singletonList(username));
        findByDateUserName.doGet(request,response);
        verify(response).setContentType("application/json");
        verify(services).callTransactionByDate(anyString(),anyString());
        assertNotEquals("expected: ","[{\"username\":\"prasha02\",\"password\":\"prash321\",\"address\":\"karkala\",\"email\":\"prash@gmail.com\",\"contact\":789267177,\"initialBalace\":1000000,\"transactionDetails\":[\"Deposit\",0,\"18-03-2024\"]},{\"username\":\"rakesh\",\"password\":\"rak123\",\"address\":\"Mangalore\",\"email\":\"rak@gmail\",\"contact\":987455335,\"initialBalace\":1000,\"transactionDetails\":[\"Deposit\",0,\"18-03-2024\"]},{\"username\":\"prash02\",\"password\":\"prash321\",\"address\":\"Mangalore\",\"email\":\"rak@gmail\",\"contact\":987455335,\"initialBalace\":1000,\"transactionDetails\":[\"Deposit\",0,\"18-03-2024\"]}]",stringWriter.toString().trim());

    }







}
