package console.client;

import backend.exceptions.ConnectionFailureException;
import backend.exceptions.UserAlreadyExistException;
import backend.exceptions.ValidationException;
import check.validations.Validation;
import entity.backend.Employee;
import web.EmployeeAddress;
import web.SoapEmployee;
import web.SoapEmployeeService;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Hello world!
 *
 */
public class App 
{
    static ResourceBundle resourceBundle = ResourceBundle.getBundle("employee");
    static Logger logger = LoggerFactory.getLogger(console.application.App.class);
    static Scanner scanner = new Scanner(System.in);
    static Validation validation = new Validation();
    public static void main( String[] args )
    {
        Scanner scanner = new Scanner(System.in);
        SoapEmployeeService soapEmployeeService = new SoapEmployeeService();
        SoapEmployee soapEmployee = soapEmployeeService.getSoapEmployeePort();
        System.out.println("Enter your Choice");
        int choice = scanner.nextInt();
        switch (choice){
            //case 1: Employee employee1 = new Employee("ankitha","k","bhat",444,7896705433L,"ankitha@gmail.com",new entity.console.EmployeeAddress("sri laxmi","moodabidri","mite","karnataka",567876),new entity.console.EmployeeAddress("sri laxmi","moodabidri","mite","karnataka",567876));
            //break;
            case 2:
                List<web.Employee> employeeList = soapEmployee.callDisplayEmployee().getEmployeeList();
                int counter = 0;

                for (web.Employee employee : employeeList) {
                    System.out.println("\nThe Employee " + (counter + 1) + " details are: ");
                    System.out.println("Name: " + employee.getFirstName() + " " + employee.getMiddleName() + " " + employee.getLastName());
                    System.out.println("Employee ID: " + employee.getEmpID());
                    System.out.println("Employee Mobile Number: " + employee.getMobileNumber());
                    System.out.println("Employee Email ID : " + employee.getEmailID());

                    System.out.println("\nEmployee " + (counter + 1) + " Permanent Details ");
                    System.out.println("Employee House Name: " + employee.getPermanentAddress().getHouseName());
                    System.out.println("Employee Street Name: " + employee.getPermanentAddress().getStreetName());
                    System.out.println("Employee City Name: " + employee.getPermanentAddress().getCityName());
                    System.out.println("Employee State Name: " + employee.getPermanentAddress().getStateName());
                    System.out.println("Employee Pin Code :" + employee.getPermanentAddress().getPinCode());

                    System.out.println("\nEmployee " + (counter + 1) + " Temporary Details ");
                    System.out.println("Employee House Name: " + employee.getTemporaryAddress().getHouseName());
                    System.out.println("Employee Street Name: " + employee.getTemporaryAddress().getStreetName());
                    System.out.println("Employee City Name: " + employee.getTemporaryAddress().getCityName());
                    System.out.println("Employee State Name: " + employee.getTemporaryAddress().getStateName());
                    System.out.println("Employee Pin Code :" + employee.getTemporaryAddress().getPinCode()+"\n");
                    counter++;
                }
                break;
            case 3 :

        }
    }



}
