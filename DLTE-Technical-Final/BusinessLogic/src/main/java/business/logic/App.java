package business.logic;

import backend.exceptions.ConnectionFailureException;
import backend.exceptions.InsertionFailureException;
import backend.exceptions.ValidationException;
import db.connection.Database;
import entity.backend.Employee;
import entity.backend.method.EmployeeInterface;
import entity.backend.validations.Validation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
        } catch (ConnectionFailureException | SQLException e) {
            System.out.println(resourceBundle.getString("conn.failed"));
            throw new ConnectionFailureException("Connection to the database failed.");
        }
    }

    static Logger logger = LoggerFactory.getLogger(business.logic.App.class);

    public static void main( String[] args )  {
    }


    //write employee details in backend
    @Override
    public boolean writeEmployeeDetails(entity.backend.Employee employee) throws SQLException {

        Validation validation = new Validation();

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
            String query1 = "INSERT INTO employee_details  VALUES (?, ?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(query1);
            preparedStatement.setString(1, employee.getFirstName());
            preparedStatement.setString(2, employee.getMiddleName());
            preparedStatement.setString(3, employee.getLastName());
            preparedStatement.setInt(4, employee.getEmpID());
            preparedStatement.setLong(5, employee.getMobileNumber());
            preparedStatement.setString(6, employee.getEmailID());
            int result = preparedStatement.executeUpdate();
//            preparedStatement.close();
            if(result==0){
                System.out.println(resourceBundle.getString("db.push.fail"));
                return false;
            }

            // Check if the insert into employee_input was successful
                // Insert data into permanent_address

                String query2 = "INSERT INTO address_details VALUES (?, ?, ?, ?, ?, ?,?)";
                preparedStatement = connection.prepareStatement(query2);
                preparedStatement.setString(1, employee.getPermanentAddress().getHouseName());
                preparedStatement.setString(2, employee.getPermanentAddress().getStreetName());
                preparedStatement.setString(3, employee.getPermanentAddress().getCityName());
                preparedStatement.setString(4, employee.getPermanentAddress().getStateName());
                preparedStatement.setInt(5, employee.getPermanentAddress().getPinCode());
                preparedStatement.setInt(6, employee.getEmpID());
                preparedStatement.setString(7, employee.getPermanentAddress().getFlag());
                result = preparedStatement.executeUpdate();

                if(result==0){
                    System.out.println(resourceBundle.getString("db.push.fail"));
                    return false;
                }

                String query3 = "INSERT INTO address_details VALUES (?, ?, ?, ?, ?, ?,?)";
                preparedStatement = connection.prepareStatement(query3);
                preparedStatement.setString(1, employee.getTemporaryAddress().getHouseName());
                preparedStatement.setString(2, employee.getTemporaryAddress().getStreetName());
                preparedStatement.setString(3, employee.getTemporaryAddress().getCityName());
                preparedStatement.setString(4, employee.getTemporaryAddress().getStateName());
                preparedStatement.setInt(5, employee.getTemporaryAddress().getPinCode());
                preparedStatement.setInt(6, employee.getEmpID());
                preparedStatement.setString(7, employee.getTemporaryAddress().getFlag());

                result = preparedStatement.executeUpdate();

                if(result==0){
                    System.out.println(resourceBundle.getString("db.push.fail"));
                    return false;
                }
                System.out.println(resourceBundle.getString("db.push.ok"));
                return true;
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
        String query = "SELECT * FROM employee_details";
        String query2 = "SELECT * FROM address_details WHERE emp_id = ? AND flag = 'permanent'";
        String query3 = "SELECT * FROM address_details WHERE emp_id = ? AND flag = 'temporary'";

        try {
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

                // Fetch permanent address
                preparedStatement = connection.prepareStatement(query2);
                preparedStatement.setInt(1, employeeId);
                ResultSet addressResultSet = preparedStatement.executeQuery();
                if (addressResultSet.next()) {
                    entity.backend.EmployeeAddress permanentAddress = new entity.backend.EmployeeAddress();
                    permanentAddress.setHouseName(addressResultSet.getString("house_name"));
                    permanentAddress.setStreetName(addressResultSet.getString("street_name"));
                    permanentAddress.setCityName(addressResultSet.getString("city_name"));
                    permanentAddress.setStateName(addressResultSet.getString("state_name"));
                    permanentAddress.setPinCode(addressResultSet.getInt("pin_code"));
                    permanentAddress.setFlag(addressResultSet.getString("flag")); // Assuming the column name is 'flag'
                    employee.setPermanentAddress(permanentAddress);
                }

                // Fetch temporary address
                preparedStatement = connection.prepareStatement(query3);
                preparedStatement.setInt(1, employeeId);
                addressResultSet = preparedStatement.executeQuery();
                if (addressResultSet.next()) {
                    entity.backend.EmployeeAddress temporaryAddress = new entity.backend.EmployeeAddress();
                    temporaryAddress.setHouseName(addressResultSet.getString("house_name"));
                    temporaryAddress.setStreetName(addressResultSet.getString("street_name"));
                    temporaryAddress.setCityName(addressResultSet.getString("city_name"));
                    temporaryAddress.setStateName(addressResultSet.getString("state_name"));
                    temporaryAddress.setPinCode(addressResultSet.getInt("pin_code"));
                    temporaryAddress.setFlag(addressResultSet.getString("flag")); // Assuming the column name is 'flag'
                    employee.setTemporaryAddress(temporaryAddress);
                }

                employees.add(employee);
            }
        } catch (SQLException e) {
            throw new ConnectionFailureException(resourceBundle.getString("fetch.fail"));
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return employees;
    }

    //sending employee temporary PinCodes to frontend
    @Override
    public List<entity.backend.Employee> findEmployeesByPincode(int pincode) throws SQLException, ConnectionFailureException {
        List<entity.backend.Employee> employeeDetailsList = new ArrayList<>();
        try {
            String query = "SELECT e.first_name, e.middle_name, e.last_name, e.emp_id, e.mobile_number, e.email_id, " +
                    "a.house_name AS permHouseName, a.street_name AS permStreet, a.city_name AS permCity, a.state_name AS permState, a.pin_code AS permPincode, " +
                    "a2.house_name AS tempHouseName, a2.street_name AS tempStreet, a2.city_name AS tempCity, a2.state_name AS tempState, a2.pin_code AS tempPincode " +
                    "FROM employee_details e " +
                    "LEFT JOIN address_details a ON e.emp_id = a.emp_id AND a.flag = 'permanent' " +
                    "LEFT JOIN address_details a2 ON e.emp_id = a2.emp_id AND a2.flag = 'temporary' " +
                    "WHERE a.pin_code = ? OR a2.pin_code = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, pincode);
            preparedStatement.setInt(2, pincode);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                entity.backend.Employee employee = new entity.backend.Employee();
                employee.setFirstName(resultSet.getString("first_name"));
                employee.setMiddleName(resultSet.getString("middle_name"));
                employee.setLastName(resultSet.getString("last_name"));
                employee.setEmpID(resultSet.getInt("emp_id"));
                employee.setMobileNumber(resultSet.getLong("mobile_number"));
                employee.setEmailID(resultSet.getString("email_id"));

                // Retrieve and set permanent address if available
                if (resultSet.getString("permHouseName") != null) {
                    entity.backend.EmployeeAddress permAddress = new entity.backend.EmployeeAddress();
                    permAddress.setHouseName(resultSet.getString("permHouseName"));
                    permAddress.setStreetName(resultSet.getString("permStreet"));
                    permAddress.setCityName(resultSet.getString("permCity"));
                    permAddress.setStateName(resultSet.getString("permState"));
                    permAddress.setPinCode(resultSet.getInt("permPincode"));
                    employee.setPermanentAddress(permAddress);
                }

                // Retrieve and set temporary address if available
                if (resultSet.getString("tempHouseName") != null) {
                    entity.backend.EmployeeAddress tempAddress = new entity.backend.EmployeeAddress();
                    tempAddress.setHouseName(resultSet.getString("tempHouseName"));
                    tempAddress.setStreetName(resultSet.getString("tempStreet"));
                    tempAddress.setCityName(resultSet.getString("tempCity"));
                    tempAddress.setStateName(resultSet.getString("tempState"));
                    tempAddress.setPinCode(resultSet.getInt("tempPincode"));
                    employee.setTemporaryAddress(tempAddress);
                }

                employeeDetailsList.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ConnectionFailureException(resourceBundle.getString("fetch.fail"));
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return employeeDetailsList;
    }




}
