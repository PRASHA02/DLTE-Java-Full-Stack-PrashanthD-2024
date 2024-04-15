package debit.cards.dao.exceptions;

import java.util.ResourceBundle;

//Card not Available Exception
public class DebitCardNullException extends RuntimeException {
    static ResourceBundle resourceBundle = ResourceBundle.getBundle("application");
    public DebitCardNullException(){
        super(resourceBundle.getString("card.not.available"));

    }
    public DebitCardNullException(String message){
        super(message);

    }

}
