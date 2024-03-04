package oops.exceptions;

import java.util.ResourceBundle;

public class MyBankException extends RuntimeException{
    public MyBankException() {
    }

    public MyBankException(String message){
        super(ResourceBundle.getBundle("application").getString(message));
    }

}
