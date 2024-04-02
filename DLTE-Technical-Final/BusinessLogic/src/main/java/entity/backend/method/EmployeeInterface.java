package entity.backend.method;

import entity.backend.Employee;

import java.sql.SQLException;
import java.util.List;

//Interface for inserting,displaying and filtering the data
public interface EmployeeInterface {
    boolean writeEmployeeDetails(Employee  employee) throws SQLException;
    List<Employee> displayEmployeeDetails() throws SQLException;
    List<entity.backend.Employee> findEmployeesByPincode(int pincode) throws SQLException;
}
