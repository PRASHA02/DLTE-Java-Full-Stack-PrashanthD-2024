package dao.technical.spring.exception;

import java.util.ResourceBundle;

public class UserAlreadyExistException extends RuntimeException{
    static ResourceBundle resourceBundle = ResourceBundle.getBundle("application");

    public UserAlreadyExistException(){
       super(resourceBundle.getString("employee.exists"));
   }
   public UserAlreadyExistException(String message){
        super(message);
   }
}
