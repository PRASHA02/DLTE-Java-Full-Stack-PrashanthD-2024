package console.application;


import console.repository.EmployeeRepository;
import console.services.EmployeeServices;
import check.validations.Validation;

import dao.technical.spring.exception.ConnectionFailureException;
import dao.technical.spring.exception.InsertionFailureException;
import dao.technical.spring.exception.UserAlreadyExistException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.*;
//main method which takes the input and displays the output
public class App
{

    static ResourceBundle resourceBundle = ResourceBundle.getBundle("employee");
    static Logger logger = LoggerFactory.getLogger(console.application.App.class);

    public static void main( String[] args ) throws SQLException {

        System.out.println(resourceBundle.getString("employee.dashboard"));
        EmployeeRepository employeeRepository = new EmployeeServices();

        System.out.println(resourceBundle.getString("employee.dashboard"));
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
                        logger.error(resourceBundle.getString("db.fail.insert"));
                        throw new UserAlreadyExistException(resourceBundle.getString("employee.exists"));
                    }catch(InsertionFailureException exception){
                        logger.error(resourceBundle.getString("db.push.fail"));
                        throw new InsertionFailureException(resourceBundle.getString("db.push.fail"));
                    }
                    catch(ConnectionFailureException | SQLException connectionFailureException){
                        logger.warn(resourceBundle.getString("db.syntax.fail"));
                        throw new ConnectionFailureException(resourceBundle.getString("conn.failure"));
                    }
                        break;

                    case 2:try{
                        employeeRepository.outputData();//displays input to the user
                    }catch(ConnectionFailureException | SQLException connectionFailureException){
                        logger.error(resourceBundle.getString("conn.failure"));
                        throw new ConnectionFailureException(resourceBundle.getString("conn.failure"));
                    } catch (IOException e) {
                        logger.error(resourceBundle.getString("conn.failure"));
                       throw new ConnectionFailureException(resourceBundle.getString("conn.failure"));
                    }
                        break;
                    case 3:try{
                        employeeRepository.filter();//filters the permanent codes
                    }catch(ConnectionFailureException connectionFailureException){
                        throw new ConnectionFailureException(resourceBundle.getString("conn.failure"));
                    }catch(SQLException sqlException){
                        throw new SQLException(resourceBundle.getString("insert.invalid"));
                    }
                        break;
                    case 4:
                        System.out.println(resourceBundle.getString("employee.exit"));
                        return;
                    default:
                        System.out.println(resourceBundle.getString("invalid.choice"));
                        return;

                }
            }catch (InputMismatchException ex) {
                logger.warn(resourceBundle.getString("invalid.input"));
                System.out.println(resourceBundle.getString("wrong.input"));
                scanner.next();
            }
//            scanner.close();
        }



    }




}
