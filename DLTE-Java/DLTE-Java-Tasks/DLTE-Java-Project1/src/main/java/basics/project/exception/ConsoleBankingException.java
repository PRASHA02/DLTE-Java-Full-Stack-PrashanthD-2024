package basics.project.exception;

import java.util.ResourceBundle;

public class ConsoleBankingException extends RuntimeException {

    ConsoleBankingException(){
        super(ResourceBundle.getBundle("application").getString("bank.exception"));
    }
    ConsoleBankingException(String message){
        super(ResourceBundle.getBundle("application").getString("bank.exception")+ " "+ message);
    }
}
