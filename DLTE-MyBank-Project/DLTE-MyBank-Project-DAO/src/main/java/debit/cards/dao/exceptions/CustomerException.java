package debit.cards.dao.exceptions;

import debit.cards.dao.entities.Customer;

//Customer Exception id no active Customer Found
public class CustomerException extends RuntimeException {

    public CustomerException(String message){
        super(message);
    }
}
