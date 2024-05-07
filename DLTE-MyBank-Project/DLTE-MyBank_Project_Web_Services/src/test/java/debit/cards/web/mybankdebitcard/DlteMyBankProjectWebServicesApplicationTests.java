package debit.cards.web.mybankdebitcard;

import debit.cards.dao.entities.DebitCard;
import debit.cards.dao.exceptions.DebitCardException;
import debit.cards.dao.remotes.DebitCardRepository;
import debit.cards.web.mybankdebitcard.rest.UpdateCardLimit;
import debit.cards.web.mybankdebitcard.soap.configs.DebitCardPhase;
import links.debitcard.ServiceStatus;
import links.debitcard.ViewDebitCardRequest;
import links.debitcard.ViewDebitCardResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataAccessException;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;


import javax.servlet.http.HttpServletResponse;

import java.sql.SQLException;

import java.text.ParseException;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
class DlteMyBankProjectWebServicesApplicationTests {

    @Test
    void contextLoads() {
    }

    @MockBean
    private DebitCardRepository debitCardRepository;
    @InjectMocks
    private DebitCardPhase debitCardPhase;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    @WithMockUser(username = "prasha02")
    public void FetchAllDebitCard() throws SQLException {
        ViewDebitCardRequest viewDebitCardRequest = new ViewDebitCardRequest();
        ServiceStatus expectedServiceStatus = new ServiceStatus();
        expectedServiceStatus.setStatus(HttpServletResponse.SC_OK);

        expectedServiceStatus.setMessage("Debit card information successfully fetched from the database.");

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


        List<DebitCard> debitCardList = Stream.of(
                debitCard, debitCardTwo
        ).collect((Collectors.toList()));
        when(debitCardRepository.getDebitCard("prasha02")).thenReturn(debitCardList);


        // Execute the method under test
        ViewDebitCardResponse response = debitCardPhase.viewDebitCardResponse(viewDebitCardRequest);


        // Assert the response
        assertEquals(HttpServletResponse.SC_OK, response.getServiceStatus().getStatus());
        assertEquals(debitCardList.size(), response.getDebitCard().size());
        assertEquals(expectedServiceStatus.getMessage(), response.getServiceStatus().getMessage());
    }


    @Test
    @WithMockUser(username = "prasha02")
    public void testViewDebitCardException() throws SQLException, DebitCardException {

        when(debitCardRepository.getDebitCard("prasha02")).thenThrow(SQLException.class);


        ViewDebitCardRequest request = new ViewDebitCardRequest();


        ViewDebitCardResponse response = debitCardPhase.viewDebitCardResponse(request);

        assertEquals(HttpServletResponse.SC_OK, response.getServiceStatus().getStatus());
        assertEquals("SQL Syntax is Not proper try to resolve it", response.getServiceStatus().getMessage());
    }


    @Test
    @WithMockUser(username = "prasha02")
    public void FetchAllDebitCardException() throws SQLException {
        ViewDebitCardRequest viewDebitCardRequest = new ViewDebitCardRequest();


        when(debitCardRepository.getDebitCard("prasha02")).thenThrow(DebitCardException.class);

        ViewDebitCardResponse response = debitCardPhase.viewDebitCardResponse(viewDebitCardRequest);


        assertEquals(HttpServletResponse.SC_OK, response.getServiceStatus().getStatus());
        assertEquals("EXC001 :No Debit cards available", response.getServiceStatus().getMessage());
    }



}
