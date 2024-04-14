package debit.cards.dao.exceptions;

//Account Exception id no active Account Found
public class AccountException extends RuntimeException {


    public AccountException(String message){
        super(message);
    }
}
