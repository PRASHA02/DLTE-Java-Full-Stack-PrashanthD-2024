package backend.method;

import entity.backend.Employee;

import java.sql.SQLException;
import java.util.List;

public interface EmployeeInterface {
    boolean writeEmployeeDetails(Employee  employee) throws SQLException;
    public List<Employee> displayEmployeeDetails() throws SQLException;
    public  List<Integer> getTemporaryPinCodes() throws SQLException;
    public  List<Integer> getPermanentPinCodes() throws SQLException;
}
