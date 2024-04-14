package debit.cards.dao.exceptions;

import debit.cards.dao.entities.DebitCard;
import debit.cards.dao.services.DebitCardServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ResourceBundle;

//Debit card Exception handling
public class DebitCardException extends RuntimeException{
    public DebitCardException(String message){
        super(message);
    }

}
