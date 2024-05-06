package debit.cards;

import debit.cards.dao.security.CardSecurity;
import debit.cards.dao.security.CardSecurityServices;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CardSecurityServicesTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private CardSecurityServices cardSecurityServices;

    @Test
    void signingUp_Success() {
        CardSecurity cardSecurity = new CardSecurity();
        cardSecurity.setCustomerName("prasha02");
        cardSecurity.setCustomerAddress("karkala");
        cardSecurity.setCustomerStatus("active");
        cardSecurity.setCustomerContact(1234567890L);
        cardSecurity.setUsername("john");
        cardSecurity.setPassword("prash321");
        cardSecurity.setAttempts(1);

        // Adjust stubbing to match the method invocation in the test
        when(jdbcTemplate.update(
                "insert into mybank_app_customer (CUSTOMER_NAME, CUSTOMER_ADDRESS, CUSTOMER_STATUS, CUSTOMER_CONTACT, USERNAME, PASSWORD, ATTEMPTS) values(?,?,?,?,?,?,?)",
                "prasha02", "karkala", "active", 1234567890L, "john", "prash321", 1))
                .thenReturn(1);

        CardSecurity result = cardSecurityServices.signingUp(cardSecurity);

        assertEquals(cardSecurity, result);
        // Verify that the update method was called with the correct arguments
        verify(jdbcTemplate, times(1)).update(
                "insert into mybank_app_customer (CUSTOMER_NAME, CUSTOMER_ADDRESS, CUSTOMER_STATUS, CUSTOMER_CONTACT, USERNAME, PASSWORD, ATTEMPTS) values(?,?,?,?,?,?,?)",
                "prasha02", "karkala", "active", 1234567890L, "john", "prash321", 1);
    }


    @Test
    void findByUserName_Exists() {
        CardSecurity cardSecurity = new CardSecurity();
        cardSecurity.setCustomerName("prasha02");
        cardSecurity.setCustomerAddress("karkala");
        cardSecurity.setCustomerStatus("active");
        cardSecurity.setCustomerContact(1234567890L);
        cardSecurity.setUsername("john");
        cardSecurity.setPassword("prash321");
        cardSecurity.setAttempts(1);
        List<CardSecurity> customerList = Arrays.asList(cardSecurity);

        when(jdbcTemplate.query(anyString(), any(RowMapper.class))).thenReturn(customerList);

        CardSecurity result = cardSecurityServices.findByUserName("john");

        assertEquals(cardSecurity, result);
        verify(jdbcTemplate, times(1)).query(anyString(), any(RowMapper.class));
    }

    @Test
    void findByUserName_NotFound() {
        when(jdbcTemplate.query(anyString(), any(RowMapper.class))).thenReturn(Arrays.asList());

        CardSecurity result = cardSecurityServices.findByUserName("john");

        assertNull(result);
        verify(jdbcTemplate, times(1)).query(anyString(), any(RowMapper.class));
    }

    @Test
    void updateAttempts_Success() {
        CardSecurity cardSecurity = new CardSecurity();
        cardSecurity.setCustomerName("prasha02");
        cardSecurity.setCustomerAddress("karkala");
        cardSecurity.setCustomerStatus("active");
        cardSecurity.setCustomerContact(1234567890L);
        cardSecurity.setUsername("john");
        cardSecurity.setPassword("prash321");
        cardSecurity.setAttempts(1);
        when(jdbcTemplate.update(
                "update mybank_app_customer set attempts = ? where username = ?",
                1, "john"))
                .thenReturn(1);

       cardSecurityServices.updateAttempts(cardSecurity);

        verify(jdbcTemplate, times(1)).update(
                "update mybank_app_customer set attempts = ? where username = ?",
                1, "john");
    }


    @Test
    void updateStatus_Success() {
        CardSecurity cardSecurity = new CardSecurity();
        cardSecurity.setCustomerName("prasha02");
        cardSecurity.setCustomerAddress("karkala");
        cardSecurity.setCustomerStatus("active");
        cardSecurity.setCustomerContact(1234567890L);
        cardSecurity.setUsername("john");
        cardSecurity.setPassword("prash321");
        cardSecurity.setAttempts(1);

        // Invoke the method under test
        cardSecurityServices.updateStatus(cardSecurity);

        // Verify that jdbcTemplate's update method was called with specific arguments
        verify(jdbcTemplate, times(1)).update(
                eq("update mybank_app_customer set customer_status = 'block' where username = ?"),
                eq("john")
        );

        // Verify that no more interactions occurred with jdbcTemplate
        verifyNoMoreInteractions(jdbcTemplate);
    }



    @Test
    void getAccountUsernameFound() {
        Long accountNumber = 12345L;
        String expectedUsername = "john";

        when(jdbcTemplate.queryForObject(anyString(), any(Object[].class), eq(String.class))).thenReturn(expectedUsername);

        String result = cardSecurityServices.getAccountOwnerUsername(accountNumber);

        assertEquals(expectedUsername, result);
        verify(jdbcTemplate, times(1)).queryForObject(anyString(), any(Object[].class), eq(String.class));
    }

    @Test
    void getAccountUsernameNotFound() {
        Long accountNumber = 12345L;

        when(jdbcTemplate.queryForObject(anyString(), any(Object[].class), eq(String.class))).thenThrow(EmptyResultDataAccessException.class);

        String result = cardSecurityServices.getAccountOwnerUsername(accountNumber);

        assertNull(result);
        verify(jdbcTemplate, times(1)).queryForObject(anyString(), any(Object[].class), eq(String.class));
    }

    @Test
    void getAccountException() {
        Long accountNumber = 12345L;

        when(jdbcTemplate.queryForObject(anyString(), any(Object[].class), eq(String.class))).thenThrow(RuntimeException.class);

        String result = cardSecurityServices.getAccountOwnerUsername(accountNumber);

        assertNull(result);
        verify(jdbcTemplate, times(1)).queryForObject(anyString(), any(Object[].class), eq(String.class));
    }



    @Test
    void loadUserByUsernameNotFound() {
        assertThrows(UsernameNotFoundException.class, () -> cardSecurityServices.loadUserByUsername("john"));
    }


    @Test
    void filterByUserNameFound() {
        CardSecurity cardSecurity = new CardSecurity();
        cardSecurity.setCustomerName("prasha02");
        cardSecurity.setCustomerAddress("karkala");
        cardSecurity.setCustomerStatus("active");
        cardSecurity.setCustomerContact(1234567890L);
        cardSecurity.setUsername("john");
        cardSecurity.setPassword("prash321");
        cardSecurity.setAttempts(1);
        CardSecurity cardSecurityTwo = new CardSecurity();
        cardSecurityTwo.setCustomerName("prasha02");
        cardSecurityTwo.setCustomerAddress("karkala");
        cardSecurityTwo.setCustomerStatus("active");
        cardSecurityTwo.setCustomerContact(1234567890L);
        cardSecurityTwo.setUsername("prash");
        cardSecurityTwo.setPassword("prash321");
        cardSecurityTwo.setAttempts(1);
        List<CardSecurity> customerList = Arrays.asList(
              cardSecurity,cardSecurityTwo
        );

        CardSecurity result = cardSecurityServices.filterByUserName(customerList, "john");

        assertEquals(customerList.get(0), result);
    }

    @Test
    void filterByUserNameNotFound() {
        CardSecurity cardSecurity = new CardSecurity();
        cardSecurity.setCustomerName("prasha02");
        cardSecurity.setCustomerAddress("karkala");
        cardSecurity.setCustomerStatus("active");
        cardSecurity.setCustomerContact(1234567890L);
        cardSecurity.setUsername("john");
        cardSecurity.setPassword("prash321");
        cardSecurity.setAttempts(1);
        CardSecurity cardSecurityTwo = new CardSecurity();
        cardSecurityTwo.setCustomerName("prasha02");
        cardSecurityTwo.setCustomerAddress("karkala");
        cardSecurityTwo.setCustomerStatus("active");
        cardSecurityTwo.setCustomerContact(1234567890L);
        cardSecurityTwo.setUsername("prash");
        cardSecurityTwo.setPassword("prash321");
        List<CardSecurity> customerList = Arrays.asList(
              cardSecurity,cardSecurityTwo
        );

        CardSecurity result = cardSecurityServices.filterByUserName(customerList, "john");

        assertNotNull(result);
    }

    @Test

    public void testPasswordMatch() {

        CardSecurityServices myBankUsersServices = mock(CardSecurityServices.class);

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


        // Setup test data

        String username = "prasha02";

        String rawPassword = "prash";

        String encodedPassword =passwordEncoder.encode(rawPassword);

        // Configure mock behavior

        CardSecurity customer = new CardSecurity();

        customer.setUsername(username);

        customer.setPassword(encodedPassword);

        when(myBankUsersServices.loadUserByUsername(username))

                .thenReturn(customer);

        // Invoke the authentication process

        UserDetails userDetails = myBankUsersServices.loadUserByUsername(username);

        String enteredPassword="prash";

        // Verify the result

        assertTrue(passwordEncoder.matches(enteredPassword, userDetails.getPassword()));

    }

    // Add more tests for edge cases and error scenarios if necessary
}
