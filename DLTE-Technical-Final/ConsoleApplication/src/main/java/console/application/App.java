package console.application;


import backend.exceptions.ConnectionFailureException;
import backend.exceptions.UserAlreadyExistException;
import console.repository.EmployeeRepository;
import console.services.EmployeeServices;
import check.validations.Validation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.*;
//main method which takes the input and displays the output
public class App
{

    static ResourceBundle resourceBundle = ResourceBundle.getBundle("employee");
    static Logger logger = LoggerFactory.getLogger(console.application.App.class);

    static Validation validation = new Validation();
    public static void main( String[] args ) throws SQLException {

        System.out.println(resourceBundle.getString("employee.dashboard"));
        EmployeeRepository employeeRepository = new EmployeeServices();
        while(true){
            Scanner scanner = new Scanner(System.in);
            try{
                System.out.println(resourceBundle.getString("employee.menu"));
                int choice = scanner.nextInt();
//                scanner.nextLine();
                switch(choice){
                    case 1:try{
                        employeeRepository.inputData();//takes the input from the user
                    }catch(UserAlreadyExistException userAlreadyExistException){
                        System.out.println(resourceBundle.getString("employee.exists"));
                    }catch(ConnectionFailureException connectionFailureException){
                        System.out.println(resourceBundle.getString("conn.failure"));
                    }catch(SQLException sqlException){
                        System.out.println(resourceBundle.getString("insert.invalid"));
                    }
                    break;

                    case 2:try{
                        employeeRepository.outputData();//displays input to the user
                    }catch(ConnectionFailureException connectionFailureException){
                        System.out.println(resourceBundle.getString("conn.failure"));
                    }catch(SQLException sqlException){
                        System.out.println(resourceBundle.getString("insert.invalid"));
                    }
                     break;
                    case 3:try{
                        employeeRepository.filter();//filters the permanent codes
                    }catch(ConnectionFailureException connectionFailureException){
                        System.out.println(resourceBundle.getString("conn.failure"));
                    }catch(SQLException sqlException){
                        System.out.println(resourceBundle.getString("insert.invalid"));
                    }
                        break;
                    case 4:
                        System.out.println("Employee Exited!!");
                        return;
                    default:
                        System.out.println("Invalid choice");
                        return;

                }
            }catch (InputMismatchException ex) {
                logger.error("Invalid input!! Try again");
                System.out.println(resourceBundle.getString("wrong.input"));
                scanner.next();
            }
//            scanner.close();
            }



    }




}
