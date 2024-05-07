package debit.cards.web.mybankdebitcard.rest;

import debit.cards.dao.entities.DebitCard;
import debit.cards.dao.exceptions.*;
import debit.cards.dao.remotes.DebitCardRepository;

import debit.cards.dao.security.Customer;
import debit.cards.dao.security.CustomerServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.MethodInvocationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

//This service is used for updating the debit card limits only if all the customer,account,debit card status is active otherwise it gives proper error response for us
@RestController
@ComponentScan("debit.cards.dao")
@RequestMapping("/update")
public class UpdateCardLimit {

    @Autowired
    private DebitCardRepository debitCardRepository;
    @Autowired
    private CustomerServices customerServices;


    private static final Logger logger = LoggerFactory.getLogger(UpdateCardLimit.class);
    private static final ResourceBundle resourceBundle = ResourceBundle.getBundle("webservice");
   //Updating the limit based on account_number
   @Operation(summary = "Updating the Debit Card Limit")
   @ApiResponses(value = {
           @ApiResponse(responseCode = "200", description = "Debit card limit updated successfully"),
           @ApiResponse(responseCode = "200", description = "Debit card limit update failed"),
           @ApiResponse(responseCode = "500", description = "Internal server error")
   })
    @PutMapping("/limit")
    public ResponseEntity<String> updateLimit(@Valid @RequestBody DebitCard debitCard) {

        try {
            String response = debitCardRepository.updateDebitLimit(debitCard);
            logger.info(resourceBundle.getString("limit.update.success"));
            return ResponseEntity.ok(response);
        } catch (DebitCardException debitCardException) {
            logger.error(debitCardException.getMessage());
            return ResponseEntity.status(HttpStatus.OK).body(debitCardException.getMessage());
        }
        catch (SQLException exception) {
            logger.error(resourceBundle.getString("internal.error"));
            return ResponseEntity.status(HttpStatus.OK).body(resourceBundle.getString("internal.error"));
        }
    }

    //For Handling Bean Validation Exception
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String,String> handleValidationException(MethodArgumentNotValidException ve){
        Map<String, String> errors = new HashMap<>();
        ve.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName,errorMessage);
        });
        return errors;
    }


}
