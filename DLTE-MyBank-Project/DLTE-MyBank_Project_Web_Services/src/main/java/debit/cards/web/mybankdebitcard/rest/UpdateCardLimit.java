package debit.cards.web.mybankdebitcard.rest;

import debit.cards.dao.entities.Customer;
import debit.cards.dao.entities.DebitCard;
import debit.cards.dao.exceptions.*;
import debit.cards.dao.remotes.DebitCardRepository;
import debit.cards.dao.security.CardSecurity;
import debit.cards.dao.security.CardSecurityServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/update")
public class UpdateCardLimit {

    @Autowired
    private DebitCardRepository debitCardRepository;
    @Autowired
    private CardSecurityServices cardSecurityServices;

    private static final Logger logger = LoggerFactory.getLogger(UpdateCardLimit.class);
    private static final ResourceBundle resourceBundle = ResourceBundle.getBundle("card");
   //Updating the limit based on account_number
   @Operation(summary = "Updating the Debit Card Limit")
   @ApiResponses(value = {
           @ApiResponse(responseCode = "200", description = "Debit card limit updated successfully"),
           @ApiResponse(responseCode = "400", description = "Debit card limit update failed"),
           @ApiResponse(responseCode = "404", description = "Customer is not Active or No account Number found or Account is not Active"),
           @ApiResponse(responseCode = "422", description = " Please provide the correct debit card details"),
           @ApiResponse(responseCode = "500", description = "Internal server error")
   })
    @PutMapping("/limit")
    public ResponseEntity<String> updateLimit(@Valid @RequestBody DebitCard debitCard) {
       Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
       String username = authentication.getName();

       // method to fetch the owner's username from the account object
       String accountOwnerUsername = cardSecurityServices.getAccountOwnerUsername(debitCard.getAccountNumber());

       // Check if the authenticated user matches the owner of the account
       if (!username.equals(accountOwnerUsername))
           return ResponseEntity.status(HttpStatus.FORBIDDEN).body(resourceBundle.getString("access.denied"));

        try {
            CardSecurity customer = cardSecurityServices.findByUserName(username);
            debitCard.setCustomerId(customer.getCustomerId());
            String response = debitCardRepository.updateDebitLimit(debitCard);
            logger.info(resourceBundle.getString("limit.update.success"));
            return ResponseEntity.ok(response);
        } catch (CustomerException customerException) {
            logger.error(resourceBundle.getString("customer.not.found"));
            return ResponseEntity.status(HttpStatus.OK).body(resourceBundle.getString("customer.not.found"));
        } catch (AccountException accountException) {
            logger.error(resourceBundle.getString("account.not.found"));
            return ResponseEntity.status(HttpStatus.OK).body(resourceBundle.getString("account.not.found"));
        } catch (DebitCardException debitCardException) {
            logger.error(resourceBundle.getString("limit.update.failed"));
            return ResponseEntity.status(HttpStatus.OK).body(resourceBundle.getString("limit.update.failed"));
        } catch (DebitCardNullException debitCardNullException){
            logger.error(resourceBundle.getString("no.data.found"));
            return ResponseEntity.status(HttpStatus.OK).body(resourceBundle.getString("no.data.found"));
        }catch(ValidationException validationException){
            logger.error(resourceBundle.getString("invalid.request"));
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(validationException.getMessage());
        }
        catch (SQLException exception) {
            logger.error(resourceBundle.getString("internal.error"));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resourceBundle.getString("internal.error"));
        }
    }

    //For Handling Bean Validation Exception
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String,String> handleValidationException(MethodArgumentNotValidException ve){
        Map<String, String> errors = new HashMap<>();
        ve.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
//           if(fieldName.equals("debitCardNumber")){
//               errors.put("PRA001", errorMessage);
//           }
//           if(fieldName.equals("debitCardCvv")){
//               errors.put("PRA002",errorMessage);
//           }
           errors.put(fieldName,errorMessage);
        });
        return errors;
    }

}
