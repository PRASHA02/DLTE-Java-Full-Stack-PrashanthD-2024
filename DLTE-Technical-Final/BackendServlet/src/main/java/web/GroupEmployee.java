package web;

import entity.backend.Employee;

import java.util.List;

public class GroupEmployee {
    private List<Employee> employeeList;

    public GroupEmployee() {
    }

    public GroupEmployee(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    @Override
    public String toString() {
        return "GroupEmployee{" +
                "employeeList=" + employeeList +
                '}';
    }
}
