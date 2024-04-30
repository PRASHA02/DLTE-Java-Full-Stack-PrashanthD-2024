package debit.cards.web.mybankdebitcard;

import com.fasterxml.jackson.databind.ObjectMapper;
import debit.cards.dao.entities.DebitCard;
import debit.cards.dao.exceptions.*;
import debit.cards.dao.remotes.DebitCardRepository;
import debit.cards.web.mybankdebitcard.rest.UpdateCardLimit;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.test.web.servlet.MockMvc;


import java.util.Calendar;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
public class UpdateLimitRestTesting {

    @Autowired
    private MockMvc mockMvc;


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

       // mockMvc.perform(put("/update/limit").contentType(MediaType.APPLICATION_JSON).content(request))
              //  .andExpect(status().isOk());


            mockMvc.perform(put("/update/limit")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(request))
                    .andExpect(status().isOk())
                    .andExpect(content().string("Debit card limit updated successfully"));
        }
    //Failure condition because username is not correct
    @Test
    @WithMockUser(username = "prasha122", password = "prash")
    void testUpdateFailure() throws Exception {
        String request = "{\n" +
                "  \"debitCardNumber\": 3692468135796670,\n" +
                "  \"accountNumber\": 78909876543530,\n" +
                "  \"customerId\": 123670,\n" +
                "  \"debitCardCvv\": 123,\n" +
                "  \"debitCardPin\": 1234,\n" +
                "  \"debitCardExpiry\": \"2024-04-03\",\n" +
                "  \"debitCardStatus\": \"active\",\n" +
                "  \"domesticLimit\": 1000,\n" +
                "  \"internationalLimit\": 50\n" +
                "}";

        when(debitCardRepository.updateDebitLimit(any())).thenReturn("Debit card limit updated successfully");

        mockMvc.perform(put("/update/limit").contentType(MediaType.APPLICATION_JSON).content(request))
                .andExpect(status().isOk());

        mockMvc.perform(put("/update/limit")
                .contentType(MediaType.APPLICATION_JSON)
                .content(request))
                .andExpect(status().isOk())
                .andExpect(content().string("Debit card limit updated successfully"));

    }

    @Test
    @WithMockUser(username = "prasha02", password = "prash321")
    void testException() throws Exception {
        String request = "{\n" +
                "  \"debitCardNumber\": 3692468135796670,\n" +
                "  \"accountNumber\": 78909876543530,\n" +
                "  \"customerId\": 123670,\n" +
                "  \"debitCardCvv\": 123,\n" +
                "  \"debitCardPin\": 1234,\n" +
                "  \"debitCardExpiry\": \"2025-04-30\",\n" +
                "  \"debitCardStatus\": \"active\",\n" +
                "  \"domesticLimit\": 1000,\n" +
                "  \"internationalLimit\": 5\n" +
                "}";

        // Mock repository to throw respective exceptions
        when(debitCardRepository.updateDebitLimit(any()))
                .thenThrow(new CustomerException("Customer is not Found"))
                .thenThrow(new AccountException("Account not Found"))
                .thenThrow(new DebitCardException("Debit card limit update failed"))
                .thenThrow(new DebitCardNullException("No Data Found to Perform the operation"))
                .thenThrow(new ValidationException("Invalid request"))
                .thenThrow(new SQLException("Internal Server Error Occurred"));

        mockMvc.perform(put("/update/limit")
                .contentType(MediaType.APPLICATION_JSON)
                .content(request))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Customer is not Found"));

        mockMvc.perform(put("/update/limit")
                .contentType(MediaType.APPLICATION_JSON)
                .content(request))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Account not Found"));

        mockMvc.perform(put("/update/limit")
                .contentType(MediaType.APPLICATION_JSON)
                .content(request))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Debit card limit update failed"));

        mockMvc.perform(put("/update/limit")
                .contentType(MediaType.APPLICATION_JSON)
                .content(request))
                .andExpect(status().isNotFound())
                .andExpect(content().string("No Data Found to Perform the operation"));

        mockMvc.perform(put("/update/limit")
                .contentType(MediaType.APPLICATION_JSON)
                .content(request))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(content().string("Invalid request"));

        mockMvc.perform(put("/update/limit")
                .contentType(MediaType.APPLICATION_JSON)
                .content(request))
                .andExpect(status().isInternalServerError())
                .andExpect(content().string("Internal Server Error Occurred"));
    }

}
