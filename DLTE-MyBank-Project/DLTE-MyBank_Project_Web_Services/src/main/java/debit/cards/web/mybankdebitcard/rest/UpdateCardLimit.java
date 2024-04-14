package debit.cards.web.mybankdebitcard.rest;

import debit.cards.dao.entities.DebitCard;
import debit.cards.dao.exceptions.*;
import debit.cards.dao.remotes.DebitCardRepository;
import debit.cards.web.mybankdebitcard.soap.configs.DebitCardPhase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

@RestController
@RequestMapping("/update")
public class UpdateCardLimit {

    @Autowired
    private DebitCardRepository debitCardRepository;

    private static final Logger logger = LoggerFactory.getLogger(UpdateCardLimit.class);
    private static final ResourceBundle resourceBundle = ResourceBundle.getBundle("application");
   //Updating the limit based on account_number
    @PutMapping("/limit")
    public ResponseEntity<String> updateLimit(@Valid @RequestBody DebitCard debitCard) {
        try {
            String response = debitCardRepository.updateDebitLimit(debitCard);
            logger.info(resourceBundle.getString("limit.update.success"));
            return ResponseEntity.ok(response);
        } catch (CustomerException customerException) {
            logger.error(resourceBundle.getString("customer.not.found"));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resourceBundle.getString("customer.not.found"));
        } catch (AccountException accountException) {
            logger.error(resourceBundle.getString("account.not.found"));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resourceBundle.getString("account.not.found"));
        } catch (DebitCardException debitCardException) {
            logger.error(resourceBundle.getString("limit.update.failed"));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resourceBundle.getString("limit.update.failed"));
        } catch (DebitCardNullException debitCardNullException){
            logger.error(resourceBundle.getString("no.data.found"));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resourceBundle.getString("no.data.found"));
        }catch(ValidationException validationException){
            logger.error(resourceBundle.getString("invalid.request"));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(validationException.getMessage());
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
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

}
