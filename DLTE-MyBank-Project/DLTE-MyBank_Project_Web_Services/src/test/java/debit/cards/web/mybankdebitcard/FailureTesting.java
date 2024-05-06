package debit.cards.web.mybankdebitcard;

import debit.cards.dao.security.CardSecurity;
import debit.cards.dao.security.CardSecurityServices;
import debit.cards.web.mybankdebitcard.security.CardFailureHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.test.context.support.WithMockUser;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
class FailureTesting {
    @Mock
    private CardSecurityServices cardSecurityServices;

    @InjectMocks
    private CardFailureHandler cardFailureHandler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @WithMockUser(username = "prasha02")
    public void testOnAuthenticationFailure_ValidUsername() throws Exception {
        // Mock HttpServletRequest, HttpServletResponse, CardSecurityServices, and ResourceBundle
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        CardSecurityServices cardSecurityServices = mock(CardSecurityServices.class);
        ResourceBundle resourceBundle = mock(ResourceBundle.class);

        // Mock values
        when(request.getParameter("username")).thenReturn("validUsername");
        CardSecurity cardSecurity = new CardSecurity();
        cardSecurity.setCustomerStatus("active");
        when(cardSecurityServices.findByUserName("validUsername")).thenReturn(cardSecurity);
        when(resourceBundle.getString(anyString())).thenReturn(""); // Mock resource bundle strings

        // Create instance of CardFailureHandler
        CardFailureHandler failureHandler = new CardFailureHandler();


        // Call the method under test
        AuthenticationException exception = assertThrows(LockedException.class, () -> {
            failureHandler.onAuthenticationFailure(request, response, new AuthenticationException("") {});
        });

        // Verify expected behavior
        verify(cardSecurityServices, times(1)).updateAttempts(cardSecurity);
        verify(resourceBundle, times(1)).getString("invalid.attempts");
        // Add more verifications as needed
    }
}
