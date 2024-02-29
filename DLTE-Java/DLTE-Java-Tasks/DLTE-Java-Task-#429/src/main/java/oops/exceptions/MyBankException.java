package oops.exceptions;

import java.util.ResourceBundle;

public class MyBankException extends RuntimeException{

    MyBankException(){
        super(ResourceBundle.getBundle("application").getString("exception.wrong"));
    }

}
