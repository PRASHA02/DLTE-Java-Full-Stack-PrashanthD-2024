package debit.cards.web.mybankdebitcard;

import com.fasterxml.jackson.databind.ObjectMapper;

import debit.cards.dao.security.Customer;
import debit.cards.dao.security.CustomerServices;
import debit.cards.web.mybankdebitcard.security.CustomerApi;
import debit.cards.web.mybankdebitcard.security.CustomerSuccessHandler;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@WebMvcTest(CustomerApi.class)
@ExtendWith({SpringExtension.class, MockitoExtension.class})
@AutoConfigureMockMvc
public class SecurityTesting {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerServices customerServices;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private  CustomerApi customerApi;

    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private Authentication authentication;
    @InjectMocks
    private CustomerSuccessHandler customerSuccessHandler;

    @Test
    @WithMockUser(username = "prasha02")
    public void testOnAuthenticationSuccess() throws Exception {
        // Arrange
        Customer customer = new Customer();
        customer.setCustomerStatus("block");

        // Mock authentication principal
        Authentication authentication = mock(Authentication.class);
        when(authentication.getPrincipal()).thenReturn(customer);

        // Mock request and response
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        // Mock the behavior of response.isCommitted() to return false
        when(response.isCommitted()).thenReturn(false);

        // Mock the behavior of response.encodeRedirectURL()
        String expectedRedirectURL = "null/card/login/?errors=Max attempts reached contact admin";
        when(response.encodeRedirectURL(anyString())).thenAnswer(invocation -> {
            String argument = invocation.getArgument(0);
            assertEquals(expectedRedirectURL, argument);
            return argument; // Return the same URL
        });

        // Act
        customerSuccessHandler.onAuthenticationSuccess(request, response, authentication);

        // Assert
        verify(response).encodeRedirectURL(expectedRedirectURL);
    }




    @Test
    @WithMockUser(username = "prasha02")
    public void testSave() {
        // Given
        Customer customer = new Customer();
        customer.setUsername("prasha02");
        customer.setPassword("prash321");

        when(passwordEncoder.encode(customer.getPassword())).thenReturn("$2a$10$scvXI.KiZPNROqCj4rE2pu2yMfVaX85/Sybh9HA1m/3V8D01UyS5K");
        when(customerServices.signingUp(customer)).thenReturn(customer);

        // When
        Customer savedCustomer = customerApi.save(customer);

        // Then
        verify(passwordEncoder).encode("prash321");
        verify(customerServices).signingUp(customer);
        assertEquals("prasha02", savedCustomer.getUsername());
        assertEquals("$2a$10$scvXI.KiZPNROqCj4rE2pu2yMfVaX85/Sybh9HA1m/3V8D01UyS5K", savedCustomer.getPassword());
    }

}