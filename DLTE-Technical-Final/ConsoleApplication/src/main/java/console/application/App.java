package console.application;


import Entities.Employee;
import Entities.EmployeeAddress;
import Validation.Validation;

import java.io.StringWriter;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Logger;

public class App
{
    static ResourceBundle resourceBundle = ResourceBundle.getBundle("employee");
    static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    static Scanner scanner = new Scanner(System.in);
    static Validation validation = new Validation();
    public static void main( String[] args ) throws SQLException {

        while(true){
            System.out.println(resourceBundle.getString("employee.dashboard"));
            System.out.println(resourceBundle.getString("employee.menu"));
            int choice = scanner.nextInt();
            String chance;
            scanner.nextLine();
            switch(choice){
                case 1:System.out.println("Enter The Employee Details: ");
                        do{
                        System.out.println(resourceBundle.getString("first.name"));
                        String firstName = scanner.nextLine();
                        System.out.println(resourceBundle.getString("middle.name"));
                        String middleName = scanner.nextLine();
                        System.out.println(resourceBundle.getString("last.name"));
                        String lastName = scanner.nextLine();
                        System.out.println(resourceBundle.getString("emp.id"));
                        Integer employeeId;
                            do {
                                try {
                                    employeeId = scanner.nextInt();
                                    String empId=employeeId.toString();
                                    if(validation.isValidId(empId)){
                                        employeeId = Integer.parseInt(empId);
                                        break;
                                    }
                                    break;
                                } catch (InputMismatchException e) {
                                    System.out.println("Invalid input. Please enter a valid number.");
                                    scanner.next();
                                }
                            } while (true);
                            System.out.println(resourceBundle.getString("mobile.number"));
                        Long mobileNumber;
                            do {
                                try {
                                    mobileNumber = scanner.nextLong();
                                    String mobile = Long.toString(mobileNumber);
                                    if(validation.isValidateMobile(mobile)){
                                        break;
                                    }
                                    else {
                                        System.out.println("Invalid mobile number. Please enter a 10-digit number.");
                                    }
                                } catch (InputMismatchException e) {
                                    System.out.println("Invalid input. Please enter a valid number not String or Special Characters.");
                                    scanner.next();
                                }
                            } while (true);

                        System.out.println(resourceBundle.getString("email.id"));
                        String emailID;
                            do {
                                try {
                                    emailID = scanner.next();
                                    if(validation.validEmailId(emailID)){
                                        break;
                                    }else {
                                        System.out.println("Invalid email ID. Please enter a Valid email ID (prash@gmail.com/prash@gmail.in).");
                                    }
                                } catch (InputMismatchException e) {
                                    System.out.println("Invalid input. Please enter a valid String (prash@gmail.com/prash@gmail.in).");
                                    scanner.next();
                                }
                            } while (true);
                        scanner.nextLine();

                        EmployeeAddress employeePermanentAddress = new EmployeeAddress();
                        System.out.println(resourceBundle.getString("permanent.address"));
                        String permanentHouse = scanner.nextLine();
                        employeePermanentAddress.setHouseName(permanentHouse);
                        System.out.println("Enter the Employee permanent Street Name");
                        String permanentStreet = scanner.nextLine();
                        employeePermanentAddress.setStreetName(permanentStreet);
                        System.out.println("Enter the Employee permanent city Name");
                        String permanentCity = scanner.nextLine();
                        employeePermanentAddress.setCityName(permanentCity);
                        System.out.println("Enter the Employee permanent State Name");
                        String permanentState = scanner.nextLine();
                        employeePermanentAddress.setStateName(permanentState);
                        System.out.println("Enter the Employee permanent Pin Code");
                        Integer permanentPinCode;
                            do {
                                try {
                                    permanentPinCode = scanner.nextInt();
                                    String pincode =permanentPinCode.toString();
                                    if(validation.isValidPin(pincode)){
                                        permanentPinCode = Integer.parseInt(pincode);
                                        break;
                                    }
                                    else {
                                        System.out.println("Invalid pin code. Please enter a 6-digit number.");
                                    }
                                } catch (InputMismatchException e) {
                                    System.out.println("Invalid input. Please enter a valid 6 Digits.");
                                    scanner.next();
                                }
                            } while (true);
                        employeePermanentAddress.setPinCode(permanentPinCode);
                        scanner.nextLine();

                        EmployeeAddress employeeTemporaryAddress = new EmployeeAddress();
                        System.out.println("Enter the Employee Temporary House Name");
                        String temporaryHouse = scanner.nextLine();
                        employeeTemporaryAddress.setHouseName(temporaryHouse);
                        System.out.println("Enter the Employee Temporary Street Name");
                        String temporaryStreet = scanner.nextLine();
                        employeeTemporaryAddress.setStreetName(temporaryStreet);
                        System.out.println("Enter the Employee Temporary city Name");
                        String temporaryCity = scanner.nextLine();
                        employeeTemporaryAddress.setCityName(temporaryCity);
                        System.out.println("Enter the Employee Temporary State Name");
                        String temporaryState = scanner.nextLine();
                        employeeTemporaryAddress.setStateName(temporaryState);
                        System.out.println("Enter the Employee Temporary Pin Code");
                        Integer temporaryPinCode;
                            do {
                                try {
                                    temporaryPinCode = scanner.nextInt();
                                    String pincode = temporaryPinCode.toString();
                                    if(validation.isValidPin(pincode)){
                                        temporaryPinCode = Integer.parseInt(pincode);
                                        break;
                                    }else {
                                        System.out.println("Invalid pin code. Please enter a 6-digit number.");
                                    }

                                } catch (InputMismatchException e) {
                                    System.out.println("Invalid input. Please enter a valid Pin Code of 6 digits.");
                                    scanner.next();
                                }
                            } while (true);
                        employeeTemporaryAddress.setPinCode(temporaryPinCode);

                        Employee employee = new Employee(firstName,middleName,lastName,employeeId,mobileNumber,emailID,employeePermanentAddress,employeeTemporaryAddress);
//                        business.logic.App app1 = new business.logic.App();
                            entity.backend.Employee employeeBackend = new entity.backend.Employee();
                            entity.backend.EmployeeAddress employeeAddressPermanent = new entity.backend.EmployeeAddress();
                            employeeBackend.setFirstName(employee.getFirstName());
                            employeeBackend.setMiddleName(employee.getMiddleName());
                            employeeBackend.setLastName(employee.getLastName());
                            employeeBackend.setEmpID(employee.getEmpID());
                            employeeBackend.setMobileNumber(employee.getMobileNumber());
                            employeeBackend.setEmailID(employee.getEmailID());
                            employeeAddressPermanent.setHouseName(employeePermanentAddress.getHouseName());
                            employeeAddressPermanent.setStreetName(employeePermanentAddress.getStreetName());
                            employeeAddressPermanent.setCityName(employeePermanentAddress.getCityName());
                            employeeAddressPermanent.setStateName(employeePermanentAddress.getStateName());
                            employeeAddressPermanent.setPinCode(employeePermanentAddress.getPinCode());

                        Interface.EmployeeInterface employeeInterface = new business.logic.App();
                        employeeInterface.writeEmployeeDetails((Entities.Employee)employee);
                        System.out.println("Do you want add another Employee");
                        chance = scanner.next();

                    }while (chance.equalsIgnoreCase("yes"));
                    break;


                case 2:business.logic.App app1 = new business.logic.App();
                    List<Employee> employees =  app1.displayEmployeeDetails();
                    int counter = 0;

                    for (Employee employee : employees) {
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
                case 3:
                    System.out.println("Employee Exited!!");
                    return;
                default:
                    System.out.println("Invalid choice");
                    return;

            }
        }

    }


}
