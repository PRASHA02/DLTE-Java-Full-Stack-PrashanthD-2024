package dao.technical.spring.exception;

import java.util.ResourceBundle;

public class ConnectionFailureException extends RuntimeException{
    static ResourceBundle resourceBundle = ResourceBundle.getBundle("application");
    public ConnectionFailureException(String message){
        super(message);
    }
    public ConnectionFailureException(){
        super(resourceBundle.getString("conn.failed"));
    }
}
