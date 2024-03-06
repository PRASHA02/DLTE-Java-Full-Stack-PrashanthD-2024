package middlelayer.oops;

import java.util.ArrayList;

public interface EmployeeDetailsInterface {
    void employeeDetails(ArrayList<Employee> employee,ArrayList<EmployeeAddress> employeeAddress);
    void employeeOutputDetails(ArrayList<Employee> employee,ArrayList<EmployeeAddress> employeeAddress);
    void filter(ArrayList<EmployeeAddress> employeeAddress);
}
