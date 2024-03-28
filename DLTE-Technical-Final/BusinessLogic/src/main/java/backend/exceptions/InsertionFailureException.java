package backend.exceptions;

import entity.backend.Employee;

public class InsertionFailureException extends RuntimeException{
    public InsertionFailureException(String message){
        super(message);
    }
}
