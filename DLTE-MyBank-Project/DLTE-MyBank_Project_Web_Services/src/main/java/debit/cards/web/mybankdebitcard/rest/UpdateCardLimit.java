package debit.cards.web.mybankdebitcard.rest;

import debit.cards.dao.entities.DebitCard;
import debit.cards.dao.exceptions.AccountException;
import debit.cards.dao.exceptions.CustomerException;
import debit.cards.dao.exceptions.DebitCardException;
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

    @PutMapping
    public ResponseEntity<String> updateLimit(@Valid @RequestBody DebitCard debitCard) {
        try {
            String response = debitCardRepository.updateDebitLimit(debitCard);
            return ResponseEntity.ok(response);
        } catch (CustomerException e) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(e.getMessage());
        } catch (AccountException e) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(e.getMessage());
        } catch (DebitCardException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Exception occurred");
        }
    }


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
