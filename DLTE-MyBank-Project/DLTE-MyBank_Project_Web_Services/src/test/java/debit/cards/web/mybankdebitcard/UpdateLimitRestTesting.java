package debit.cards.web.mybankdebitcard;

import com.fasterxml.jackson.databind.ObjectMapper;
import debit.cards.dao.entities.DebitCard;
import debit.cards.dao.exceptions.*;
import debit.cards.dao.remotes.DebitCardRepository;

import debit.cards.dao.security.Customer;
import debit.cards.dao.security.CustomerServices;
import debit.cards.web.mybankdebitcard.rest.UpdateCardLimit;
import com.jayway.jsonpath.JsonPath;
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
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
public class UpdateLimitRestTesting {

    @Test
    void contextLoads() {
    }

    public static  ResourceBundle resourceBundle;

    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private CustomerServices customerServices;

    @Mock
    private BindingResult bindingResult;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Mock
    private MethodArgumentNotValidException methodArgumentNotValidException;


    @MockBean
    private DebitCardRepository debitCardRepository;
    @InjectMocks
    private UpdateCardLimit updateCardLimit;


    @Test
    @WithMockUser(username = "prasha02")
    void testUpdateSuccess() throws Exception {
        String request = "{\n" +
                "  \"debitCardNumber\": 3692468135796670,\n" +
                "  \"accountNumber\": 78909876543530,\n" +
                "  \"customerId\": 123670,\n" +
                "  \"debitCardCvv\": 123,\n" +
                "  \"debitCardPin\": 1234,\n" +
                "  \"debitCardExpiry\": \"2025-04-30\",\n" +
                "  \"debitCardStatus\": \"active\",\n" +
                "  \"domesticLimit\": 1000,\n" +
                "  \"internationalLimit\": 5000\n" +
                "}";

        when(debitCardRepository.updateDebitLimit(any())).thenReturn("Debit card limit updated successfully");

        mockMvc.perform(put("/update/limit")
                .contentType(MediaType.APPLICATION_JSON)
                .content(request))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "prasha02")
    void testUpdateFailure() throws Exception {
        String request = "{\n" +
                "  \"debitCardNumber\": 3692468135796670,\n" +
                "  \"accountNumber\": 78909876543530,\n" +
                "  \"customerId\": 123670,\n" +
                "  \"debitCardCvv\": 123,\n" +
                "  \"debitCardPin\": 1234,\n" +
                "  \"debitCardExpiry\": \"2024-05-30\",\n" +
                "  \"debitCardStatus\": \"active\",\n" +
                "  \"domesticLimit\": 1000,\n" +
                "  \"internationalLimit\": 5000\n" +
                "}";

        when(debitCardRepository.updateDebitLimit(any())).thenReturn("Debit card limit updated successfully");

        mockMvc.perform(put("/update/limit")
                .contentType(MediaType.APPLICATION_JSON)
                .content(request))
                .andExpect(status().isOk())
                .andExpect(content().string("Debit card limit updated successfully"));
    }


    @Test
    @WithMockUser(username = "prasha02", password = "prash321")
    void testDebitCardException() throws Exception {
        String request = "{\n" +
                "  \"debitCardNumber\": 3692468135796670,\n" +
                "  \"accountNumber\": 78909876543530,\n" +
                "  \"customerId\": 123670,\n" +
                "  \"debitCardCvv\": 123,\n" +
                "  \"debitCardPin\": 1234,\n" +
                "  \"debitCardExpiry\": \"2025-04-30\",\n" +
                "  \"debitCardStatus\": \"active\",\n" +
                "  \"domesticLimit\": 1000,\n" +
                "  \"internationalLimit\": 5000\n" +
                "}";

        when(debitCardRepository.updateDebitLimit(any()))
                .thenThrow(new DebitCardException("Debit card limit update failed"));

        mockMvc.perform(put("/update/limit")
                .contentType(MediaType.APPLICATION_JSON)
                .content(request))
                .andExpect(status().isOk())
                .andExpect(content().string("Debit card limit update failed"));
    }

    @Test
    @WithMockUser(username = "prasha02", password = "prash321")
    void testSQLException() throws Exception {
        String request = "{\n" +
                "  \"debitCardNumber\": 3692468135796670,\n" +
                "  \"accountNumber\": 78909876543530,\n" +
                "  \"customerId\": 123670,\n" +
                "  \"debitCardCvv\": 123,\n" +
                "  \"debitCardPin\": 1234,\n" +
                "  \"debitCardExpiry\": \"2025-04-30\",\n" +
                "  \"debitCardStatus\": \"active\",\n" +
                "  \"domesticLimit\": 1000,\n" +
                "  \"internationalLimit\": 50000\n" +
                "}";

        when(debitCardRepository.updateDebitLimit(any()))
                .thenThrow(new SQLException("Internal Server Error Occurred"));

        mockMvc.perform(put("/update/limit")
                .contentType(MediaType.APPLICATION_JSON)
                .content(request))
                .andExpect(status().isOk())
                .andExpect(content().string("Internal Server Error Occurred"));
    }


    @Test
    @WithMockUser(username = "prasha02", password = "prash321")
    void testHandleValidationException() throws Exception {
        // Create a request with invalid data
        String request = "{\n" +
                "  \"debitCardNumber\": 3692468135796670876545,\n" +
                "  \"accountNumber\": 789098765435308767,\n" +
                "  \"customerId\": 123670,\n" +
                "  \"debitCardCvv\": 123,\n" +
                "  \"debitCardPin\": 1234,\n" +
                "  \"debitCardExpiry\": \"2025-04-30\",\n" +
                "  \"debitCardStatus\": \"active\",\n" +
                "  \"domesticLimit\": 1000,\n" +
                "  \"internationalLimit\": 50000\n" +
                "}";

        // Perform the request
        mockMvc.perform(put("/update/limit")
                .contentType(MediaType.APPLICATION_JSON)
                .content(request))
                .andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser(username = "prasha02", password = "prash321")
    void testHandleBeanValidationException() throws Exception {
        // Create a request with invalid data
        String request = "{\n" +
                "  \"debitCardNumber\": null,\n" +
                "  \"accountNumber\": null,\n" +
                "  \"customerId\": null,\n" +
                "  \"debitCardCvv\": null,\n" +
                "  \"debitCardPin\": null,\n" +
                "  \"debitCardExpiry\": \"2025-04-30\",\n" +
                "  \"debitCardStatus\": \"null\",\n" +
                "  \"domesticLimit\": null,\n" +
                "  \"internationalLimit\": null\n" +
                "}";

        // Perform the request
        mockMvc.perform(put("/update/limit")
                .contentType(MediaType.APPLICATION_JSON)
                .content(request))
                .andExpect(status().isOk());
    }





}
