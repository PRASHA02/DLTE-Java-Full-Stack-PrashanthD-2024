package dao.web.services.rest;

import dao.technical.spring.entity.Employee;
import dao.technical.spring.exception.ConnectionFailureException;
import dao.technical.spring.exception.InsertionFailureException;
import dao.technical.spring.exception.UserAlreadyExistException;
import dao.technical.spring.remotes.EmployeeInterface;
import dao.technical.spring.services.EmployeeServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.ResourceBundle;


@ComponentScan("dao.technical.spring")
@RestController
@RequestMapping("/controller")
public class EmployeeRest {

    @Autowired
    private EmployeeInterface employeeInterface;


    @PostMapping("/writeEmployee")
    public ResponseEntity<String> insertEmployee(@RequestBody Employee employee) {

        try {
        String response = employeeInterface.writeEmployeeDetails(employee);
        return ResponseEntity.ok(response);
        } catch (InsertionFailureException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        } catch (UserAlreadyExistException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        }
        @GetMapping("/findAll")
       public ResponseEntity<Object> readAllEmployee(){
        try{
            List<Employee> employeeList = employeeInterface.displayEmployeeDetails();
            return ResponseEntity.ok(employeeList);
        }catch (ConnectionFailureException con){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(con.getMessage());
        }
        }
    @GetMapping("/findbypin/{pinCode}")
    public ResponseEntity<Object> findByPinCode(@PathVariable Integer pinCode){
        try{
            List<Employee> employeeList = employeeInterface.findEmployeesByPincode(pinCode);
            return ResponseEntity.ok(employeeList);
        }catch (ConnectionFailureException con){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(con.getMessage());
        }
    }
}
