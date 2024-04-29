//package debit.cards;
//
//import debit.cards.dao.entities.Account;
//import debit.cards.dao.entities.Customer;
//import debit.cards.dao.entities.DebitCard;
//import debit.cards.dao.exceptions.*;
//import debit.cards.dao.services.DebitCardServices;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.dao.DataAccessException;
//import org.springframework.jdbc.core.CallableStatementCreator;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContext;
//import org.springframework.security.core.context.SecurityContextHolder;
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
//        // Mock authentication
//        Authentication authentication = mock(Authentication.class);
//        when(authentication.getName()).thenReturn("testUser"); // Set the authenticated username
//
//        // Mock SecurityContext
//        SecurityContext securityContext = mock(SecurityContext.class);
//        when(securityContext.getAuthentication()).thenReturn(authentication);
//        SecurityContextHolder.setContext(securityContext);
//
//
//        Customer customer = new Customer();
//
//        // Set values using setter methods
//        customer.setCustomerId(200005);
//        customer.setCustomerName("Prashanth");
//        customer.setCustomerAddress("karkala");
//        customer.setCustomerStatus("active");
//        customer.setCustomerContact(1234567890L);
//        customer.setUsername("prasha02");
//        customer.setPassword("prash");
//
//        Account account = new Account();
//        account.setAccountId(1001);
//        account.setCustomerId(200005);
//        account.setAccountType("Savings");
//        account.setAccountNumber(78903456789123L);
//        account.setAccountStatus("active");
//        account.setAccountBalance(50000.0);
//
//
//        // Mocking the response from the database
//        List<DebitCard> debitCardList = new ArrayList<>();
//
//        DebitCard debitCard = new DebitCard(1234567890981234L, 78903456789123L, 200005, 111, 1234, new Date(2024, 04, 4), "active", 2000.0, 50000.0);
//        DebitCard debitCard1 = new DebitCard(7837645907637746L, 35467956789123L, 123658, 234, 2323, new Date(2024, 04, 9), "inactive", 4000.0, 70000.0);
//        DebitCard debitCard2 = new DebitCard(1234567890123456L, 78901234567890L, 300007, 555, 9876, new Date(2024, 4, 14), "active", 3000.0, 60000.0);
//        DebitCard debitCard3 = new DebitCard(9876543210987654L, 65432109876543L, 400009, 777, 5432, new Date(2024, 4, 19), "blocked", 5000.0, 80000.0);
//        //Add some dummy data into the arrayList for testing
//        debitCardList = Stream.of(debitCard, debitCard1, debitCard2, debitCard3).collect(Collectors.toList());
//
//        //Fetching the data from database
//      when(jdbcTemplate.query(eq("SELECT * FROM mybank_app_debitcard d JOIN mybank_app_customer c ON d.customer_id = c.customer_id JOIN mybank_app_account a on a.account_number=d.account_number WHERE NOT debitcard_status = 'Blocked' AND  a.account_status='active'  AND c.customer_status='active' AND c.username = ?"),  any(Object[].class), any(DebitCardServices.DebitCardMapper.class))).
//                thenReturn(debitCardList);
//
//        List<DebitCard> actualList = debitCardServices.getDebitCard("prasha");
//
//        assertTrue(debitCardList.size() == actualList.size());//success
//        assertEquals(debitCardList.get(0).getCustomerId(), actualList.get(0).getCustomerId());//success
//    }
//
//    @Test
//    void testAllDebitCardsFail() throws SQLException {
//        // Mocking the response from the database
//        List<DebitCard> debitCardList = new ArrayList<>();
//
//
//        DebitCard debitCard= new DebitCard(1234567890981234L,78903456789123L,200005,111,1234,new Date(2024,04,4), "active", 2000.0,50000.0);
//        DebitCard debitCard1 = new DebitCard(7837645907637746L,35467956789123L,123658,234,2323,new Date(2024,04,9), "inactive", 4000.0,70000.0);
//        DebitCard debitCard2 = new DebitCard(1234567890123456L, 78901234567890L, 300007, 555, 9876, new Date(2024, 4, 14), "active", 3000.0, 60000.0);
//        DebitCard debitCard3 = new DebitCard(9876543210987654L, 65432109876543L, 400009, 777, 5432, new Date(2024, 4, 19), "blocked", 5000.0, 80000.0);
//        //Add some dummy data into the arrayList for testing
//        debitCardList = Stream.of(debitCard,debitCard1,debitCard2,debitCard3).collect(Collectors.toList());
//
//        //Fetching the data from database
//       // when(jdbcTemplate.query(anyString(),any(DebitCardServices.DebitCardMapper.class))).thenReturn(debitCardList);
//
//        when(jdbcTemplate.query(anyString(), any(Object[].class), any(DebitCardServices.DebitCardMapper.class)))
//                .thenReturn(debitCardList);
//        List<DebitCard> actualList = debitCardServices.getDebitCard("prasha02");
//
//        assertFalse(debitCardList.get(0).getCustomerId()==actualList.get(0).getCustomerId());
//        assertSame(debitCardList,actualList);
//    }
//
//    @Test
//    void testGetDebitCardEmptyList() {
//        // Mocking an empty response from the database
//        when(jdbcTemplate.query(anyString(), any(DebitCardServices.DebitCardMapper.class))).thenReturn(new ArrayList<>())
//                .thenThrow(new DebitCardNullException() {
//                });
//
//        //If an exception of the specified type DebitCardException is thrown, the assertThrows method will pass; otherwise, it will fail.
//        assertThrows(DebitCardNullException.class, () -> debitCardServices.getDebitCard("prasha02"));
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
//            debitCardServices.getDebitCard("prasha02");
//        });
//    }
//
//   //Rest testing for updating the limits of the debit card
//    @Test
//    public void testUpdateDebitLimit_Success() throws SQLException {
//        //This object is used for updating the limits
//        DebitCard debitCard = new DebitCard();
//        debitCard.setDebitCardNumber(1234567890981234L);
//        debitCard.setAccountNumber(78903456789123L);
//        debitCard.setCustomerId(200005);
//        debitCard.setDebitCardCvv(111);
//        debitCard.setDebitCardPin(1234);
//        Calendar calendar = Calendar.getInstance();
//        calendar.set(2024, Calendar.APRIL, 4);
//        Date expiryDate = calendar.getTime();
//        System.out.println(expiryDate);
//        debitCard.setDebitCardExpiry(expiryDate);
//        debitCard.setDebitCardStatus("active");
//        debitCard.setDomesticLimit(2000.0);
//        debitCard.setInternationalLimit(5000.0);
//
//        // Mock returned DebitCard fetched from the database
//        DebitCard debitCardFetched = new DebitCard();
//        debitCardFetched.setDebitCardNumber(1234567890981234L);
//        debitCardFetched.setAccountNumber(78903456789123L);
//        debitCardFetched.setCustomerId(200005);
//        debitCardFetched.setDebitCardCvv(111);
//        debitCardFetched.setDebitCardPin(1234);
//        calendar.set(2024, Calendar.APRIL, 4);
//        Date expiryUpdateDate = calendar.getTime();
//        debitCardFetched.setDebitCardExpiry(expiryUpdateDate);
//        debitCardFetched.setDebitCardStatus("active");
//        debitCardFetched.setDomesticLimit(200000.0);
//        debitCardFetched.setInternationalLimit(5000000.0);
//
//
//        // Stub the jdbcTemplate.queryForObject() method to return the fetchedAccount
//        when(jdbcTemplate.queryForObject(
//                anyString(),
//                any(Object[].class),
//                any(DebitCardServices.DebitCardMapper.class)))
//                .thenReturn(debitCardFetched);
//
//        // Mock the returned execution map with success result
//        Map<String, Object> returnedExecution = new HashMap<>();
//        returnedExecution.put("status", "SQLCODE-000");
//
//        // Stub the jdbcTemplate.call() method to return the mock execution map
//        when(jdbcTemplate.call(any(CallableStatementCreator.class), anyList())).thenReturn(returnedExecution);
//
//        // Calling the method to be tested
//        String result = debitCardServices.updateDebitLimit(debitCard);
//        // Asserting that the result is as expected
//        assertEquals("Debit card limit updated successfully", result);
//        verify(jdbcTemplate).call(any(CallableStatementCreator.class), anyList());
//    }
//
//    @Test
//    public void testUpdateDebit_Failure() throws SQLException {
//        // Mocking the response from the database
//        //This object is used for updating the limits
//        DebitCard debitCard = new DebitCard();
//        debitCard.setDebitCardNumber(1234567890981234L);
//        debitCard.setAccountNumber(78903456789L);
//        debitCard.setCustomerId(200005);
//        debitCard.setDebitCardCvv(111);
//        debitCard.setDebitCardPin(1234);
//        Calendar calendar = Calendar.getInstance();
//        calendar.set(2024, Calendar.APRIL, 4);
//        Date expiryDate = calendar.getTime();
//        debitCard.setDebitCardExpiry(expiryDate);
//        debitCard.setDebitCardStatus("active");
//        debitCard.setDomesticLimit(2000.0);
//        debitCard.setInternationalLimit(5000.0);
//
//        // Mock returned DebitCard fetched from the database
//        DebitCard debitCardFetched = new DebitCard();
//        debitCardFetched.setDebitCardNumber(1234567890981234L);
//        debitCardFetched.setAccountNumber(78903456789123L);
//        debitCardFetched.setCustomerId(200005);
//        debitCardFetched.setDebitCardCvv(111);
//        debitCardFetched.setDebitCardPin(1234);
//        calendar.set(2024, Calendar.APRIL, 4);
//        Date expiryUpdateDate = calendar.getTime();
//        debitCardFetched.setDebitCardExpiry(expiryUpdateDate);
//        debitCardFetched.setDebitCardStatus("active");
//        debitCardFetched.setDomesticLimit(200000.0);
//        debitCardFetched.setInternationalLimit(5000000.0);
//
//
//        // Stub the jdbcTemplate.queryForObject() method to return the fetchedAccount
//        if (debitCard.getDebitCardStatus() != debitCardFetched.getDebitCardStatus()) {
//            assertThrows(DebitCardException.class, () -> debitCardServices.updateDebitLimit(debitCard));
//        }
//        when(jdbcTemplate.queryForObject(
//                anyString(),
//                any(Object[].class),
//                any(DebitCardServices.DebitCardMapper.class)))
//                .thenReturn(debitCardFetched);
//
//        // Mock the returned execution map with success result
//        Map<String, Object> returnedExecution = new HashMap<>();
//        returnedExecution.put("status", "SQLCODE-000");
//
//        // Stub the jdbcTemplate.call() method to return the mock execution map
//        when(jdbcTemplate.call(any(CallableStatementCreator.class), anyList())).thenReturn(returnedExecution);
//
//        // Calling the method to be tested
//        String result = debitCardServices.updateDebitLimit(debitCard);
//
//        // Asserting that the result is as expected
//        assertEquals(" Debit card limit update failed", result);
//        verify(jdbcTemplate).call(any(CallableStatementCreator.class), anyList());
//    }
//    //exception
//    @Test
//    public void testUpdateDebitLimit_Failure() throws SQLException {
//
//        //This object is used for updating the limits
//        DebitCard debitCard = new DebitCard();
//        debitCard.setDebitCardNumber(1234567890981234L);
//        debitCard.setAccountNumber(78903456789123L);
//        debitCard.setCustomerId(200005);
//        debitCard.setDebitCardCvv(111);
//        debitCard.setDebitCardPin(1234);
//        Calendar calendar = Calendar.getInstance();
//        calendar.set(2024, Calendar.APRIL, 4);
//        Date expiryDate = calendar.getTime();
//        debitCard.setDebitCardExpiry(expiryDate);
//        debitCard.setDebitCardStatus("inactive");
//        debitCard.setDomesticLimit(2000.0);
//        debitCard.setInternationalLimit(5000.0);
//
//        // Mock returned DebitCard fetched from the database
//        DebitCard debitCardFetched = new DebitCard();
//        debitCardFetched.setDebitCardNumber(1234567890981234L);
//        debitCardFetched.setAccountNumber(78909876543530L);
//        debitCardFetched.setCustomerId(200005);
//        debitCardFetched.setDebitCardCvv(111);
//        debitCardFetched.setDebitCardPin(1234);
//        calendar.set(2024, Calendar.APRIL, 4);
//        Date expiryUpdateDate = calendar.getTime();
//        debitCardFetched.setDebitCardExpiry(expiryUpdateDate);
//        debitCardFetched.setDebitCardStatus("active");
//        debitCardFetched.setDomesticLimit(200000.0);
//        debitCardFetched.setInternationalLimit(5000000.0);
//
//        if (debitCard.getDebitCardStatus() != debitCardFetched.getDebitCardStatus()) {
//            assertThrows(DebitCardException.class, () -> debitCardServices.updateDebitLimit(debitCard));
//        }
//
//
//        // Stub the jdbcTemplate.queryForObject() method to return the fetchedAccount
//        when(jdbcTemplate.queryForObject(
//                anyString(),
//                any(Object[].class),
//                any(DebitCardServices.DebitCardMapper.class)))
//                .thenReturn(debitCardFetched);
//
//        // Mock the returned execution map with success result
//        Map<String, Object> returnedExecution = new HashMap<>();
//        returnedExecution.put("status", "SQLCODE-003");
//
//        // Stub the jdbcTemplate.call() method to return the mock execution map
//        when(jdbcTemplate.call(any(CallableStatementCreator.class), anyList())).thenReturn(returnedExecution);
//
//        // Calling the method to be tested
//        try {
//            String result = debitCardServices.updateDebitLimit(debitCard);
//        } catch (DebitCardException e) {
//            // If an exception is thrown, assert the failure message
//            assertThrows(DebitCardException.class, () -> debitCardServices.updateDebitLimit(debitCard));
//            // Exit the test early since the expected exception was thrown
//            return;
//        }
//        verify(jdbcTemplate).call(any(CallableStatementCreator.class), anyList());
//    }
//
//    @Test
//    public void testUpdateDebitCard_Success() throws SQLException {
//        //This object is used for updating the limits
//        DebitCard debitCard = new DebitCard();
//        debitCard.setDebitCardNumber(1234567890981234L);
//        debitCard.setAccountNumber(78903456789123L);
//        debitCard.setCustomerId(200005);
//        debitCard.setDebitCardCvv(111);
//        debitCard.setDebitCardPin(1234);
//        Calendar calendar = Calendar.getInstance();
//        calendar.set(2024, Calendar.APRIL, 4);
//        Date expiryDate = calendar.getTime();
//        debitCard.setDebitCardExpiry(expiryDate);
//        debitCard.setDebitCardStatus("inactive");
//        debitCard.setDomesticLimit(2000.0);
//        debitCard.setInternationalLimit(5000.0);
//
//        // Mock returned DebitCard fetched from the database
//        DebitCard debitCardFetched = new DebitCard();
//        debitCardFetched.setDebitCardNumber(1234567890981234L);
//        debitCardFetched.setAccountNumber(78909876543530L);
//        debitCardFetched.setCustomerId(200005);
//        debitCardFetched.setDebitCardCvv(111);
//        debitCardFetched.setDebitCardPin(1234);
//        calendar.set(2024, Calendar.APRIL, 4);
//        Date expiryUpdateDate = calendar.getTime();
//        debitCardFetched.setDebitCardExpiry(expiryUpdateDate);
//        debitCardFetched.setDebitCardStatus("active");
//        debitCardFetched.setDomesticLimit(200000.0);
//        debitCardFetched.setInternationalLimit(5000000.0);
//
//        if (debitCard.getDebitCardStatus() != debitCardFetched.getDebitCardStatus()) {
//            assertThrows(ValidationException.class, () -> debitCardServices.updateDebitLimit(debitCard));
//        }
//
//        // Stub the jdbcTemplate.queryForObject() method to return the fetchedAccount
//        when(jdbcTemplate.queryForObject(
//                anyString(),
//                any(Object[].class),
//                any(DebitCardServices.DebitCardMapper.class)))
//                .thenReturn(debitCardFetched);
//
//        // Mock the returned execution map with success result
//        Map<String, Object> returnedExecution = new HashMap<>();
//        returnedExecution.put("status", "SQLCODE-003");
//
//        // Stub the jdbcTemplate.call() method to return the mock execution map
//        when(jdbcTemplate.call(any(CallableStatementCreator.class), anyList())).thenReturn(returnedExecution);
//
//        // Calling the method to be tested
//        try {
//            String result = debitCardServices.updateDebitLimit(debitCard);
//        } catch (DebitCardException e) {
//            // If an exception is thrown, assert the failure message
//            assertThrows(DebitCardException.class, () -> debitCardServices.updateDebitLimit(debitCard));
//            // Exit the test early since the expected exception was thrown
//            return;
//        }
//        verify(jdbcTemplate).call(any(CallableStatementCreator.class), anyList());
//    }
//
//
//
//    }
//
//
//
//
//
