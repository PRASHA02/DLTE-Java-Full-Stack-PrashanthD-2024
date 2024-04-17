package dao.web.services.rest;

import dao.technical.spring.entity.Employee;
import dao.technical.spring.exception.ConnectionFailureException;
import dao.technical.spring.exception.InsertionFailureException;
import dao.technical.spring.exception.UserAlreadyExistException;
import dao.technical.spring.exception.ValidationException;
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

    private static final Logger logger = LoggerFactory.getLogger(EmployeeRest.class);

    private static final ResourceBundle resourceBundle = ResourceBundle.getBundle("application");

    @PostMapping("/writeEmployee")
    public ResponseEntity<String> insertEmployee(@RequestBody Employee employee) {

        try {
        String response = employeeInterface.writeEmployeeDetails(employee);
        logger.info(resourceBundle.getString("db.push.ok"));
        return ResponseEntity.ok(response);
        } catch (InsertionFailureException e) {
            logger.error(resourceBundle.getString("db.push.fail"));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        } catch (UserAlreadyExistException e) {
            logger.error(resourceBundle.getString("employee.exists"));
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }catch(ConnectionFailureException e){
            logger.warn(resourceBundle.getString("db.syntax.fail"));
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(e.getMessage());
        }catch(ValidationException e){
            logger.warn(resourceBundle.getString("validation.error"));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
        }
        @GetMapping("/findAll")
       public ResponseEntity<Object> readAllEmployee(){
        try{
            List<Employee> employeeList = employeeInterface.displayEmployeeDetails();
            return ResponseEntity.ok(employeeList);
        }catch (ConnectionFailureException connection){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(connection.getMessage());
        }
        }
    @GetMapping("/findbypin/{pinCode}")
    public ResponseEntity<Object> findByPinCode(@PathVariable Integer pinCode){
        try{
            List<Employee> employeeList = employeeInterface.findEmployeesByPincode(pinCode);
            return ResponseEntity.ok(employeeList);
        }catch (ConnectionFailureException connection){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(connection.getMessage());
        }
    }
}
