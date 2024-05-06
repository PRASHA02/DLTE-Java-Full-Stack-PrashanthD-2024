package debit.cards.web.mybankdebitcard;

import debit.cards.dao.security.Customer;
import debit.cards.dao.security.CustomerServices;
import debit.cards.web.mybankdebitcard.security.CustomerFailureHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.test.context.support.WithMockUser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
class FailureTesting {
    @Mock
    private CustomerServices customerServices;

    @InjectMocks
    private CustomerFailureHandler customerFailureHandler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    @WithMockUser(username = "prasha02")
    public void testOnAuthenticationFailure_ValidUsername() throws Exception {

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        CustomerServices customerServices = mock(CustomerServices.class);

        // Mock values
        when(request.getParameter("username")).thenReturn("Prashanth");
        Customer customer = new Customer();
        customer.setCustomerStatus("active");
        when(customerServices.findByUserName("Prashanth")).thenReturn(customer);

        // Create instance of CustomerFailureHandler
        CustomerFailureHandler failureHandler = new CustomerFailureHandler();

        // Call the method under test
        AuthenticationException exception = assertThrows(LockedException.class, () -> {
            failureHandler.onAuthenticationFailure(request, response, new AuthenticationException("Account Locked") {});
        });

        // Verify expected behavior
        verify(customerServices, times(1)).updateAttempts(customer);

        // Add more verifications as needed
    }
}