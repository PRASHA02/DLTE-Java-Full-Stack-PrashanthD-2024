package business.logic;

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
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    static ResourceBundle resourceBundle = ResourceBundle.getBundle("application");
    public App() throws SQLException {
        try {
            Database database = new Database();
            connection = database.getConnection();
        } catch (backend.exceptions.ConnectionFailureException| SQLException e) {
            System.out.println(resourceBundle.getString("conn.failed"));
            throw new backend.exceptions.ConnectionFailureException("Connection to the database failed.");
        }
    }

    static Logger logger = LoggerFactory.getLogger(business.logic.App.class);

    public static void main( String[] args ) throws SQLException {
    }



    @Override
    public boolean writeEmployeeDetails(entity.backend.Employee employee) throws SQLException {

        if (employee == null) {
            System.out.println(resourceBundle.getString("employee.empty"));
            return false;
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
            preparedStatement.close();

            // Check if the insert into employee_input was successful
            if (resultSet > 0) {
                // Insert data into permanent_address table
                String query2 = "INSERT INTO permanent_address_details VALUES (?, ?, ?, ?, ?, ?)";
                preparedStatement = connection.prepareStatement(query2);
                preparedStatement.setInt(1, employee.getEmpID());
                preparedStatement.setString(2, employee.getPermanentAddress().getHouseName());
                preparedStatement.setString(3, employee.getPermanentAddress().getStreetName());
                preparedStatement.setString(4, employee.getPermanentAddress().getCityName());
                preparedStatement.setString(5, employee.getPermanentAddress().getStateName());
                preparedStatement.setInt(6, employee.getPermanentAddress().getPinCode());
                resultSet = preparedStatement.executeUpdate();
                preparedStatement.close();

                // Insert data into temporary_address table
                String query3 = "INSERT INTO temporary_address_details VALUES (?, ?, ?, ?, ?, ?)";
                preparedStatement = connection.prepareStatement(query3);
                preparedStatement.setInt(1, employee.getEmpID());
                preparedStatement.setString(2, employee.getTemporaryAddress().getHouseName());
                preparedStatement.setString(3, employee.getTemporaryAddress().getStreetName());
                preparedStatement.setString(4, employee.getTemporaryAddress().getCityName());
                preparedStatement.setString(5, employee.getTemporaryAddress().getStateName());
                preparedStatement.setInt(6, employee.getTemporaryAddress().getPinCode());
                resultSet = preparedStatement.executeUpdate();

                System.out.println(resourceBundle.getString("db.push.ok"));
                return true;
            } else {
                System.out.println("Failed to insert into employee_input table.");
                return false;
            }
        } catch (SQLException e) {
            // Rollback transaction if an error occurs
            connection.rollback();
            System.out.println(resourceBundle.getString("db.push.fail"));
            e.printStackTrace();
            return false;
        } finally {
            // Restore auto-commit mode and close resources
            connection.setAutoCommit(true);
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
    }


    @Override
    public List<entity.backend.Employee> displayEmployeeDetails() throws SQLException {
        List<entity.backend.Employee> employees = new ArrayList<>();
            String query = "SELECT * FROM employee_input";
            String query2 = "SELECT * FROM permanent_address_details WHERE employeeId = ?";
            String query3 = "SELECT * FROM temporary_address_details WHERE employeeId = ?";

            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                entity.backend.Employee employee = new entity.backend.Employee();
                int employeeId = resultSet.getInt("employeeID");
                employee.setEmpID(employeeId);
                employee.setFirstName(resultSet.getString("firstName"));
                employee.setMiddleName(resultSet.getString("middleName"));
                employee.setLastName(resultSet.getString("lastName"));
                employee.setMobileNumber(resultSet.getLong("mobileNumber"));
                employee.setEmailID(resultSet.getString("emailID"));

                // Fetch permanent address
                preparedStatement = connection.prepareStatement(query2);
                preparedStatement.setInt(1, employeeId);
                ResultSet permAddressResultSet = preparedStatement.executeQuery();
                if (permAddressResultSet.next()) {
                    entity.backend.EmployeeAddress permanentAddress = new entity.backend.EmployeeAddress();

                    permanentAddress.setHouseName(permAddressResultSet.getString("permanentHouseName"));
                    permanentAddress.setStreetName(permAddressResultSet.getString("permanentStreetName"));
                    permanentAddress.setCityName(permAddressResultSet.getString("permanentCityName"));
                    permanentAddress.setStateName(permAddressResultSet.getString("permanentStateName"));
                    permanentAddress.setPinCode(permAddressResultSet.getInt("permanentPinCode"));
                    employee.setPermanentAddress(permanentAddress);
                }

                // Fetch temporary address
                preparedStatement = connection.prepareStatement(query3);
                preparedStatement.setInt(1, employeeId);
                ResultSet tempAddressResultSet = preparedStatement.executeQuery();
                if (tempAddressResultSet.next()) {
                    entity.backend.EmployeeAddress temporaryAddress = new entity.backend.EmployeeAddress();
                    temporaryAddress.setHouseName(tempAddressResultSet.getString("temporaryHouseName"));
                    temporaryAddress.setStreetName(tempAddressResultSet.getString("temporaryStreetName"));
                    temporaryAddress.setCityName(tempAddressResultSet.getString("temporaryCityName"));
                    temporaryAddress.setStateName(tempAddressResultSet.getString("temporaryStateName"));
                    temporaryAddress.setPinCode(tempAddressResultSet.getInt("temporaryPinCode"));
                    employee.setTemporaryAddress(temporaryAddress);
                }

                employees.add(employee);
            }
            connection.close();
            preparedStatement.close();
            resultSet.close();
        return employees;
    }

    @Override
    public  List<Integer> getTemporaryPinCodes() throws SQLException {
        List<Integer> temporaryPinCodes = new ArrayList<>();
        try{

            String query = "SELECT temporaryPinCode FROM temporary_address_details";

            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                temporaryPinCodes.add(resultSet.getInt("temporaryPinCode"));
            }
        }catch(SQLException e){
            e.printStackTrace();
            throw e;
        }finally {
            connection.close();
            preparedStatement.close();
            resultSet.close();
        }


        return temporaryPinCodes;
    }

    @Override
    public List<Integer> getPermanentPinCodes() throws SQLException {
        List<Integer> permanentPinCodes = new ArrayList<>();
        try{
            String query = "SELECT permanentPinCode FROM permanent_address_details";

            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                permanentPinCodes.add(resultSet.getInt("permanentPinCode"));
            }
        }catch (SQLException e){
            e.printStackTrace();
            throw e;
        }
        return permanentPinCodes;
    }



}
