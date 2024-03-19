package business.logic;

import db.connection.Database;
import entity.backend.Employee;
import entity.backend.EmployeeAddress;
import backend.method.EmployeeInterface;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class App implements EmployeeInterface {
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    static ResourceBundle resourceBundle = ResourceBundle.getBundle("application");
    public App() throws SQLException {
        Database database = new Database();
        connection = database.getConnection();
    }

    public static void main( String[] args ) throws SQLException {
    }

    @Override
    public void writeEmployeeDetails(Employee employee) throws SQLException {

        if(employee==null){
            System.out.println("Employee is Empty");
            return;
        }
        String query = "INSERT INTO employee_details ( firstName, middleName, lastName,employeeID, mobileNumber, emailID, permanentAddress, permanentHouseName, permanentStreetName, permanentCityName, permanentStateName, permanentPinCode, temporaryAddress, temporaryHouseName, temporaryStreetName, temporaryCityName, temporaryStateName, temporaryPinCode) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(4, employee.getEmpID());
        preparedStatement.setString(1, employee.getFirstName());
        preparedStatement.setString(2, employee.getMiddleName());
        preparedStatement.setString(3, employee.getLastName());
        preparedStatement.setLong(5, employee.getMobileNumber());
        preparedStatement.setString(6, employee.getEmailID());
        preparedStatement.setString(7, employee.getPermanentAddress().getAddress());
        preparedStatement.setString(8, employee.getPermanentAddress().getHouseName());
        preparedStatement.setString(9, employee.getPermanentAddress().getStreetName());
        preparedStatement.setString(10, employee.getPermanentAddress().getCityName());
        preparedStatement.setString(11, employee.getPermanentAddress().getStateName());
        preparedStatement.setInt(12, employee.getPermanentAddress().getPinCode());
        preparedStatement.setString(13, employee.getTemporaryAddress().getAddress());
        preparedStatement.setString(14, employee.getTemporaryAddress().getHouseName());
        preparedStatement.setString(15, employee.getTemporaryAddress().getStreetName());
        preparedStatement.setString(16, employee.getTemporaryAddress().getCityName());
        preparedStatement.setString(17, employee.getTemporaryAddress().getStateName());
        preparedStatement.setInt(18,employee.getTemporaryAddress().getPinCode());
        int resultSet = preparedStatement.executeUpdate();
        if (resultSet != 0) {
            System.out.println(resourceBundle.getString("db.push.ok"));
        } else {
            System.out.println(resourceBundle.getString("db.push.fail"));
        }

    }

    @Override
    public List<Employee> displayEmployeeDetails() throws SQLException {
        List<Employee> employees = new ArrayList<>();
        String query = "SELECT * FROM employee_details";

        preparedStatement = connection.prepareStatement(query);
        resultSet = preparedStatement.executeQuery();
        while(resultSet.next()){
            Employee employee = new Employee();
            employee.setEmpID(resultSet.getInt("employeeID"));
            employee.setFirstName(resultSet.getString("firstName"));
            employee.setMiddleName(resultSet.getString("middleName"));
            employee.setLastName(resultSet.getString("lastName"));
            employee.setMobileNumber(resultSet.getLong("mobileNumber"));
            employee.setEmailID(resultSet.getString("emailID"));

            EmployeeAddress permanentAddress = new EmployeeAddress();
            permanentAddress.setAddress(resultSet.getString("permanentAddress"));
            permanentAddress.setHouseName(resultSet.getString("permanentHouseName"));
            permanentAddress.setStreetName(resultSet.getString("permanentStreetName"));
            permanentAddress.setCityName(resultSet.getString("permanentCityName"));
            permanentAddress.setStateName(resultSet.getString("permanentStateName"));
            permanentAddress.setPinCode(resultSet.getInt("permanentPinCode"));
            employee.setPermanentAddress(permanentAddress);

            EmployeeAddress temporaryAddress = new EmployeeAddress();
            temporaryAddress.setAddress(resultSet.getString("temporaryAddress"));
            temporaryAddress.setHouseName(resultSet.getString("temporaryHouseName"));
            temporaryAddress.setStreetName(resultSet.getString("temporaryStreetName"));
            temporaryAddress.setCityName(resultSet.getString("temporaryCityName"));
            temporaryAddress.setStateName(resultSet.getString("temporaryStateName"));
            temporaryAddress.setPinCode(resultSet.getInt("temporaryPinCode"));
            employee.setTemporaryAddress(temporaryAddress);

            employees.add(employee);
        }
        connection.close();
        return employees;
    }



}
