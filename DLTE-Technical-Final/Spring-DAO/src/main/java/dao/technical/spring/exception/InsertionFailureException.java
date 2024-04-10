package dao.technical.spring.exception;

public class InsertionFailureException extends RuntimeException{
    public InsertionFailureException(String message){
        super(message);
    }
}
