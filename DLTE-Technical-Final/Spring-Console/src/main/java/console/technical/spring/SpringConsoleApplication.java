package console.technical.spring;

import console.technical.spring.repository.EmployeeRepository;
import console.technical.spring.services.EmployeeServices;
import console.technical.spring.validation.Validation;
import dao.technical.spring.SpringDaoApplication;
import dao.technical.spring.exception.ConnectionFailureException;
import dao.technical.spring.exception.UserAlreadyExistException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.InputMismatchException;
import java.util.ResourceBundle;
import java.util.Scanner;

@SpringBootApplication
public class SpringConsoleApplication {
        static ResourceBundle resourceBundle = ResourceBundle.getBundle("application");
        static Logger logger = LoggerFactory.getLogger(console.technical.spring.application.App.class);

        static Validation validation = new Validation();
    public static void main(String[] args) {
        ConfigurableApplicationContext context=  SpringApplication.run(SpringConsoleApplication.class, args);
        console.technical.spring.services.EmployeeServices employeeServices=context.getBean(console.technical.spring.services.EmployeeServices.class);
            System.out.println(resourceBundle.getString("employee.dashboard"));
            while(true){
                Scanner scanner = new Scanner(System.in);
                try{
                    System.out.println(resourceBundle.getString("employee.menu"));
                    int choice = scanner.nextInt();
//                scanner.nextLine();
                    switch(choice){
                        case 1:try{
                            employeeServices.inputData();//takes the input from the user
                        }catch (SQLIntegrityConstraintViolationException sqlException){
                            System.out.println(resourceBundle.getString("db.fail.insert"));
                        }catch(UserAlreadyExistException userAlreadyExistException){
                            System.out.println(resourceBundle.getString("employee.exists"));
                        }catch(ConnectionFailureException connectionFailureException){
                            System.out.println(resourceBundle.getString("conn.failure"));
                        }catch(SQLException sqlException){
                            System.out.println(resourceBundle.getString("insert.invalid"));
                        }
                            break;

                        case 2:try{
                            employeeServices.outputData();//displays input to the user
                        }catch(ConnectionFailureException connectionFailureException){
                            System.out.println(resourceBundle.getString("conn.failure"));
                        }catch(SQLException sqlException){
                            System.out.println(resourceBundle.getString("insert.invalid"));
                        }
                            break;
                        case 3:try{
                            employeeServices.filter();//filters the permanent codes
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

