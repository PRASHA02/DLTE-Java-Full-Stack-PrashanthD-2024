package debit.cards;

import debit.cards.dao.security.Customer;
import debit.cards.dao.security.CustomerServices;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerServicesTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private CustomerServices customerServices;

    @Test
    void signingUp_Success() {
        Customer customer = new Customer();
        customer.setCustomerName("prasha02");
        customer.setCustomerAddress("karkala");
        customer.setCustomerStatus("active");
        customer.setCustomerContact(1234567890L);
        customer.setUsername("john");
        customer.setPassword("prash321");
        customer.setAttempts(1);

        // Adjust stubbing to match the method invocation in the test
        when(jdbcTemplate.update(
                "insert into mybank_app_customer (CUSTOMER_NAME, CUSTOMER_ADDRESS, CUSTOMER_STATUS, CUSTOMER_CONTACT, USERNAME, PASSWORD, ATTEMPTS) values(?,?,?,?,?,?,?)",
                "prasha02", "karkala", "active", 1234567890L, "john", "prash321", 1))
                .thenReturn(1);

        Customer result = customerServices.signingUp(customer);

        assertEquals(customer, result);
        // Verify that the update method was called with the correct arguments
        verify(jdbcTemplate, times(1)).update(
                "insert into mybank_app_customer (CUSTOMER_NAME, CUSTOMER_ADDRESS, CUSTOMER_STATUS, CUSTOMER_CONTACT, USERNAME, PASSWORD, ATTEMPTS) values(?,?,?,?,?,?,?)",
                "prasha02", "karkala", "active", 1234567890L, "john", "prash321", 1);
    }


    @Test
    void findByUserName_Exists() {
        Customer customer = new Customer();
        customer.setCustomerName("prasha02");
        customer.setCustomerAddress("karkala");
        customer.setCustomerStatus("active");
        customer.setCustomerContact(1234567890L);
        customer.setUsername("john");
        customer.setPassword("prash321");
        customer.setAttempts(1);
        List<Customer> customerList = Arrays.asList(customer);

        when(jdbcTemplate.query(anyString(), any(RowMapper.class))).thenReturn(customerList);

        Customer result = customerServices.findByUserName("john");

        assertEquals(customer, result);
        verify(jdbcTemplate, times(1)).query(anyString(), any(RowMapper.class));
    }

    @Test
    void findByUserName_NotFound() {
        when(jdbcTemplate.query(anyString(), any(RowMapper.class))).thenReturn(Arrays.asList());

        Customer result = customerServices.findByUserName("john");

        assertNull(result);
        verify(jdbcTemplate, times(1)).query(anyString(), any(RowMapper.class));
    }

    @Test
    void updateAttempts_Success() {
        Customer customer = new Customer();
        customer.setCustomerName("prasha02");
        customer.setCustomerAddress("karkala");
        customer.setCustomerStatus("active");
        customer.setCustomerContact(1234567890L);
        customer.setUsername("john");
        customer.setPassword("prash321");
        customer.setAttempts(1);
        when(jdbcTemplate.update(
                "update mybank_app_customer set attempts = ? where username = ?",
                1, "john"))
                .thenReturn(1);

       customerServices.updateAttempts(customer);

        verify(jdbcTemplate, times(1)).update(
                "update mybank_app_customer set attempts = ? where username = ?",
                1, "john");
    }


    @Test
    void updateStatus_Success() {
        Customer customer = new Customer();
        customer.setCustomerName("prasha02");
        customer.setCustomerAddress("karkala");
        customer.setCustomerStatus("active");
        customer.setCustomerContact(1234567890L);
        customer.setUsername("john");
        customer.setPassword("prash321");
        customer.setAttempts(1);

        // Invoke the method under test
        customerServices.updateStatus(customer);

        // Verify that jdbcTemplate's update method was called with specific arguments
        verify(jdbcTemplate, times(1)).update(
                eq("update mybank_app_customer set customer_status = 'block' where username = ?"),
                eq("john")
        );

        // Verify that no more interactions occurred with jdbcTemplate
        verifyNoMoreInteractions(jdbcTemplate);
    }


    @Test
    void loadUserByUsernameNotFound() {
        assertThrows(UsernameNotFoundException.class, () -> customerServices.loadUserByUsername("john"));
    }


    @Test
    void filterByUserNameFound() {
        Customer customer = new Customer();
        customer.setCustomerName("prasha02");
        customer.setCustomerAddress("karkala");
        customer.setCustomerStatus("active");
        customer.setCustomerContact(1234567890L);
        customer.setUsername("john");
        customer.setPassword("prash321");
        customer.setAttempts(1);
        Customer customerTwo = new Customer();
        customerTwo.setCustomerName("prasha02");
        customerTwo.setCustomerAddress("karkala");
        customerTwo.setCustomerStatus("active");
        customerTwo.setCustomerContact(1234567890L);
        customerTwo.setUsername("prash");
        customerTwo.setPassword("prash321");
        customerTwo.setAttempts(1);
        List<Customer> customerList = Arrays.asList(
                customer, customerTwo
        );

        Customer result = customerServices.filterByUserName(customerList, "john");

        assertEquals(customerList.get(0), result);
    }

    @Test
    void filterByUserNameNotFound() {
        Customer customer = new Customer();
        customer.setCustomerName("prasha02");
        customer.setCustomerAddress("karkala");
        customer.setCustomerStatus("active");
        customer.setCustomerContact(1234567890L);
        customer.setUsername("john");
        customer.setPassword("prash321");
        customer.setAttempts(1);
        Customer customerTwo = new Customer();
        customerTwo.setCustomerName("prasha02");
        customerTwo.setCustomerAddress("karkala");
        customerTwo.setCustomerStatus("active");
        customerTwo.setCustomerContact(1234567890L);
        customerTwo.setUsername("prash");
        customerTwo.setPassword("prash321");
        List<Customer> customerList = Arrays.asList(
                customer, customerTwo
        );

        Customer result = customerServices.filterByUserName(customerList, "john");

        assertNotNull(result);
    }

    @Test

    public void testPasswordMatch() {

        CustomerServices myBankUsersServices = mock(CustomerServices.class);

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


        // Setup test data

        String username = "prasha02";

        String rawPassword = "prash";

        String encodedPassword =passwordEncoder.encode(rawPassword);

        // Configure mock behavior

        Customer customer = new Customer();

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

    @Test
    public void testIsAccountNonExpired() {
        Customer userDetails = new Customer();
        assertTrue(userDetails.isAccountNonExpired());
    }

    @Test
    public void testIsAccountNonLocked() {
        Customer userDetails = new Customer();
        assertTrue(userDetails.isAccountNonLocked());
    }

    @Test
    public void testIsCredentialsNonExpired() {
        Customer userDetails = new Customer();
        assertTrue(userDetails.isCredentialsNonExpired());
    }

    @Test
    public void testIsEnabled() {
        Customer userDetails = new Customer();
        assertTrue(userDetails.isEnabled());
    }
    @Test
    public void maxAttempts() {
        int expectedMaxAttempt = 3; // Assuming the maxAttempt value is 5
        Customer customUserDetails = new Customer();

        customUserDetails.setAttempts(expectedMaxAttempt);

        // When
        int actualMaxAttempt = customUserDetails.getMaxAttempt();

        // Then
        assertEquals(expectedMaxAttempt, actualMaxAttempt);
    }


    @Test
    public void testGetAuthoritiesIsNull() {
        // Given
        Customer customUserDetails = new Customer();

        // When
        Collection<? extends GrantedAuthority> authorities = customUserDetails.getAuthorities();

        // Then
        assertEquals(null, authorities);
        // Add additional assertions based on how your application handles null authorities
    }

    // Add more tests for edge cases and error scenarios if necessary
}
