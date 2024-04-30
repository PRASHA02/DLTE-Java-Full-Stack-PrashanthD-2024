package debit.cards.web.mybankdebitcard;

import debit.cards.dao.entities.DebitCard;
import debit.cards.dao.exceptions.DebitCardException;
import debit.cards.dao.exceptions.DebitCardNullException;
import debit.cards.dao.remotes.DebitCardRepository;
import debit.cards.web.mybankdebitcard.soap.configs.DebitCardPhase;
import links.debitcard.ServiceStatus;
import links.debitcard.ViewDebitCardRequest;
import links.debitcard.ViewDebitCardResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletResponse;
import javax.swing.text.View;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class DlteMyBankProjectWebServicesApplicationTests {

    @MockBean
    private DebitCardRepository debitCardRepository;
    @InjectMocks
    private DebitCardPhase debitCardPhase;

    @Test
    public void FetchAllDebitCard() throws SQLException {
        Authentication authentication = Mockito.mock(Authentication.class);
        Mockito.when(authentication.getName()).thenReturn("prasha02");
        ViewDebitCardRequest viewDebitCardRequest = new ViewDebitCardRequest();

        List<DebitCard> debitCardList = new ArrayList<>();


        DebitCard debitCard= new DebitCard(1234567890981234L,78903456789123L,200005,111,1234,new Date(2024,04,4), "active", 2000.0,50000.0);
        DebitCard debitCard1 = new DebitCard(7837645907637746L,35467956789123L,123658,234,2323,new Date(2024,04,9), "inactive", 4000.0,70000.0);
        DebitCard debitCard2 = new DebitCard(1234567890123456L, 78901234567890L, 300007, 555, 9876, new Date(2024, 4, 14), "active", 3000.0, 60000.0);
        DebitCard debitCard3 = new DebitCard(9876543210987654L, 65432109876543L, 400009, 777, 5432, new Date(2024, 4, 19), "blocked", 5000.0, 80000.0);
        //Add some dummy data into the arrayList for testing
        debitCardList = Stream.of(debitCard,debitCard1,debitCard2,debitCard3).collect(Collectors.toList());

        when(debitCardRepository.getDebitCard("prasha02")).thenReturn(debitCardList);
        // Execute the method under test
        ViewDebitCardResponse response = debitCardPhase.viewDebitCardResponse(viewDebitCardRequest);

        // Assert the response
        assertEquals(HttpServletResponse.SC_OK, response.getServiceStatus().getStatus());

        assertEquals(debitCardList.size(),response.getDebitCard().size());
    }

    @Test
    void testAllDebitCards_failure() throws SQLException, DebitCardException {
        ViewDebitCardRequest viewDebitCardRequest = new ViewDebitCardRequest();

        List<DebitCard> debitCardList = new ArrayList<>();


        DebitCard debitCard= new DebitCard(1234567890981234L,78903456789123L,200005,111,1234,new Date(2024,04,4), "active", 2000.0,50000.0);
        DebitCard debitCard1 = new DebitCard(7837645907637746L,35467956789123L,123658,234,2323,new Date(2024,04,9), "inactive", 4000.0,70000.0);
        DebitCard debitCard2 = new DebitCard(1234567890123456L, 78901234567890L, 300007, 555, 9876, new Date(2024, 4, 14), "active", 3000.0, 60000.0);
        DebitCard debitCard3 = new DebitCard(9876543210987654L, 65432109876543L, 400009, 777, 5432, new Date(2024, 4, 19), "blocked", 5000.0, 80000.0);
        //Add some dummy data into the arrayList for testing
        debitCardList = Stream.of(debitCard,debitCard1,debitCard2,debitCard3).collect(Collectors.toList());

        when(debitCardRepository.getDebitCard("prasha02")).thenReturn(debitCardList);
        // Execute the method under test
        ViewDebitCardResponse response = debitCardPhase.viewDebitCardResponse(viewDebitCardRequest);
        assertEquals(HttpServletResponse.SC_NOT_FOUND, response.getServiceStatus().getStatus()); //FAIL
    }

    @Test
    public void FetchAllDebitCard_DebitCardException() throws SQLException {
        // Create a new ViewDebitCardRequest object
        ViewDebitCardRequest viewDebitCardRequest = new ViewDebitCardRequest();

        // Mock the behavior of the debitCardRepository.getDebitCard() method to throw DebitCardException
        when(debitCardRepository.getDebitCard("prasha02")).thenThrow(DebitCardException.class);

        // Execute the method under test
        ViewDebitCardResponse response = debitCardPhase.viewDebitCardResponse(viewDebitCardRequest);

        // Assert the response
        assertEquals(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, response.getServiceStatus().getStatus());
        assertEquals("SQL Syntax is Not proper try to resolve it", response.getServiceStatus().getMessage());
    }



    @Test
    public void FetchAllDebitCard_DebitCardNullException() throws SQLException {
        // Create a new ViewDebitCardRequest object
        ViewDebitCardRequest viewDebitCardRequest = new ViewDebitCardRequest();

        // Mock the behavior of the debitCardRepository.getDebitCard() method to throw DebitCardException
        when(debitCardRepository.getDebitCard("prasha02")).thenThrow(DebitCardNullException.class);

        // Execute the method under test
        ViewDebitCardResponse response = debitCardPhase.viewDebitCardResponse(viewDebitCardRequest);

        // Assert the response
        assertEquals(HttpServletResponse.SC_NO_CONTENT, response.getServiceStatus().getStatus());
        assertEquals("No Debit cards available", response.getServiceStatus().getMessage());
    }





}
