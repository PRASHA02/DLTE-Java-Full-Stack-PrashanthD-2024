package backend.method;

import entity.backend.Employee;

import java.sql.SQLException;
import java.util.List;

public interface EmployeeInterface {
    void writeEmployeeDetails(Employee  employee) throws SQLException;
    public List<Employee> displayEmployeeDetails() throws SQLException;
}
