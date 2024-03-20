package backend.exceptions;

import entity.backend.Employee;

public class EmployeeException extends RuntimeException{
    public EmployeeException(String message){
        super(message);
    }
}
