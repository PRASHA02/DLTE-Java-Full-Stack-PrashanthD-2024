package dao.technical.spring;

import dao.technical.spring.entity.Employee;
import dao.technical.spring.entity.EmployeeAddress;
import dao.technical.spring.services.EmployeeServices;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.rmi.ServerException;
import java.sql.SQLException;
import java.util.List;

@SpringBootApplication
public class SpringDaoApplication {

    public static void main(String[] args) throws SQLException, ServerException {

        ConfigurableApplicationContext context=  SpringApplication.run(SpringDaoApplication.class, args);

        EmployeeServices employeeServices=context.getBean(EmployeeServices.class);
        Employee employee = new Employee();

        employee.setFirstName("jeevan");
        employee.setMiddleName("D");
        employee.setLastName("kumar");
        employee.setEmpID(180);
        employee.setMobileNumber(7892675177L);
        employee.setEmailID("jeevan@gmail.com");

// Setting permanent address
        EmployeeAddress permanentAddress = new EmployeeAddress();
        permanentAddress.setHouseName("veera");
        permanentAddress.setStreetName("moodabidri");
        permanentAddress.setCityName("mijar");
        permanentAddress.setStateName("karnataka");
        permanentAddress.setPinCode(574107);
        permanentAddress.setEmpID(employee.getEmpID());
        employee.setPermanentAddress(permanentAddress);

// Setting temporary address
        EmployeeAddress temporaryAddress = new EmployeeAddress();
        temporaryAddress.setHouseName("prassanna");
        temporaryAddress.setStreetName("mailasandra");
        temporaryAddress.setCityName("bangalore");
        temporaryAddress.setStateName("karnataka");
        temporaryAddress.setPinCode(123429);
        temporaryAddress.setEmpID(employee.getEmpID());
        employee.setTemporaryAddress(temporaryAddress);

//        System.out.println(employeeServices.writeEmployeeDetails(employee));
        List<Employee> employeeList = employeeServices.displayEmployeeDetails();

        employeeList.forEach(System.out::println);
        List<Employee> employeeList1 = employeeServices.findEmployeesByPincode(543216);

        employeeList1.forEach(System.out::println);

    }



}
