package business.logic;

import backend.exceptions.ConnectionFailureException;
import backend.exceptions.InsertionFailureException;
import backend.exceptions.ValidationException;
import db.connection.Database;
import entity.backend.Employee;
import entity.backend.EmployeeAddress;
import backend.method.EmployeeInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jws.WebParam;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class App implements EmployeeInterface {
    public Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    static ResourceBundle resourceBundle = ResourceBundle.getBundle("application");
    public App() throws SQLException {
        try {
            Database database = new Database();
            connection = database.getConnection();
        } catch (backend.exceptions.ConnectionFailureException | SQLException e) {
            System.out.println(resourceBundle.getString("conn.failed"));
            throw new backend.exceptions.ConnectionFailureException("Connection to the database failed.");
        }
    }

    static Logger logger = LoggerFactory.getLogger(business.logic.App.class);

    public static void main( String[] args )  {
    }


    //write employee details in backend
    @Override
    public boolean writeEmployeeDetails(entity.backend.Employee employee) throws SQLException {

        backend.validations.Validation validation = new backend.validations.Validation();

        if (employee == null) {
            throw new ValidationException(resourceBundle.getString("employee.empty"));
        }

       if(validation.isValidId(employee.getEmpID())){
           employee.setEmpID(employee.getEmpID());
       }else{
           throw new ValidationException(resourceBundle.getString("VAL-004"));
       }

       if(validation.isValidateMobile(employee.getMobileNumber())){
           employee.setMobileNumber(employee.getMobileNumber());
       }else{
           throw new ValidationException(resourceBundle.getString("VAL-005"));
       }

       if(validation.isValidEmailId(employee.getEmailID())){
           employee.setEmailID(employee.getEmailID());
       }else{
           throw new ValidationException(resourceBundle.getString("VAL-006"));
       }

       if(validation.isValidPin(employee.getPermanentAddress().getPinCode())){
           employee.getPermanentAddress().setPinCode(employee.getPermanentAddress().getPinCode());
       }else{
           throw new ValidationException(resourceBundle.getString("VAL-007"));
       }
        if(validation.isValidPin(employee.getTemporaryAddress().getPinCode())){
            employee.getTemporaryAddress().setPinCode(employee.getTemporaryAddress().getPinCode());
        }else{
            throw new ValidationException(resourceBundle.getString("VAL-008"));
        }
        try {
            // Insert data into employee_input table
            String query1 = "INSERT INTO employee_input  VALUES (?, ?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(query1);
            preparedStatement.setString(1, employee.getFirstName());
            preparedStatement.setString(2, employee.getMiddleName());
            preparedStatement.setString(3, employee.getLastName());
            preparedStatement.setInt(4, employee.getEmpID());
            preparedStatement.setLong(5, employee.getMobileNumber());
            preparedStatement.setString(6, employee.getEmailID());
            int resultSet = preparedStatement.executeUpdate();
//            preparedStatement.close();

            // Check if the insert into employee_input was successful
            if (resultSet > 0) {
                // Insert data into permanent_address table
                String query2 = "INSERT INTO address_details VALUES (?, ?, ?, ?, ?, ?,?,?,?,?,?)";
                preparedStatement = connection.prepareStatement(query2);
                preparedStatement.setInt(1, employee.getEmpID());
                preparedStatement.setString(2, employee.getPermanentAddress().getHouseName());
                preparedStatement.setString(3, employee.getPermanentAddress().getStreetName());
                preparedStatement.setString(4, employee.getPermanentAddress().getCityName());
                preparedStatement.setString(5, employee.getPermanentAddress().getStateName());
                preparedStatement.setInt(6, employee.getPermanentAddress().getPinCode());
                preparedStatement.setString(7, employee.getTemporaryAddress().getHouseName());
                preparedStatement.setString(8, employee.getTemporaryAddress().getStreetName());
                preparedStatement.setString(9, employee.getTemporaryAddress().getCityName());
                preparedStatement.setString(10, employee.getTemporaryAddress().getStateName());
                preparedStatement.setInt(11, employee.getTemporaryAddress().getPinCode());
                resultSet = preparedStatement.executeUpdate();

                System.out.println(resourceBundle.getString("db.push.ok"));
                return true;
            } else {
                System.out.println(resourceBundle.getString("db.push.fail"));
                return false;
            }
        } catch (SQLIntegrityConstraintViolationException e) {
            // Rollback transaction if an error occurs
            connection.rollback();
            System.out.println(resourceBundle.getString("db.fail.insert"));
            e.printStackTrace();
            return false;
        } catch(SQLException sqlException){
            throw new InsertionFailureException(resourceBundle.getString("db.push.fail"));
        }
        finally {
            // Restore auto-commit mode and close resources
            connection.setAutoCommit(true);
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
    }

    //sending employee details to the frontend
    @Override
    public List<entity.backend.Employee> displayEmployeeDetails() throws SQLException, ConnectionFailureException {
        List<entity.backend.Employee> employees = new ArrayList<>();
        String query = "SELECT * FROM employee_input";
        String query2 = "SELECT * FROM address_details WHERE emp_id = ?";

        preparedStatement = connection.prepareStatement(query);
        resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            entity.backend.Employee employee = new entity.backend.Employee();
            int employeeId = resultSet.getInt("emp_id");
            employee.setEmpID(employeeId);
            employee.setFirstName(resultSet.getString("first_name"));
            employee.setMiddleName(resultSet.getString("middle_name"));
            employee.setLastName(resultSet.getString("last_name"));
            employee.setMobileNumber(resultSet.getLong("mobile_number"));
            employee.setEmailID(resultSet.getString("email_id"));

            // Fetch permanent and temporary addresses
            preparedStatement = connection.prepareStatement(query2);
            preparedStatement.setInt(1, employeeId);
            ResultSet addressResultSet = preparedStatement.executeQuery();
            if (addressResultSet.next()) {
                entity.backend.EmployeeAddress permanentAddress = new entity.backend.EmployeeAddress();
                permanentAddress.setHouseName(addressResultSet.getString("PERMANENTHOUSENAME"));
                permanentAddress.setStreetName(addressResultSet.getString("PERMANENTSTREETNAME"));
                permanentAddress.setCityName(addressResultSet.getString("PERMANENTCITYNAME"));
                permanentAddress.setStateName(addressResultSet.getString("PERMANENTSTATENAME"));
                permanentAddress.setPinCode(addressResultSet.getInt("PERMANENTPINCODE"));
                employee.setPermanentAddress(permanentAddress);

                entity.backend.EmployeeAddress temporaryAddress = new entity.backend.EmployeeAddress();
                temporaryAddress.setHouseName(addressResultSet.getString("TEMPORARYHOUSENAME"));
                temporaryAddress.setStreetName(addressResultSet.getString("TEMPORARYSTREETNAME"));
                temporaryAddress.setCityName(addressResultSet.getString("TEMPORARYCITYNAME"));
                temporaryAddress.setStateName(addressResultSet.getString("TEMPORARYSTATENAME"));
                temporaryAddress.setPinCode(addressResultSet.getInt("TEMPORARYPINCODE"));
                employee.setTemporaryAddress(temporaryAddress);
            }
            employees.add(employee);
        }
        connection.close();
        preparedStatement.close();
        resultSet.close();
        return employees;
    }

    //sending employee temporary PinCodes to frontend
    @Override
    public List<entity.backend.Employee> findEmployeesByPincode(int pincode) throws SQLException,ConnectionFailureException {
        List<entity.backend.Employee> employeeDetailsList = new ArrayList<>();
        try {
            String query = "SELECT e.first_name, e.middle_name, e.last_name, e.emp_id, e.mobile_number, e.email_id, " +
                    "a.PERMANENTHOUSENAME AS permHouseName, a.PERMANENTSTREETNAME AS permStreet, a.PERMANENTCITYNAME AS permCity, a.PERMANENTSTATENAME AS permState, a.PERMANENTPINCODE AS permPincode, " +
                    "a.TEMPORARYHOUSENAME AS tempHouseName, a.TEMPORARYSTREETNAME AS tempStreet, a.TEMPORARYCITYNAME AS tempCity, a.TEMPORARYSTATENAME AS tempState, a.TEMPORARYPINCODE AS tempPincode " +
                    "FROM employee_input e " +
                    "INNER JOIN address_details a ON e.emp_id = a.emp_id " +
                    "WHERE a.TEMPORARYPINCODE = ? OR a.PERMANENTPINCODE = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, pincode);
            preparedStatement.setInt(2, pincode);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String firstName = resultSet.getString("first_name");
                String middleName = resultSet.getString("middle_name");
                String lastName = resultSet.getString("last_name");
                int empId = resultSet.getInt("emp_id");
                Long empMobileNumber = resultSet.getLong("mobile_number");
                String emailId = resultSet.getString("email_id");
                String tempHouseName = resultSet.getString("tempHouseName");
                String tempStreet = resultSet.getString("tempStreet");
                String tempCity = resultSet.getString("tempCity");
                String tempState = resultSet.getString("tempState");
                int tempPincode = resultSet.getInt("tempPincode");
                EmployeeAddress tempAddress = new EmployeeAddress(tempHouseName, tempStreet, tempCity, tempState, tempPincode);

                String permHouseName = resultSet.getString("permHouseName");
                String permStreet = resultSet.getString("permStreet");
                String permCity = resultSet.getString("permCity");
                String permState = resultSet.getString("permState");
                int permPincode = resultSet.getInt("permPincode");
                EmployeeAddress permAddress = new EmployeeAddress(permHouseName, permStreet, permCity, permState, permPincode);

                Employee employeeDetails = new Employee(firstName, middleName, lastName, empId, empMobileNumber, emailId, permAddress, tempAddress);
                employeeDetailsList.add(employeeDetails);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            connection.close();
        }
        return employeeDetailsList;
    }


}
