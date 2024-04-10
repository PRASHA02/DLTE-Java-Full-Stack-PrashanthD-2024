package dao.technical.spring.remotes;

import dao.technical.spring.entity.Employee;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

//Interface for inserting,displaying and filtering the data
@Repository
public interface EmployeeInterface {
    String writeEmployeeDetails(Employee employee);
    List<Employee> displayEmployeeDetails();
    List<Employee> findEmployeesByPincode(int pincode);
}
