package debit.cards;

import debit.cards.dao.entities.DebitCard;
import debit.cards.dao.exceptions.*;
import debit.cards.dao.services.DebitCardServices;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.sql.*;
import java.util.*;
import java.util.Date;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class DlteMyBankProjectDaoApplicationTests {

    //This annotation is used to create a mock object of a class or interface. It creates a mock instance that you can then use to define behaviors, verify interactions, etc.
    @Mock
    private JdbcTemplate jdbcTemplate;
    //This annotation is used to inject mock dependencies into the target object when its instantiated
    @InjectMocks
    public DebitCardServices debitCardServices;

    @Autowired
    private DlteMyBankProjectDaoApplication application;

    @Test
    void contextLoads() {
        System.out.println("Test has Started");
    }

    @Mock
    private Logger logger;

    private ResourceBundle resourceBundle;

    @BeforeEach
    public void setup() {
        // Load the resource bundle for a specific locale
        resourceBundle = ResourceBundle.getBundle("application");
    }

    @Test
    public void testResourceBundleContent() {
        // Test specific expected values and key
        assertEquals("No Debit cards available", resourceBundle.getString("card.list.null"));
    }


    @Test
    void testAllDebitCards() throws SQLException {
        // Mocking the response from the database
        List<DebitCard> debitCardList = new ArrayList<>();

        //This object is used for updating the limits
        DebitCard debitCard = new DebitCard();
        debitCard.setDebitCardNumber(1234567890981234L);
        debitCard.setAccountNumber(78903456789123L);
        debitCard.setCustomerId(200005);
        debitCard.setDebitCardCvv(111);
        debitCard.setDebitCardPin(1234);
        Calendar calendar = Calendar.getInstance();
        calendar.set(2024, Calendar.APRIL, 4);
        Date expiryDate = calendar.getTime();

        debitCard.setDebitCardExpiry(expiryDate);
        debitCard.setDebitCardStatus("active");
        debitCard.setDomesticLimit(2000.0);
        debitCard.setInternationalLimit(5000.0);

        // Mock returned DebitCard fetched from the database
        DebitCard debitCardTwo = new DebitCard();
        debitCardTwo.setDebitCardNumber(1234567890981234L);
        debitCardTwo.setAccountNumber(78903456789123L);
        debitCardTwo.setCustomerId(200005);
        debitCardTwo.setDebitCardCvv(111);
        debitCardTwo.setDebitCardPin(1234);
        calendar.set(2024, Calendar.APRIL, 4);
        Date expiryUpdateDate = calendar.getTime();
        debitCardTwo.setDebitCardExpiry(expiryUpdateDate);
        debitCardTwo.setDebitCardStatus("active");
        debitCardTwo.setDomesticLimit(200000.0);
        debitCardTwo.setInternationalLimit(5000000.0);
        //Add some dummy data into the arrayList for testing
        debitCardList = Stream.of(debitCard, debitCardTwo).collect(Collectors.toList());

        //Fetching the data from database
        when(jdbcTemplate.query(anyString(), any(Object[].class), any(DebitCardServices.DebitCardMapper.class))).thenReturn(debitCardList);


        List<DebitCard> result = debitCardServices.getDebitCard("prasha02");

        // Verify the exception message
        assertEquals(2, result.size());

        // Verify that jdbcTemplate.query() was called with the expected SQL query and arguments
        verify(jdbcTemplate).query(anyString(), any(Object[].class), any(DebitCardServices.DebitCardMapper.class));
    }

    @Test
    void testAllDebitCardsSQLException() throws SQLException {

        // Define the expected exception message
        String expectedMessage = "SQL Syntax is Not proper try to resolve it";

        // Mock jdbcTemplate.query() to throw DebitCardException
        when(jdbcTemplate.query(anyString(), any(Object[].class), any(DebitCardServices.DebitCardMapper.class)))
                .thenThrow(new DebitCardException(expectedMessage));

        // Assert that calling debitCardServices.getDebitCard("prasha02") throws DebitCardException
        DebitCardException exception = assertThrows(DebitCardException.class, () -> debitCardServices.getDebitCard("prasha02"));

        // Verify that the exception message is as expected
        assertEquals(expectedMessage, exception.getMessage());

        // Verify that jdbcTemplate.query() was called with the expected SQL query and arguments
        verify(jdbcTemplate).query(anyString(), any(Object[].class), any(DebitCardServices.DebitCardMapper.class));
    }

    ////
    @Test
    void testGetDebitCardEmptyList() {
        // Mocking the response from the database
        List<DebitCard> debitCardList = new ArrayList<>();

        //This object is used for updating the limits
        DebitCard debitCard = new DebitCard();
        debitCard.setDebitCardNumber(1234567890981234L);
        debitCard.setAccountNumber(78903456789123L);
        debitCard.setCustomerId(200005);
        debitCard.setDebitCardCvv(111);
        debitCard.setDebitCardPin(1234);
        Calendar calendar = Calendar.getInstance();
        calendar.set(2024, Calendar.APRIL, 4);
        Date expiryDate = calendar.getTime();
        System.out.println(expiryDate);
        debitCard.setDebitCardExpiry(expiryDate);
        debitCard.setDebitCardStatus("block");
        debitCard.setDomesticLimit(2000.0);
        debitCard.setInternationalLimit(5000.0);

        // Mock returned DebitCard fetched from the database
        DebitCard debitCardTwo = new DebitCard();
        debitCardTwo.setDebitCardNumber(1234567890981234L);
        debitCardTwo.setAccountNumber(78903456789123L);
        debitCardTwo.setCustomerId(200005);
        debitCardTwo.setDebitCardCvv(111);
        debitCardTwo.setDebitCardPin(1234);
        calendar.set(2024, Calendar.APRIL, 4);
        Date expiryUpdateDate = calendar.getTime();
        debitCardTwo.setDebitCardExpiry(expiryUpdateDate);
        debitCardTwo.setDebitCardStatus("active");
        debitCardTwo.setDomesticLimit(200000.0);
        debitCardTwo.setInternationalLimit(5000000.0);
        //Add some dummy data into the arrayList for testing
        debitCardList = Stream.of(debitCard, debitCardTwo).collect(Collectors.toList());

        //Fetching the data from database
        when(jdbcTemplate.query(anyString(), any(Object[].class), any(DebitCardServices.DebitCardMapper.class))).thenThrow(new DebitCardException("No Debit cards available"));

        // Call the method under test with a sample customer ID and capture the exception
        DebitCardException exception = assertThrows(DebitCardException.class, () -> {
            debitCardServices.getDebitCard("prasha02");
        });

        // Verify the exception message
        assertEquals("No Debit cards available", exception.getMessage());

        // Verify that jdbcTemplate.query() was called with the expected SQL query and arguments
        verify(jdbcTemplate).query(anyString(), any(Object[].class), any(DebitCardServices.DebitCardMapper.class));
    }


    @Test
    public void testListAllCards() throws SQLException {

        // Mock ResultSet
        ResultSet resultSet = mock(ResultSet.class);
        when(resultSet.getLong(1)).thenReturn(1234567890123456L);
        when(resultSet.getLong(2)).thenReturn(90876543212345L);
        when(resultSet.getInt(3)).thenReturn(123456);
        when(resultSet.getInt(4)).thenReturn(342);
        when(resultSet.getInt(5)).thenReturn(2312);

        // Create a java.util.Date object
        Calendar calendar = Calendar.getInstance();
        calendar.set(2024, Calendar.APRIL, 4);
        Date expiryDateUtil = calendar.getTime();

        // Convert to java.sql.Date
        java.sql.Date expiryDateSql = new java.sql.Date(expiryDateUtil.getTime());

        // Stub the ResultSet to return the java.sql.Date
        when(resultSet.getDate(6)).thenReturn(expiryDateSql);

        when(resultSet.getString(7)).thenReturn("active");
        when(resultSet.getDouble(8)).thenReturn(50000.0);
        when(resultSet.getDouble(9)).thenReturn(80000.0);

        // Mock RowMapper
        DebitCardServices.DebitCardMapper mapper = debitCardServices.new DebitCardMapper();
        DebitCard debitCard = mapper.mapRow(resultSet, 1); // Ensure the index is appropriate

        // Assertions
        assertEquals(1234567890123456L, debitCard.getDebitCardNumber());
        assertEquals(90876543212345L, debitCard.getAccountNumber());
        assertEquals(123456, debitCard.getCustomerId());
        assertEquals(342, debitCard.getDebitCardCvv());
        assertEquals(2312, debitCard.getDebitCardPin());
        assertEquals(expiryDateSql, debitCard.getDebitCardExpiry()); // Ensure to compare with sql.Date
        assertEquals("active", debitCard.getDebitCardStatus());
        assertEquals(50000.0, debitCard.getDomesticLimit());
        assertEquals(80000.0, debitCard.getInternationalLimit());
    }

    //Rest testing for updating the limits of the debit card
    @Test
    public void testUpdateDebitLimit_Success() throws SQLException {
        // This part remains unchanged
        //This object is used for updating the limits
        DebitCard debitCard = new DebitCard();
        debitCard.setDebitCardNumber(1234567890981234L);
        debitCard.setAccountNumber(78903456789123L);
        debitCard.setCustomerId(200005);
        debitCard.setDebitCardCvv(111);
        debitCard.setDebitCardPin(1234);
        Calendar calendar = Calendar.getInstance();
        calendar.set(2024, Calendar.APRIL, 4);
        Date expiryDate = calendar.getTime();
        System.out.println(expiryDate);
        debitCard.setDebitCardExpiry(expiryDate);
        debitCard.setDebitCardStatus("active");
        debitCard.setDomesticLimit(2000.0);
        debitCard.setInternationalLimit(5000.0);

        // Mock returned DebitCard fetched from the database
        DebitCard debitCardFetched = new DebitCard();
        debitCardFetched.setDebitCardNumber(1234567890981234L);
        debitCardFetched.setAccountNumber(78903456789123L);
        debitCardFetched.setCustomerId(200005);
        debitCardFetched.setDebitCardCvv(111);
        debitCardFetched.setDebitCardPin(1234);
        calendar.set(2024, Calendar.APRIL, 4);
        Date expiryUpdateDate = calendar.getTime();
        debitCardFetched.setDebitCardExpiry(expiryUpdateDate);
        debitCardFetched.setDebitCardStatus("active");
        debitCardFetched.setDomesticLimit(200000.0);
        debitCardFetched.setInternationalLimit(5000000.0);

        // Stub the jdbcTemplate.queryForObject() method to return the fetchedAccount
        when(jdbcTemplate.queryForObject(
                anyString(),
                any(Object[].class),
                any(DebitCardServices.DebitCardMapper.class)))
                .thenReturn(debitCardFetched);

        // Mock the returned execution map with success result
        Map<String, Object> returnedExecution = new HashMap<>();
        returnedExecution.put("status", "SQLCODE-000");

        // Stub the jdbcTemplate.call() method to return the mock execution map
        when(jdbcTemplate.call(any(CallableStatementCreator.class), any())).thenReturn(returnedExecution);

        // Calling the method to be tested
        String result = debitCardServices.updateDebitLimit(debitCard);

        // Asserting that the result is as expected
        assertEquals("Debit card limit updated successfully", result);

        // Verify that jdbcTemplate.call() was called with the expected arguments
        verify(jdbcTemplate).call(any(CallableStatementCreator.class), anyList());
    }

    @Test
    public void testUpdateDebitLimit_Failure() throws SQLException {

        DebitCard debitCard = new DebitCard();
        debitCard.setDebitCardNumber(1234567890981234L);
        debitCard.setAccountNumber(78903456789123L);
        debitCard.setCustomerId(200005);
        debitCard.setDebitCardCvv(111);
        debitCard.setDebitCardPin(1234);
        Calendar calendar = Calendar.getInstance();
        calendar.set(2024, Calendar.APRIL, 4);
        Date expiryDate = calendar.getTime();
        System.out.println(expiryDate);
        debitCard.setDebitCardExpiry(expiryDate);
        debitCard.setDebitCardStatus("active");
        debitCard.setDomesticLimit(2000.0);
        debitCard.setInternationalLimit(5000.0);
        // Mock returned DebitCard fetched from the database
        DebitCard debitCardFetched = new DebitCard();
        debitCardFetched.setDebitCardNumber(1234567890981234L);
        debitCardFetched.setAccountNumber(78903456789123L);
        debitCardFetched.setCustomerId(200005);
        debitCardFetched.setDebitCardCvv(111);
        debitCardFetched.setDebitCardPin(1234);

        calendar.set(2024, Calendar.APRIL, 4);
        Date expiryUpdateDate = calendar.getTime();
        debitCardFetched.setDebitCardExpiry(expiryUpdateDate);
        debitCardFetched.setDebitCardStatus("active");
        debitCardFetched.setDomesticLimit(200000.0);
        debitCardFetched.setInternationalLimit(5000000.0);

        // Stub the jdbcTemplate.queryForObject() method to return the fetchedAccount
        when(jdbcTemplate.queryForObject(
                anyString(),
                any(Object[].class),
                any(DebitCardServices.DebitCardMapper.class)))
                .thenReturn(debitCardFetched);

        // Mock the returned execution map with failure result
        Map<String, Object> returnedExecution = new HashMap<>();
        returnedExecution.put("status", "SQLCODE-003");

        // Stub the jdbcTemplate.call() method to return the mock execution map
        when(jdbcTemplate.call(any(CallableStatementCreator.class), anyList())).thenReturn(returnedExecution);

        // Calling the method to be tested
        try {
            String result = debitCardServices.updateDebitLimit(debitCard);
        } catch (DebitCardException e) {
            // If an exception is thrown, assert the failure message
            assertEquals("Debit card limit update failed", e.getMessage());
            // Exit the test early since the expected exception was thrown

        }
    }



    @Test
    public void testUpdateDebitCard_Success() throws SQLException {
        //This object is used for updating the limits
        DebitCard debitCard = new DebitCard();
        debitCard.setDebitCardNumber(1234567890981234L);
        debitCard.setAccountNumber(78903456789123L);
        debitCard.setCustomerId(200005);
        debitCard.setDebitCardCvv(111);
        debitCard.setDebitCardPin(1234);
        Calendar calendar = Calendar.getInstance();
        calendar.set(2024, Calendar.APRIL, 4);
        Date expiryDate = calendar.getTime();
        System.out.println(expiryDate);
        debitCard.setDebitCardExpiry(expiryDate);
        debitCard.setDebitCardStatus("inactive");
        debitCard.setDomesticLimit(2000.0);
        debitCard.setInternationalLimit(5000.0);

        // Mock returned DebitCard fetched from the database
        DebitCard debitCardFetched = new DebitCard();
        debitCardFetched.setDebitCardNumber(1234567890981234L);
        debitCardFetched.setAccountNumber(78903456789123L);
        debitCardFetched.setCustomerId(200005);
        debitCardFetched.setDebitCardCvv(111);
        debitCardFetched.setDebitCardPin(1234);
        calendar.set(2024, Calendar.APRIL, 4);
        Date expiryUpdateDate = calendar.getTime();
        debitCardFetched.setDebitCardExpiry(expiryUpdateDate);
        debitCardFetched.setDebitCardStatus("active");
        debitCardFetched.setDomesticLimit(200000.0);
        debitCardFetched.setInternationalLimit(5000000.0);


        // Stub the jdbcTemplate.queryForObject() method to return the fetchedAccount
        when(jdbcTemplate.queryForObject(
                anyString(),
                any(Object[].class),
                any(DebitCardServices.DebitCardMapper.class)))
                .thenReturn(debitCardFetched);

        // Mock the returned execution map with success result
        Map<String, Object> returnedExecution = new HashMap<>();
        returnedExecution.put("status", "SQLCODE-003");

        // Stub the jdbcTemplate.call() method to return the mock execution map
        when(jdbcTemplate.call(any(CallableStatementCreator.class), anyList())).thenReturn(returnedExecution);

        // Calling the method to be tested
        try {
            String result = debitCardServices.updateDebitLimit(debitCard);
        } catch (DebitCardException e) {
            // If an exception is thrown, assert the failure message
            assertThrows(DebitCardException.class, () -> debitCardServices.updateDebitLimit(debitCard));
            // Exit the test early since the expected exception was thrown

        }
//        verify(jdbcTemplate).call(any(CallableStatementCreator.class), anyList());
    }

    @Test
    public void testUpdateDebitCard_Failure() throws SQLException {
        //This object is used for updating the limits
        DebitCard debitCard = new DebitCard();
        debitCard.setDebitCardNumber(1234567890981234L);
        debitCard.setAccountNumber(78903456789123L);
        debitCard.setCustomerId(200005);
        debitCard.setDebitCardCvv(111);
        debitCard.setDebitCardPin(1234);
        Calendar calendar = Calendar.getInstance();
        calendar.set(2024, Calendar.APRIL, 4);
        Date expiryDate = calendar.getTime();
        System.out.println(expiryDate);
        debitCard.setDebitCardExpiry(expiryDate);
        debitCard.setDebitCardStatus("inactive");
        debitCard.setDomesticLimit(2000.0);
        debitCard.setInternationalLimit(5000.0);

        // Mock returned DebitCard fetched from the database
        DebitCard debitCardFetched = new DebitCard();
        debitCardFetched.setDebitCardNumber(1234567890981234L);
        debitCardFetched.setAccountNumber(78903456789123L);
        debitCardFetched.setCustomerId(200005);
        debitCardFetched.setDebitCardCvv(111);
        debitCardFetched.setDebitCardPin(1234);
        calendar.set(2024, Calendar.APRIL, 4);
        Date expiryUpdateDate = calendar.getTime();
        debitCardFetched.setDebitCardExpiry(expiryUpdateDate);
        debitCardFetched.setDebitCardStatus("active");
        debitCardFetched.setDomesticLimit(200000.0);
        debitCardFetched.setInternationalLimit(5000000.0);


        // Stub the jdbcTemplate.queryForObject() method to return the fetchedAccount
        when(jdbcTemplate.queryForObject(
                anyString(),
                any(Object[].class),
                any(DebitCardServices.DebitCardMapper.class)))
                .thenReturn(debitCardFetched);

        // Mock the returned execution map with failure result
        Map<String, Object> returnedExecution = new HashMap<>();
        returnedExecution.put("status", "SQLCODE-003"); // Replace YYY with the appropriate failure code

        // Stub the jdbcTemplate.call() method to return the mock execution map
        when(jdbcTemplate.call(any(CallableStatementCreator.class), anyList())).thenReturn(returnedExecution);

        // Calling the method to be tested
        try {
            String result = debitCardServices.updateDebitLimit(debitCard);
        } catch (DebitCardException e) {
            // If an exception is thrown, assert the failure message
            assertEquals("Debit card limit update failed", e.getMessage());
            // Exit the test early since the expected exception was thrown
        }
    }

    @Test
    public void testUpdateDebitLimit_NoAccountFound() throws SQLException {
        DebitCard debitCard = new DebitCard();
        debitCard.setDebitCardNumber(1234567890981234L);
        debitCard.setAccountNumber(78903456789123L);
        debitCard.setCustomerId(200005);
        debitCard.setDebitCardCvv(111);
        debitCard.setDebitCardPin(1234);
        Calendar calendar = Calendar.getInstance();
        calendar.set(2024, Calendar.APRIL, 4);
        Date expiryDate = calendar.getTime();
        System.out.println(expiryDate);
        debitCard.setDebitCardExpiry(expiryDate);
        debitCard.setDebitCardStatus("inactive");
        debitCard.setDomesticLimit(2000.0);
        debitCard.setInternationalLimit(5000.0);

        // Mock returned DebitCard fetched from the database
        DebitCard debitCardFetched = new DebitCard();
        debitCardFetched.setDebitCardNumber(12345678909L);
        debitCardFetched.setAccountNumber(78903456789123L);
        debitCardFetched.setCustomerId(200005);
        debitCardFetched.setDebitCardCvv(111);
        debitCardFetched.setDebitCardPin(1234);
        calendar.set(2024, Calendar.APRIL, 4);
        Date expiryUpdateDate = calendar.getTime();
        debitCardFetched.setDebitCardExpiry(expiryUpdateDate);
        debitCardFetched.setDebitCardStatus("active");
        debitCardFetched.setDomesticLimit(200000.0);
        debitCardFetched.setInternationalLimit(5000000.0);

        when(jdbcTemplate.queryForObject(
                anyString(),
                any(Object[].class),
                any(DebitCardServices.DebitCardMapper.class)))
                .thenThrow(new DebitCardException("No Account Found"));

        // Calling the method to be tested and capturing the exception
        DebitCardException exception = assertThrows(DebitCardException.class, () -> {
            debitCardServices.updateDebitLimit(debitCard);
        });

        // Verifying that the correct exception is thrown and the logger is called with the expected message
        assertEquals("No Account Found", exception.getMessage());
    }
    @Test
    public void testUpdateDebitLimit_InternalError() throws SQLException {
        DebitCard debitCard = new DebitCard();
        debitCard.setDebitCardNumber(1234567890981234L);
        debitCard.setAccountNumber(78903456789123L);
        debitCard.setCustomerId(200005);
        debitCard.setDebitCardCvv(111);
        debitCard.setDebitCardPin(1234);
        Calendar calendar = Calendar.getInstance();
        calendar.set(2024, Calendar.APRIL, 4);
        Date expiryDate = calendar.getTime();
        System.out.println(expiryDate);
        debitCard.setDebitCardExpiry(expiryDate);
        debitCard.setDebitCardStatus("inactive");
        debitCard.setDomesticLimit(2000.0);
        debitCard.setInternationalLimit(5000.0);

        // Mock returned DebitCard fetched from the database
        DebitCard debitCardFetched = new DebitCard();
        debitCardFetched.setDebitCardNumber(12345678909L);
        debitCardFetched.setAccountNumber(78903456789123L);
        debitCardFetched.setCustomerId(200005);
        debitCardFetched.setDebitCardCvv(111);
        debitCardFetched.setDebitCardPin(1234);
        calendar.set(2024, Calendar.APRIL, 4);
        Date expiryUpdateDate = calendar.getTime();
        debitCardFetched.setDebitCardExpiry(expiryUpdateDate);
        debitCardFetched.setDebitCardStatus("active");
        debitCardFetched.setDomesticLimit(200000.0);
        debitCardFetched.setInternationalLimit(5000000.0);

        when(jdbcTemplate.queryForObject(
                anyString(),
                any(Object[].class),
                any(DebitCardServices.DebitCardMapper.class)))
                .thenThrow(new DataAccessException("Internal Server Error Occurred") {
                });

        // Calling the method to be tested and capturing the exception
        DataAccessException exception = assertThrows(DataAccessException.class, () -> {
            debitCardServices.updateDebitLimit(debitCard);
        });

        // Verifying that the correct exception is thrown and the logger is called with the expected message
        assertEquals("Internal Server Error Occurred", exception.getMessage());
        // Mock the jdbcTemplate.queryForObject() method to throw a DataAccessException
    }



    @Test
    public void testUpdateDebitLimitSuccess() throws SQLException {
        // Mocking the necessary objects and methods for a successful update

        DebitCard debitCard = new DebitCard();
        debitCard.setDebitCardNumber(1234567890981234L);
        debitCard.setAccountNumber(78903456789123L);
        debitCard.setCustomerId(200005);
        debitCard.setDebitCardCvv(111);
        debitCard.setDebitCardPin(1234);
        Calendar calendar = Calendar.getInstance();
        calendar.set(2024, Calendar.APRIL, 4);
        Date expiryDate = calendar.getTime();
        System.out.println(expiryDate);
        debitCard.setDebitCardExpiry(expiryDate);
        debitCard.setDebitCardStatus("inactive");
        debitCard.setDomesticLimit(2000.0);
        debitCard.setInternationalLimit(5000.0);

        // Mock returned DebitCard fetched from the database
        DebitCard debitCardFetched = new DebitCard();
        debitCardFetched.setDebitCardNumber(1234567890981234L);
        debitCardFetched.setAccountNumber(78903456789123L);
        debitCardFetched.setCustomerId(200005);
        debitCardFetched.setDebitCardCvv(111);
        debitCardFetched.setDebitCardPin(1234);
        calendar.set(2024, Calendar.APRIL, 4);
        Date expiryUpdateDate = calendar.getTime();
        debitCardFetched.setDebitCardExpiry(expiryUpdateDate);
        debitCardFetched.setDebitCardStatus("active");
        debitCardFetched.setDomesticLimit(200000.0);
        debitCardFetched.setInternationalLimit(5000000.0); // Set a sample account number

        when(jdbcTemplate.queryForObject(
                anyString(),
                any(Object[].class),
                any(DebitCardServices.DebitCardMapper.class)))
                .thenReturn(debitCard);

        // Mock the result of jdbcTemplate.call() to return a success status
        Map<String, Object> returnedExecution = new HashMap<>();
        returnedExecution.put("status", "SQLCODE-000");
        when(jdbcTemplate.call(any(CallableStatementCreator.class), any()))
                .thenReturn(returnedExecution);

        // Calling the method to be tested
        String result = debitCardServices.updateDebitLimit(debitCard);
        assertEquals(resourceBundle.getString("limit.update.success"), result);
    }

    @Mock
    private MethodArgumentNotValidException mockException;

    @Mock
    private BindingResult mockBindingResult;





}

