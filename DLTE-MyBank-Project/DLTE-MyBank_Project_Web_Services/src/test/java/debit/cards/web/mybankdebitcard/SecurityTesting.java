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
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@WebMvcTest(CustomerApi.class)
@ExtendWith({SpringExtension.class, MockitoExtension.class})
@AutoConfigureMockMvc
public class SecurityTesting {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CustomerServices customerServices;

    @MockBean
    private PasswordEncoder passwordEncoder;

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
    public void testOnAuthenticationSuccess_InactiveCustomer() throws Exception {
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
        Mockito.verify(response).encodeRedirectURL(expectedRedirectURL);
    }




    @Test
    @WithMockUser(username = "prasha02")
    public void testRegister() throws Exception {
        // Mock the card security object
        Customer customer = new Customer();
        customer.setUsername("prasha02");
        customer.setPassword("prash321"); // Plain text password

        // Mock the behavior of passwordEncoder
        when(passwordEncoder.encode(customer.getPassword()))
                .thenReturn("$2a$10$scvXI.KiZPNROqCj4rE2pu2yMfVaX85/Sybh9HA1m/3V8D01UyS5K"); // Encoded password

        // Mock the behavior of CustomerServices
        when(customerServices.signingUp(Mockito.any(Customer.class)))
                .thenReturn(customer); // Mocking the registration response

        // Perform the POST request to /profile/register
        mockMvc.perform(post("/profile/register")
                .content(objectMapper.writeValueAsString(customer)))
                .andExpect(status().isForbidden())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.username").value("prasha02"))
                .andExpect(jsonPath("$.password").doesNotExist()); // Ensure password is not returned in the response
    }
}