//package debit.cards;
//
//import debit.cards.dao.entities.DebitCard;
//import debit.cards.dao.exceptions.AccountException;
//import debit.cards.dao.exceptions.CustomerException;
//import debit.cards.dao.exceptions.DebitCardException;
//import debit.cards.dao.exceptions.DebitCardNullException;
//import debit.cards.dao.remotes.DebitCardRepository;
//import debit.cards.dao.services.DebitCardServices;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.dao.DataAccessException;
//import org.springframework.jdbc.core.CallableStatementCreator;
//import org.springframework.jdbc.core.JdbcTemplate;
//
//import java.sql.SQLException;
//import java.sql.SQLSyntaxErrorException;
//import java.util.*;
//import java.util.stream.Collectors;
//import java.util.stream.Stream;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.anyString;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//@SpringBootTest
//class DlteMyBankProjectDaoApplicationTests {
//    //This annotation is used to create a mock object of a class or interface. It creates a mock instance that you can then use to define behaviors, verify interactions, etc.
//    @Mock
//    private JdbcTemplate jdbcTemplate;
//    //This annotation is used to inject mock dependencies into the target object when its instantiated
//    @InjectMocks
//    public DebitCardServices debitCardServices;
//
//
//    @Test
//    void testAllDebitCards() throws SQLException {
//        // Mocking the response from the database
//        List<DebitCard> debitCardList = new ArrayList<>();
//
//
//        DebitCard debitCard = new DebitCard(1234567890981234L, 78903456789123L, 200005, 111, 1234, new Date(2024, 04, 4), "active", 2000.0, 50000.0);
//        DebitCard debitCard1 = new DebitCard(7837645907637746L, 35467956789123L, 123658, 234, 2323, new Date(2024, 04, 9), "inactive", 4000.0, 70000.0);
//        DebitCard debitCard2 = new DebitCard(1234567890123456L, 78901234567890L, 300007, 555, 9876, new Date(2024, 4, 14), "active", 3000.0, 60000.0);
//        DebitCard debitCard3 = new DebitCard(9876543210987654L, 65432109876543L, 400009, 777, 5432, new Date(2024, 4, 19), "blocked", 5000.0, 80000.0);
//        //Add some dummy data into the arrayList for testing
//        debitCardList = Stream.of(debitCard, debitCard1, debitCard2, debitCard3).collect(Collectors.toList());
//
//        //Fetching the data from database
//        when(jdbcTemplate.query(anyString(), any(DebitCardServices.DebitCardMapper.class))).thenReturn(debitCardList);
//
//        List<DebitCard> actualList = debitCardServices.getDebitCard();
//
//        assertTrue(debitCardList.size() == actualList.size());//success
//        assertEquals(debitCardList.get(0).getCustomerId(), actualList.get(0).getCustomerId());//success
//
//    }
//
////    @Test
////    void testAllDebitCardsFail() throws SQLException {
////        // Mocking the response from the database
////        List<DebitCard> debitCardList = new ArrayList<>();
////
////
////        DebitCard debitCard= new DebitCard(1234567890981234L,78903456789123L,200005,111,1234,new Date(2024,04,4), "active", 2000.0,50000.0);
////        DebitCard debitCard1 = new DebitCard(7837645907637746L,35467956789123L,123658,234,2323,new Date(2024,04,9), "inactive", 4000.0,70000.0);
////        DebitCard debitCard2 = new DebitCard(1234567890123456L, 78901234567890L, 300007, 555, 9876, new Date(2024, 4, 14), "active", 3000.0, 60000.0);
////        DebitCard debitCard3 = new DebitCard(9876543210987654L, 65432109876543L, 400009, 777, 5432, new Date(2024, 4, 19), "blocked", 5000.0, 80000.0);
////        //Add some dummy data into the arrayList for testing
////        debitCardList = Stream.of(debitCard,debitCard1,debitCard2,debitCard3).collect(Collectors.toList());
////
////        //Fetching the data from database
////        when(jdbcTemplate.query(anyString(),any(DebitCardServices.DebitCardMapper.class))).thenReturn(debitCardList);
////
////        List<DebitCard> actualList = debitCardServices.getDebitCard();
////
////        assertFalse(debitCardList.get(0).getCustomerId()==actualList.get(0).getCustomerId());
////        assertSame(debitCardList,actualList);
////    }
//
//    @Test
//    void testGetDebitCardEmptyList() {
//        // Mocking an empty response from the database
//        when(jdbcTemplate.query(anyString(), any(DebitCardServices.DebitCardMapper.class))).thenReturn(new ArrayList<>())
//                .thenThrow(new DebitCardNullException() {
//                });
//
//        //If an exception of the specified type DebitCardException is thrown, the assertThrows method will pass; otherwise, it will fail.
//        assertThrows(DebitCardNullException.class, () -> debitCardServices.getDebitCard());
//    }
//
//    @Test
//    void testGetDebitCard_SQLException() throws SQLException {
//        // Mocking the jdbcTemplate.query() method to throw a SQLException
//        when(jdbcTemplate.query(anyString(), any(DebitCardServices.DebitCardMapper.class)))
//                .thenThrow(new DebitCardException("SQL Syntax is Not proper try to resolve it"));
//
//        // Testing that DebitCardException is thrown when SQLException occurs
//        assertThrows(DebitCardException.class, () -> {
//            debitCardServices.getDebitCard();
//        });
//    }
//
//    @Test
//    public void testUpdateDebitLimit_Success() throws SQLException {
//        // Mocking the response from the database
//        List<DebitCard> debitCardList = new ArrayList<>();
//
//        DebitCard debitCard0 = new DebitCard(1234567890981234L, 78903456789123L, 200005, 111, 1234, new Date(2024, 04, 4), "active", 2000.0, 50000.0);
//        DebitCard debitCard1 = new DebitCard(7837645907637746L, 35467956789123L, 123658, 234, 2323, new Date(2024, 04, 9), "active", 4000.0, 70000.0);
//        DebitCard debitCard2 = new DebitCard(1234567890123456L, 78901234567890L, 300007, 555, 9876, new Date(2024, 4, 14), "active", 3000.0, 60000.0);
//        DebitCard debitCard3 = new DebitCard(9876543210987654L, 65432109876543L, 400009, 777, 5432, new Date(2024, 4, 19), "blocked", 5000.0, 80000.0);
//        //Add some dummy data into the arrayList for testing
//        debitCardList = Stream.of(debitCard0, debitCard1, debitCard2, debitCard3).collect(Collectors.toList());
//        // Mock returned execution map with success result
//        Map<String, Object> returnedExecution = new HashMap<>();
//        returnedExecution.put("status", "SQLCODE-000");
//
//        // Stubbing jdbcTemplate.call() method to return the mock execution map
//        when(jdbcTemplate.call(any(CallableStatementCreator.class), any())).thenReturn(returnedExecution);
//
//        // Creating a DebitCard object to update
//        DebitCard debitCardToUpdate = new DebitCard(1234567890981234L, 78903456789123L, 200005, 111, 1234, new Date(2024, 04, 4), "active", 200000.0, 5000000.0);
//
//        // Calling the method to be tested
//        String result = debitCardServices.updateDebitLimit(debitCard0);
//
//        // Asserting that the result is as expected
//        assertEquals("Debit card limit updated successfully", result);
//        // Adjust delta as per your requirements
//    }
//
//
//    @Test
//    public void testUpdateDebitLimit_Failure() throws SQLException {
//        // Mocking the response from the database
//        List<DebitCard> debitCardList = new ArrayList<>();
//
//        DebitCard debitCard0 = new DebitCard(1234567890981234L, 78903456789123L, 200005, 111, 1234, new Date(2024, 04, 4), "active", 2000.0, 50000.0);
//        DebitCard debitCard1 = new DebitCard(7837645907637746L, 35467956789123L, 123658, 234, 2323, new Date(2024, 04, 9), "inactive", 4000.0, 70000.0);
//        DebitCard debitCard2 = new DebitCard(1234567890123456L, 78901234567890L, 300007, 555, 9876, new Date(2024, 4, 14), "active", 3000.0, 60000.0);
//        DebitCard debitCard3 = new DebitCard(9876543210987654L, 65432109876543L, 400009, 777, 5432, new Date(2024, 4, 19), "blocked", 5000.0, 80000.0);
//        //Add some dummy data into the arrayList for testing
//        debitCardList = Stream.of(debitCard0, debitCard1, debitCard2, debitCard3).collect(Collectors.toList());
//        // Mock the returned execution from the database
//        DebitCard debitCard = new DebitCard(1234567890981234L, 78903456789123L, 200005, 111, 1234, new Date(2024, 04, 4), "active", 200000.0, 5000000.0);
//        Map<String, Object> returnedExecution = mock(Map.class);
//        when(returnedExecution.get("p_status")).thenReturn("SQLCODE-000");
//        when(jdbcTemplate.call(any(CallableStatementCreator.class), anyList())).thenReturn(returnedExecution);
//        String result = debitCardServices.updateDebitLimit(debitCard);
//        assertEquals("Debit card limit update Failure", result);
//    }
//
//    @Test()
//    public void testUpdateDebitLimit_CustomerNotFound() throws SQLException {
//        // Mock the returned execution from the database
//        Map<String, Object> returnedExecution = mock(Map.class);
//        when(returnedExecution.get("p_status")).thenReturn("SQLCODE-001");
//        when(jdbcTemplate.call(any(CallableStatementCreator.class), anyList())).thenReturn(returnedExecution);
//
//        DebitCard debitCard = new DebitCard(/* add necessary parameters */);
//        debitCardServices.updateDebitLimit(debitCard);
//        // Ensure that CustomerException is thrown
//    }
//}
//
//
