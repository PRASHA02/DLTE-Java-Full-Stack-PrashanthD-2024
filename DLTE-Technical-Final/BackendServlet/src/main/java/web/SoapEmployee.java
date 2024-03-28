package web;

import backend.method.EmployeeInterface;
import business.logic.App;
import db.connection.Database;
import entity.backend.Employee;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class SoapEmployee {

    EmployeeInterface employeeInterface;


    public SoapEmployee() {
        try {
            employeeInterface = new App();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @WebMethod
    @WebResult(name = "writeEmployee")
    public boolean callWriteEmployee(@WebParam(name = "Employee") Employee employee) {
        boolean data = false;
        try {
            data=employeeInterface.writeEmployeeDetails(employee);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }

    @WebMethod
    @WebResult(name="displayEmployee")
    public GroupEmployee callDisplayEmployee() {
       GroupEmployee groupEmployee = new GroupEmployee();
        List<Employee> employeeList = null;
        try {
            employeeList = employeeInterface.displayEmployeeDetails();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        groupEmployee.setEmployeeList(employeeList);
        return groupEmployee;
    }

    @WebMethod
    @WebResult
    public GroupEmployee callFilterPinCodes(@WebParam(name = "Integer") Integer pinCode) {
        GroupEmployee groupEmployee = new GroupEmployee();
        List<Employee> employeeList = null;
        try {
            employeeList = employeeInterface.findEmployeesByPincode(pinCode);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        groupEmployee.setEmployeeList(employeeList);
        return groupEmployee;
    }

}
