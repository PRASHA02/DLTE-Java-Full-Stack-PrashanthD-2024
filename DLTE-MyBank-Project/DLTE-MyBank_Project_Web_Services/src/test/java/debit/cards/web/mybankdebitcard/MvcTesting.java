package debit.cards.web.mybankdebitcard;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.authentication;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import debit.cards.dao.remotes.DebitCardRepository;
import debit.cards.dao.security.CardSecurity;
import debit.cards.dao.security.CardSecurityServices;
import debit.cards.web.mybankdebitcard.mvc.MvcController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.net.URLEncoder;


@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
public class MvcTesting {
    @InjectMocks
    private MvcController mvcController;
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CardSecurityServices cardSecurityServices;

    @Mock
    private Authentication authentication;

    @Test
    @WithMockUser(username = "prasha02")
    public void testIndex() {
        String indexName = mvcController.index();
        assertEquals("index", indexName);
    }
     @Test
    @WithMockUser(username = "prasha02")
    public void testDashboard() {
        String dashboardName = mvcController.dashboard();
        assertEquals("dashboard", dashboardName);
    }

    @Test
    @WithMockUser(username = "prasha02")
    public void testAccountView() {
        String viewName = mvcController.accountView();
        assertEquals("account", viewName);
    }

    @Test
    @WithMockUser(username = "prasha02")
    public void testDebitCardDetails() {
        String viewName = mvcController.debitCardDetails();
        assertEquals("view", viewName);
    }

    @Test
    @WithMockUser(username = "prasha02")
    public void testUpdateCardLimit() {
        String updateName = mvcController.updateCardLimit();
        assertEquals("update", updateName);
    }

    @Test
    @WithMockUser(username = "prasha02")
    public void testError() {
        String error = mvcController.error();
        assertEquals("error", error);
    }


    @Test
    @WithMockUser(username = "prasha02")
    public void testHandleError_NotFound() throws Exception {
        String expectedErrorMessage = "Requested page is not available, Page Under Construction";
        int expectedErrorCode = 404;

        mockMvc.perform(get("/error").requestAttr("javax.servlet.error.status_code", expectedErrorCode))
                .andExpect(status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("../error?code=" + expectedErrorCode + "&message=" + URLEncoder.encode(expectedErrorMessage, "UTF-8")));
    }

    @Test
    @WithMockUser(username = "prasha02")
    public void testHandleError_InternalServerError() throws Exception {
        String expectedErrorMessage = "Internal Server Error !! Reload the Page";
        int expectedErrorCode = 500;

        mockMvc.perform(get("/error").requestAttr("javax.servlet.error.status_code", expectedErrorCode))
                .andExpect(status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("../error?code=" + expectedErrorCode + "&message=" + URLEncoder.encode(expectedErrorMessage, "UTF-8")));
    }
    @Test
    @WithMockUser(username = "prasha02")
    public void testCustomerName() throws Exception {
        CardSecurity customer = new CardSecurity();
        customer.setCustomerName("");
        lenient().when(cardSecurityServices.findByUserName("prasha02")).thenReturn(customer);

        mockMvc.perform(MockMvcRequestBuilders.get("/name"))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().string(""));
    }

    @Test
    @WithMockUser(username = "prasha02")
    public void testCustomername() throws Exception {
        // Mock the authentication object
        String userName = "prasha02";

        // Mock the SecurityContext and Authentication
        Authentication authentication = mock(Authentication.class);
        SecurityContext securityContext = mock(SecurityContext.class);
        lenient().when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
        lenient().when(authentication.getName()).thenReturn(userName);

        // Mock the cardSecurityServices
        String expectedCustomerName = "";
        CardSecurity cardSecurity = new CardSecurity();
        cardSecurity.setCustomerName(expectedCustomerName);
        lenient().when(cardSecurityServices.findByUserName(userName)).thenReturn(cardSecurity);

        // Perform GET request and verify response
        mockMvc.perform(get("/name"))
                .andExpect(status().isNotFound())
                .andExpect(content().string(expectedCustomerName));
    }

    @Test
    @WithMockUser(username = "prasha02")
    public void testCustomername_Exception() {
        // Mock authentication
        Authentication authentication = new TestingAuthenticationToken("user", "password");

        // Set the authentication in SecurityContextHolder
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Mock cardSecurityServices
        CardSecurityServices cardSecurityServices = mock(CardSecurityServices.class);
        lenient().when(cardSecurityServices.findByUserName(anyString())).thenThrow(new RuntimeException("Simulated exception"));
    }


}