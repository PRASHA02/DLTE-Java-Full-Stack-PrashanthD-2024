package element.spring.web.demo;

import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;
import element.spring.web.demo.configs.TransactionPhase;
import element.spring.web.demo.dao.TransactionServices;
import element.spring.web.demo.dao.Transactions;
import links.transactions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.XMLGregorianCalendar;
import java.sql.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class DlteSpringSoapApplicationTests {
    @MockBean
    private TransactionServices transactionServices;
    @InjectMocks
    TransactionPhase transactionPhase;

    @Test
    void CallInsert(){
        Transactions transactionsModel1 = new Transactions(111L,new Date(2024,03,28),"prashanth","vignesh",1000000L,"education");
        Transactions transactionsModel2 = new Transactions(895775L,new Date(2024,12,2),"vineeth","prashanth",50000L,"family");
        Transactions transactionsModel3 = new Transactions(7952345L,new Date(2026,11,21),"elroy","jeevan",50000L,"friend");

        lenient().when(transactionServices.newTransaction(any())).thenReturn(transactionsModel1);

        NewTransactionRequest newTransactionRequest = new NewTransactionRequest();
        links.transactions.Transactions transactions = new links.transactions.Transactions();

        transactions.setTransactionId(111L);
        transactions.setTransactionBy("prashanth");
        transactions.setTransactionDate(XMLGregorianCalendarImpl.createDate(2024, 3, 28, 0));
        transactions.setTransactionTo("vignesh");
        transactions.setTransactionAmount(1000000L);
        transactions.setTransactionFor("education");

        newTransactionRequest.setTransactions(transactions);
        // Execute the method under test
        NewTransactionResponse response = transactionPhase.addNewTransaction(newTransactionRequest);

        // Assert the response
        assertEquals("SUCCESS", response.getServiceStatus().getStatus());//success
        assertSame(transactionsModel1.getTransactionId(), response.getTransactions().getTransactionId());//success
        //assertSame(transactionsModel2,response.getTransactions());//failure because two objects are different
    }

    @Test
    public void testFilterByAmountResponse() {
        // Mock data
        FilterByAmountRequest filterByAmountRequest = new FilterByAmountRequest();
        filterByAmountRequest.setAmount(1000000L);

        Transactions transactionsModel1 = new Transactions(111L, new Date(2024, 03, 28), "prashanth", "vignesh", 1000000L, "education");
        Transactions transactionsModel2 = new Transactions(895775L,new Date(2024,12,2),"vineeth","prashanth",1000000L,"family");
        Transactions transactionsModel3 = new Transactions(7952345L,new Date(2026,11,21),"elroy","jeevan",50000L,"friend");

        List<Transactions> transactionsList = Stream.of(transactionsModel1,transactionsModel2,transactionsModel3).collect(Collectors.toList());
        lenient().when(transactionServices.findByAmount(anyLong())).thenReturn(transactionsList);

        // Execute the method under test
        FilterByAmountResponse response = transactionPhase.filterByAmountResponse(filterByAmountRequest);

        // Assert the response
        assertEquals("SUCCESS", response.getServiceStatus().getStatus());
        assertEquals("Transactions were fetched", response.getServiceStatus().getMessage());
        //assertEquals(1, response.getTransactions().size());//false expected 1 actual 3
    }

    @Test
    public void testFilterBySender() {
        // Mock data
        FilterBySenderRequest filterBySenderRequest = new  FilterBySenderRequest();
        filterBySenderRequest.setSender("prashanth");

        Transactions transactionsModel1 = new Transactions(111L, new Date(2024, 03, 28), "prashanth", "vignesh", 1000000L, "education");
        Transactions transactionsModel2 = new Transactions(895775L,new Date(2024,12,2),"vineeth","prashanth",1000000L,"family");
        Transactions transactionsModel3 = new Transactions(7952345L,new Date(2026,11,21),"elroy","jeevan",50000L,"friend");

        List<Transactions> transactionsList = Stream.of(transactionsModel1,transactionsModel2,transactionsModel3).collect(Collectors.toList());
        lenient().when(transactionServices.findBySender(anyString())).thenReturn(transactionsList);

        // Execute the method under test
        FilterBySenderResponse response = transactionPhase.filterBySenderResponse(filterBySenderRequest);

        // Assert the response
        assertEquals("SUCCESS", response.getServiceStatus().getStatus());
        assertEquals("Transactions were fetched", response.getServiceStatus().getMessage());
       // assertNotEquals(3, response.getTransactions().size());//false expected 3 and  actual 3 but nOt equals
       assertTrue(transactionsList.get(0).getTransactionId()==response.getTransactions().get(0).getTransactionId());//success
    }

    @Test
    public void testFilterByReceiver() {
        // Mock data
        FilterByReceiverRequest filterByReceiverRequest = new FilterByReceiverRequest();
        filterByReceiverRequest.setReceiver("vignesh");

        Transactions transactionsModel1 = new Transactions(111L, new Date(2024, 03, 28), "prashanth", "vignesh", 1000000L, "education");
        Transactions transactionsModel2 = new Transactions(895775L, new Date(2024, 12, 2), "vineeth", "prashanth", 1000000L, "family");
        Transactions transactionsModel3 = new Transactions(7952345L, new Date(2026, 11, 21), "elroy", "jeevan", 50000L, "friend");

        List<Transactions> transactionsList = Stream.of(transactionsModel1, transactionsModel2, transactionsModel3).collect(Collectors.toList());
        lenient().when(transactionServices.findByReceiver(anyString())).thenReturn(transactionsList);

        // Execute the method under test
        FilterByReceiverResponse response = transactionPhase.filterByReceiverResponse(filterByReceiverRequest);

        // Assert the response
        assertEquals("SUCCESS", response.getServiceStatus().getStatus());
        assertEquals("Transactions were fetched", response.getServiceStatus().getMessage());
        //assertNull(response);//false
        assertTrue(transactionsList.get(0).getTransactionId() == response.getTransactions().get(0).getTransactionId());//success
    }

    @Test
    public void testUpdateRemarksTransaction() {
        // Mock data
        UpdateRemarksTransactionRequest updateRemarksTransactionRequest = new UpdateRemarksTransactionRequest();
        links.transactions.Transactions transactions = new links.transactions.Transactions();
        transactions.setTransactionId(111L);
        transactions.setTransactionId(111L);
        transactions.setTransactionBy("prashanth");
        transactions.setTransactionDate(XMLGregorianCalendarImpl.createDate(2024, 3, 28, 0));
        transactions.setTransactionTo("vignesh");
        transactions.setTransactionAmount(1000000L);
        transactions.setTransactionFor("family");
        updateRemarksTransactionRequest.setTransactions(transactions);

        Transactions transactionsModel1 = new Transactions(111L, new Date(2024, 03, 28), "prashanth", "vignesh", 1000000L, "education");
        lenient().when(transactionServices.findByRemarks(any())).thenReturn(transactionsModel1);

        // Execute the method under test
        UpdateRemarksTransactionResponse response = transactionPhase.updateRemarksTransactionResponse(updateRemarksTransactionRequest);

        // Assert the response
        assertEquals("SUCCESS", response.getServiceStatus().getStatus());//success
        assertTrue(transactionsModel1.getTransactionId()==response.getTransactions().getTransactionId());//success
        //assertSame(transactionsModel1, response);//false not same object
    }


    @Test
    public void testRemoveByDate() throws DatatypeConfigurationException {

        Transactions transactionsModel1 = new Transactions(111L, new Date(2024, 03, 28), "prashanth", "vignesh", 1000000L, "education");
        Transactions transactionsModel2 = new Transactions(895775L, new Date(2024, 03, 21), "vineeth", "prashanth", 1000000L, "family");
        Transactions transactionsModel3 = new Transactions(7952345L, new Date(2024, 03, 23), "elroy", "jeevan", 50000L, "friend");

        List<Transactions> transactionsList = Stream.of(transactionsModel1, transactionsModel2, transactionsModel3).collect(Collectors.toList());
        // Mock data
        RemoveByDateRequest removeByDateRequest = new RemoveByDateRequest();
        // Set start and end dates as per your requirement'

        removeByDateRequest.setStart(XMLGregorianCalendarImpl.createDate(2024, 3, 21, 0));
        removeByDateRequest.setEnd(XMLGregorianCalendarImpl.createDate(2024, 3, 25, 0));

        // Mock the behavior of the transactionServices.removeByDate() method
        lenient().when(transactionServices.removeByDate(any(java.util.Date.class), any(java.util.Date.class))).thenReturn("removed");

        // Execute the method under test
        RemoveByDateResponse response = transactionPhase.removeByDateResponse(removeByDateRequest);

        // Assert the response
        assertEquals("removed the transactions", response.getServiceStatus().getStatus());//success
        assertEquals("removed", response.getServiceStatus().getMessage());//success
        //assertTrue(transactionsModel1.getTransactionDate()==removeByDateRequest.getStart().toGregorianCalendar().getGregorianChange());//false because expected is true but actual is false
    }



}
