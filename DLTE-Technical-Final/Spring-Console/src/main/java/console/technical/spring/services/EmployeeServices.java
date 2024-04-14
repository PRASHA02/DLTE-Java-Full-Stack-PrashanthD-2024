package console.technical.spring.services;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import console.technical.spring.entity.Employee;
import console.technical.spring.entity.EmployeeAddress;
import console.technical.spring.remotes.EmployeeRepository;
import console.technical.spring.validation.Validation;
import dao.technical.spring.exception.ConnectionFailureException;
import dao.technical.spring.exception.UserAlreadyExistException;
import dao.technical.spring.exception.ValidationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.SQLException;
import java.util.*;

@Service
public class EmployeeServices implements EmployeeRepository {
    @Autowired
    Validation validation;
    static ResourceBundle resourceBundle = ResourceBundle.getBundle("application");
    static Logger logger = LoggerFactory.getLogger(console.technical.spring.SpringConsoleApplication.class);
    static Scanner scanner = new Scanner(System.in);
    static HttpURLConnection con;
    //takes the user data
    @Override
    public void inputData() throws SQLException, UserAlreadyExistException, ConnectionFailureException {
        String chance;
        System.out.println(resourceBundle.getString("employee.name"));
        do{
            try{
                System.out.println(resourceBundle.getString("first.name"));
                String firstName = validation.validateName();
                System.out.println(resourceBundle.getString("middle.name"));
                String middleName = validation.validateName();
                System.out.println(resourceBundle.getString("last.name"));
                String lastName = validation.validateName();
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
                        logger.warn(resourceBundle.getString("empId.invalid"));
                        System.out.println(resourceBundle.getString("empId.invalid"));
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
                            System.out.println(resourceBundle.getString("mobile.invalid"));
                        }
                    } catch (InputMismatchException e) {
                        logger.warn(resourceBundle.getString("mobile.mismatch"));
                        System.out.println(resourceBundle.getString("mobile.mismatch"));
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
                            System.out.println(resourceBundle.getString("email.invalid"));
                        }
                    } catch (InputMismatchException e) {
                        logger.warn(resourceBundle.getString("email.mismatch"));
                        System.out.println(resourceBundle.getString("email.mismatch"));
                        scanner.next();
                    }
                } while (true);
                scanner.nextLine();

                EmployeeAddress employeePermanentAddress = new EmployeeAddress();
                System.out.println(resourceBundle.getString("permanent.house"));
                String permanentHouse = scanner.nextLine();
                employeePermanentAddress.setHouseName(permanentHouse);
                System.out.println(resourceBundle.getString("permanent.street"));
                String permanentStreet = scanner.nextLine();
                employeePermanentAddress.setStreetName(permanentStreet);
                System.out.println(resourceBundle.getString("permanent.city"));
                String permanentCity = scanner.nextLine();
                employeePermanentAddress.setCityName(permanentCity);
                System.out.println(resourceBundle.getString("permanent.state"));
                String permanentState = scanner.nextLine();
                employeePermanentAddress.setStateName(permanentState);
                System.out.println(resourceBundle.getString("permanent.pinCode"));
                Integer permanentPinCode;
                do {
                    try {
                        permanentPinCode = scanner.nextInt();
                        String pincode = permanentPinCode.toString();
                        if(validation.isValidPin(pincode)){
                            permanentPinCode = Integer.parseInt(pincode);
                            break;
                        }
                        else {
                            System.out.println(resourceBundle.getString("pinCode.invalid"));
                        }
                    } catch (InputMismatchException e) {
                        logger.warn(resourceBundle.getString("pinCode.mismatch"));
                        System.out.println(resourceBundle.getString("pinCode.mismatch"));
                        scanner.next();
                    }
                } while (true);
                employeePermanentAddress.setPinCode(permanentPinCode);
                employeePermanentAddress.setEmpId(employeeId);
                employeePermanentAddress.setFlag("permanent");
                scanner.nextLine();




                EmployeeAddress employeeTemporaryAddress = new EmployeeAddress();
                System.out.println(resourceBundle.getString("temporary.house"));
                String temporaryHouse = scanner.nextLine();
                employeeTemporaryAddress.setHouseName(temporaryHouse);
                System.out.println(resourceBundle.getString("temporary.street"));
                String temporaryStreet = scanner.nextLine();
                employeeTemporaryAddress.setStreetName(temporaryStreet);
                System.out.println(resourceBundle.getString("temporary.city"));
                String temporaryCity = scanner.nextLine();
                employeeTemporaryAddress.setCityName(temporaryCity);
                System.out.println(resourceBundle.getString("temporary.state"));
                String temporaryState = scanner.nextLine();
                employeeTemporaryAddress.setStateName(temporaryState);
                System.out.println(resourceBundle.getString("temporary.pinCode"));
                Integer temporaryPinCode;
                do {
                    try {
                        temporaryPinCode = scanner.nextInt();
                        String pincode = temporaryPinCode.toString();
                        if(validation.isValidPin(pincode)){
                            temporaryPinCode = Integer.parseInt(pincode);
                            break;
                        }else {
                            System.out.println(resourceBundle.getString("pinCode.invalid"));
                        }

                    } catch (InputMismatchException e) {
                        logger.warn(resourceBundle.getString("pinCode.mismatch"));
                        System.out.println(resourceBundle.getString("pinCode.mismatch"));
                        scanner.next();
                    }
                } while (true);
                employeeTemporaryAddress.setPinCode(temporaryPinCode);
                scanner.nextLine();
                employeeTemporaryAddress.setEmpId(employeeId);
                employeeTemporaryAddress.setFlag("temporary");

                Employee employee = new Employee(firstName,middleName,lastName,employeeId,mobileNumber,emailID,employeePermanentAddress,employeeTemporaryAddress);
                //dao.technical.spring.services.EmployeeServices employeeServices = new dao.technical.spring.services.EmployeeServices();

                dao.technical.spring.entity.Employee employeeBackend = new dao.technical.spring.entity.Employee();
                dao.technical.spring.entity.EmployeeAddress employeeAddressPermanent = new dao.technical.spring.entity.EmployeeAddress();
                dao.technical.spring.entity.EmployeeAddress employeeAddressTemporary = new dao.technical.spring.entity.EmployeeAddress();

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
                employeeAddressPermanent.setEmpID(employeePermanentAddress.getEmpId());
                employeeAddressPermanent.setFlag(employeePermanentAddress.getFlag());
                employeeBackend.setPermanentAddress(employeeAddressPermanent);

                employeeAddressTemporary.setHouseName(employeeTemporaryAddress.getHouseName());
                employeeAddressTemporary.setStreetName(employeeTemporaryAddress.getStreetName());
                employeeAddressTemporary.setCityName(employeeTemporaryAddress.getCityName());
                employeeAddressTemporary.setStateName(employeeTemporaryAddress.getStateName());
                employeeAddressTemporary.setPinCode(employeeTemporaryAddress.getPinCode());
                employeeAddressTemporary.setEmpID(employeeTemporaryAddress.getEmpId());
                employeeAddressTemporary.setFlag(employeeTemporaryAddress.getFlag());
                employeeBackend.setTemporaryAddress(employeeAddressTemporary);

//                entity.backend.method.EmployeeInterface employeeInterface = new business.logic.App();
                try {
                    URL url = new URL(resourceBundle.getString("web.services.write"));
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    con.setRequestMethod("POST");
                    con.setRequestProperty("Content-Type", "application/json");
                    con.setDoOutput(true);

                    Gson gson = new Gson();
                    String jsonInputString = gson.toJson(employeeBackend);

                    //Print the JSON string before sending the request
                    System.out.println("JSON Request Body: " + jsonInputString);

                    // Write JSON string to output stream using BufferedWriter
                    try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(con.getOutputStream()))) {
                        writer.write(jsonInputString);
                    }

                    int responseCode = con.getResponseCode();
                    if (responseCode == HttpURLConnection.HTTP_OK) {
                        System.out.println(resourceBundle.getString("web.insert.success"));
                    } else {
                        System.out.println(resourceBundle.getString("web.insert.fail"));
                    }
                } catch(IOException e) {
                    System.out.println("IOException occurred: " + e.getMessage());
                }

            }catch (ValidationException ex) {
                // Handle validation error
                Employee employee = new Employee();
                dao.technical.spring.entity.Employee employee1 = new dao.technical.spring.entity.Employee();
                System.out.println("Validation Error: " + ex.getMessage());
                if(ex.getMessage()==resourceBundle.getString("VAL-001")){
                    System.out.println(resourceBundle.getString("first.name"));
                    String firstName = validation.validateName();
                    employee.setFirstName(firstName);
                    employee1.setFirstName(employee.getFirstName());

                }if(ex.getMessage()==resourceBundle.getString("VAL-002")){
                    System.out.println(resourceBundle.getString("middle.name"));
                    String middleName = validation.validateName();
                    employee.setMiddleName(middleName);
                    employee1.setMiddleName(employee.getMiddleName());
                }if(ex.getMessage()==resourceBundle.getString("VAL-003")){
                    System.out.println(resourceBundle.getString("last.name"));
                    String lastName = validation.validateName();
                    employee.setLastName(lastName);
                    employee1.setLastName(employee.getLastName());
                }if(ex.getMessage()==resourceBundle.getString("VAL-004")){
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
                            logger.warn(resourceBundle.getString("empId.invalid"));
                            System.out.println(resourceBundle.getString("empId.invalid"));
                            scanner.next();
                        }
                    } while (true);
                    employee.setEmpID(employeeId);
                    employee1.setEmpID(employee.getEmpID());
                }if(ex.getMessage()==resourceBundle.getString("VAL-005")){
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
                                System.out.println(resourceBundle.getString("mobile.invalid"));
                            }
                        } catch (InputMismatchException e) {
                            logger.warn(resourceBundle.getString("mobile.mismatch"));
                            System.out.println(resourceBundle.getString("mobile.mismatch"));
                            scanner.next();
                        }
                    } while (true);
                    employee.setMobileNumber(mobileNumber);
                    employee1.setMobileNumber(employee.getMobileNumber());
                }
                if(ex.getMessage()==resourceBundle.getString("VAL-006")){
                    System.out.println(resourceBundle.getString("email.id"));
                    String emailID;
                    do {
                        try {
                            emailID = scanner.next();
                            if(validation.validEmailId(emailID)){
                                break;
                            }else {
                                System.out.println(resourceBundle.getString("email.invalid"));
                            }
                        } catch (InputMismatchException e) {
                            logger.warn(resourceBundle.getString("email.mismatch"));
                            System.out.println(resourceBundle.getString("email.mismatch"));
                            scanner.next();
                        }
                    } while (true);
                    employee.setEmailID(emailID);
                    employee1.setEmailID(employee.getEmailID());
                }
                if(ex.getMessage()==resourceBundle.getString("VAL-007")){
                    System.out.println(resourceBundle.getString("permanent.pinCode"));
                    Integer permanentPinCode;
                    do {
                        try {
                            permanentPinCode = scanner.nextInt();
                            String pincode = permanentPinCode.toString();
                            if(validation.isValidPin(pincode)){
                                permanentPinCode = Integer.parseInt(pincode);
                                break;
                            }
                            else {
                                System.out.println(resourceBundle.getString("pinCode.invalid"));
                            }
                        } catch (InputMismatchException e) {
                            logger.warn(resourceBundle.getString("pinCode.mismatch"));
                            System.out.println(resourceBundle.getString("pinCode.mismatch"));
                            scanner.next();
                        }
                    } while (true);
                    employee.getPermanentAddress().setPinCode(permanentPinCode);
                    employee1.getPermanentAddress().setPinCode(employee.getPermanentAddress().getPinCode());
                }
                if(ex.getMessage()==resourceBundle.getString("VAL-008")){
                    System.out.println(resourceBundle.getString("temporary.pinCode"));
                    Integer temporaryPinCode;
                    do {
                        try {
                            temporaryPinCode = scanner.nextInt();
                            String pincode = temporaryPinCode.toString();
                            if(validation.isValidPin(pincode)){
                                temporaryPinCode = Integer.parseInt(pincode);
                                break;
                            }else {
                                System.out.println(resourceBundle.getString("pinCode.invalid"));
                            }

                        } catch (InputMismatchException e) {
                            logger.warn(resourceBundle.getString("pinCode.mismatch"));
                            System.out.println(resourceBundle.getString("pinCode.mismatch"));
                            scanner.next();
                        }
                    } while (true);
                    employee.getTemporaryAddress().setPinCode(temporaryPinCode);
                    employee1.getTemporaryAddress().setPinCode(employee.getTemporaryAddress().getPinCode());
                }
            } catch (Exception e) {
                // Handle other exceptions
                e.printStackTrace();
                System.out.println("An error occurred: " + e.getMessage());
            }

            System.out.println(resourceBundle.getString("another.employee"));
            chance = scanner.next();

        }while (chance.equalsIgnoreCase("yes"));
    }

    //displays the output data
    @Override
    public void outputData() throws SQLException {

       // dao.technical.spring.remotes.EmployeeInterface app1 = new dao.technical.spring.services.EmployeeServices();

        List<Employee> consoleEmployees = new ArrayList<>();  //frontend
       // List<entity.backend.Employee> backendEmployees = app1.displayEmployeeDetails(); //backend
        List<dao.technical.spring.entity.Employee> backendEmployees=null;
        try {
            URL url = new URL(resourceBundle.getString("web.services.read"));//specified url
            con = (HttpURLConnection) url.openConnection();//connection to the specified url
            con.setRequestMethod("GET");//request is a get method
            Gson gson = new Gson();
            int status = con.getResponseCode();
            if (status == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));//gets the response from the server in the form of json
                Type employeeListType = new TypeToken<List<dao.technical.spring.entity.Employee>>(){}.getType();// Type-erasure:-During compilation, the compiler removes all information related to type parameters and replaces them with their bounds or the type Object if no bounds are specified.
                List<dao.technical.spring.entity.Employee> employeeList = gson.fromJson(in,employeeListType);//
                backendEmployees = employeeList; //backend
            } else {
                System.out.println(resourceBundle.getString("web.disconnect")+ status);
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

        for (dao.technical.spring.entity.Employee backendEmployee : backendEmployees) {

            console.technical.spring.entity.Employee consoleEmployee = new console.technical.spring.entity.Employee();

            consoleEmployee.setEmpID(backendEmployee.getEmpID());
            consoleEmployee.setFirstName(backendEmployee.getFirstName());
            consoleEmployee.setMiddleName(backendEmployee.getMiddleName());
            consoleEmployee.setLastName(backendEmployee.getLastName());
            consoleEmployee.setMobileNumber(backendEmployee.getMobileNumber());
            consoleEmployee.setEmailID(backendEmployee.getEmailID());

            // Set permanent address
            dao.technical.spring.entity.EmployeeAddress backendPermanentAddress = backendEmployee.getPermanentAddress();
            console.technical.spring.entity.EmployeeAddress consolePermanentAddress = new console.technical.spring.entity.EmployeeAddress();
            consolePermanentAddress.setHouseName(backendPermanentAddress.getHouseName());
            consolePermanentAddress.setStreetName(backendPermanentAddress.getStreetName());
            consolePermanentAddress.setCityName(backendPermanentAddress.getCityName());
            consolePermanentAddress.setStateName(backendPermanentAddress.getStateName());
            consolePermanentAddress.setPinCode(backendPermanentAddress.getPinCode());
            consoleEmployee.setPermanentAddress(consolePermanentAddress);

            // Set temporary address
            dao.technical.spring.entity.EmployeeAddress backendTemporaryAddress = backendEmployee.getTemporaryAddress();
            console.technical.spring.entity.EmployeeAddress consoleTemporaryAddress = new console.technical.spring.entity.EmployeeAddress();
            consoleTemporaryAddress.setHouseName(backendTemporaryAddress.getHouseName());
            consoleTemporaryAddress.setStreetName(backendTemporaryAddress.getStreetName());
            consoleTemporaryAddress.setCityName(backendTemporaryAddress.getCityName());
            consoleTemporaryAddress.setStateName(backendTemporaryAddress.getStateName());
            consoleTemporaryAddress.setPinCode(backendTemporaryAddress.getPinCode());
            consoleEmployee.setTemporaryAddress(consoleTemporaryAddress);

            // Add console employee to the list
            consoleEmployees.add(consoleEmployee);
        }

        int counter = 0;

        for (Employee employee : consoleEmployees) {
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
    }

    //filter the pin code
    @Override
    public void filter() throws SQLException {
        //entity.backend.method.EmployeeInterface employeeInterface = new business.logic.App(); // Instantiate the class where findEmployeesByPincode() is defined
        List<Employee> consoleEmployees = new ArrayList<>();  //frontend
        Scanner scanner = new Scanner(System.in);
        List<dao.technical.spring.entity.Employee> filteredEmployees = null;
        System.out.println(resourceBundle.getString("display.pin"));
        String pinCode = scanner.next();

        try {
            URL url = new URL(resourceBundle.getString("web.services.filter")+pinCode);//specified url
            HttpURLConnection con = (HttpURLConnection) url.openConnection();//connection to the specified url
            con.setRequestMethod("GET");//request is a get method
            Gson gson = new Gson();
            int status = con.getResponseCode();
            if (status == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));//gets the response from the server in the form of json
                Type employeeListType = new TypeToken<List<dao.technical.spring.entity.Employee>>(){}.getType();
                List<dao.technical.spring.entity.Employee> employeeList = gson.fromJson(in,employeeListType);//
                filteredEmployees = employeeList; //backend
            } else {
                System.out.println(resourceBundle.getString("web.disconnect")+ status);
            }

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }


//        List<entity.backend.Employee> filteredEmployees = employeeInterface.findEmployeesByPincode(pincode);

        for (dao.technical.spring.entity.Employee backendEmployee : filteredEmployees) {

            console.technical.spring.entity.Employee consoleEmployee = new console.technical.spring.entity.Employee();

            consoleEmployee.setEmpID(backendEmployee.getEmpID());
            consoleEmployee.setFirstName(backendEmployee.getFirstName());
            consoleEmployee.setMiddleName(backendEmployee.getMiddleName());
            consoleEmployee.setLastName(backendEmployee.getLastName());
            consoleEmployee.setMobileNumber(backendEmployee.getMobileNumber());
            consoleEmployee.setEmailID(backendEmployee.getEmailID());

            // Set permanent address
            dao.technical.spring.entity.EmployeeAddress backendPermanentAddress = backendEmployee.getPermanentAddress();
            console.technical.spring.entity.EmployeeAddress consolePermanentAddress = new console.technical.spring.entity.EmployeeAddress();
            consolePermanentAddress.setHouseName(backendPermanentAddress.getHouseName());
            consolePermanentAddress.setStreetName(backendPermanentAddress.getStreetName());
            consolePermanentAddress.setCityName(backendPermanentAddress.getCityName());
            consolePermanentAddress.setStateName(backendPermanentAddress.getStateName());
            consolePermanentAddress.setPinCode(backendPermanentAddress.getPinCode());
            consoleEmployee.setPermanentAddress(consolePermanentAddress);

            // Set temporary address
            dao.technical.spring.entity.EmployeeAddress backendTemporaryAddress = backendEmployee.getTemporaryAddress();
            console.technical.spring.entity.EmployeeAddress consoleTemporaryAddress = new console.technical.spring.entity.EmployeeAddress();
            consoleTemporaryAddress.setHouseName(backendTemporaryAddress.getHouseName());
            consoleTemporaryAddress.setStreetName(backendTemporaryAddress.getStreetName());
            consoleTemporaryAddress.setCityName(backendTemporaryAddress.getCityName());
            consoleTemporaryAddress.setStateName(backendTemporaryAddress.getStateName());
            consoleTemporaryAddress.setPinCode(backendTemporaryAddress.getPinCode());
            consoleEmployee.setTemporaryAddress(consoleTemporaryAddress);

            // Add console employee to the list
            consoleEmployees.add(consoleEmployee);
        }
        if (consoleEmployees.isEmpty()) {
            System.out.println(resourceBundle.getString("employee.not.found"));
        } else {
            System.out.println("Filtered Employees:");
            for (int i = 0; i < consoleEmployees.size(); i++) {
                console.technical.spring.entity.Employee employee = consoleEmployees.get(i);
                System.out.println("Employee " + (i + 1) + ":");
                System.out.println("First Name: " + employee.getFirstName());
                System.out.println("Middle Name: " + employee.getMiddleName());
                System.out.println("Last Name: " + employee.getLastName());
                System.out.println("Employee ID: " + employee.getEmpID());
                System.out.println("Mobile Number: " + employee.getMobileNumber());
                System.out.println("Email ID: " + employee.getEmailID());
                System.out.println("Permanent Address:");
                System.out.println("House Name: " + employee.getPermanentAddress().getHouseName());
                System.out.println("Street Name: " + employee.getPermanentAddress().getStreetName());
                System.out.println("City Name: " + employee.getPermanentAddress().getCityName());
                System.out.println("State Name: " + employee.getPermanentAddress().getStateName());
                System.out.println("Pincode: " + employee.getPermanentAddress().getPinCode());
                System.out.println("Temporary Address:");
                System.out.println("House Name: " + employee.getTemporaryAddress().getHouseName());
                System.out.println("Street Name: " + employee.getTemporaryAddress().getStreetName());
                System.out.println("City Name: " + employee.getTemporaryAddress().getCityName());
                System.out.println("State Name: " + employee.getTemporaryAddress().getStateName());
                System.out.println("Pincode: " + employee.getTemporaryAddress().getPinCode());
                System.out.println();
            }
        }
    }

}
