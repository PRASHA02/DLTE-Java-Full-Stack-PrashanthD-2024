package dao.technical.spring.services;

import dao.technical.spring.entity.Employee;
import dao.technical.spring.entity.EmployeeAddress;
import dao.technical.spring.exception.ConnectionFailureException;
import dao.technical.spring.exception.InsertionFailureException;
import dao.technical.spring.exception.UserAlreadyExistException;
import dao.technical.spring.exception.ValidationException;
import dao.technical.spring.remotes.EmployeeInterface;
import dao.technical.spring.validations.Validation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

@Service
public class EmployeeServices implements EmployeeInterface {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final ResourceBundle resourceBundle = ResourceBundle.getBundle("application");

    private static final Logger logger = LoggerFactory.getLogger(EmployeeServices.class);




    @Override
    public String writeEmployeeDetails(Employee employee)  {
        Validation validation = new Validation();
        if (employee == null) {
            throw new ValidationException(resourceBundle.getString("employee.empty"));
        }

        if(validation.isValidId(employee.getEmpID())){
            employee.setEmpID(employee.getEmpID());
        }else{
            throw new ValidationException(resourceBundle.getString("VAL-004"));
        }

        if(validation.isValidateMobile(employee.getMobileNumber())){
            employee.setMobileNumber(employee.getMobileNumber());
        }else{
            throw new ValidationException(resourceBundle.getString("VAL-005"));
        }

        if(validation.isValidEmailId(employee.getEmailID())){
            employee.setEmailID(employee.getEmailID());
        }else{
            throw new ValidationException(resourceBundle.getString("VAL-006"));
        }

        if(validation.isValidPin(employee.getPermanentAddress().getPinCode())){
            employee.getPermanentAddress().setPinCode(employee.getPermanentAddress().getPinCode());
        }else{
            throw new ValidationException(resourceBundle.getString("VAL-007"));
        }
        if(validation.isValidPin(employee.getTemporaryAddress().getPinCode())){
            employee.getTemporaryAddress().setPinCode(employee.getTemporaryAddress().getPinCode());
        }else{
            throw new ValidationException(resourceBundle.getString("VAL-008"));
        }
        try {
            String insertEmployeeQuery = "INSERT INTO employee_details  VALUES (?, ?, ?, ?, ?, ?)";

            String insertAddressQuery = "INSERT INTO address_details VALUES (?, ?, ?, ?, ?, ?,?)";
            int ack = jdbcTemplate.update(insertEmployeeQuery,employee.getFirstName(),employee.getMiddleName(),employee.getLastName(),employee.getEmpID(),employee.getMobileNumber(),employee.getMobileNumber());
            if(ack==0){
                logger.error(resourceBundle.getString("db.push.fail"));
                return resourceBundle.getString("db.push.fail");
            }
            ack= jdbcTemplate.update(insertAddressQuery,
                    employee.getPermanentAddress().getHouseName(),
                    employee.getPermanentAddress().getStreetName(),
                    employee.getPermanentAddress().getCityName(),
                    employee.getPermanentAddress().getStateName(),
                    employee.getPermanentAddress().getPinCode(),
                    employee.getEmpID(),
                    "permanent");
            if(ack==0 ){
                logger.error(resourceBundle.getString("db.permanentAddress.fail"));
                throw new InsertionFailureException(resourceBundle.getString("db.permanentAddress.fail"));
            }

           ack = jdbcTemplate.update(insertAddressQuery,
                   employee.getTemporaryAddress().getHouseName(),
                    employee.getTemporaryAddress().getStreetName(),
                    employee.getTemporaryAddress().getCityName(),
                    employee.getTemporaryAddress().getStateName(),
                    employee.getTemporaryAddress().getPinCode(),
                            employee.getEmpID(),
                    "temporary");

            if(ack==0){
                logger.error(resourceBundle.getString("db.temporaryAddress.fail"));
                throw new InsertionFailureException(resourceBundle.getString("db.temporaryAddress.fail"));
            }
            logger.info(resourceBundle.getString("db.push.ok"));
            return resourceBundle.getString("db.push.ok");
        }catch (org.springframework.dao.DataIntegrityViolationException e) {
            logger.warn(resourceBundle.getString("db.fail.insert"));
            throw new UserAlreadyExistException(resourceBundle.getString("employee.exists"));
        }

    }

    @Override
    public List<Employee> displayEmployeeDetails()  {
        List<Employee> employees = new ArrayList<>();
        String query = "SELECT * FROM employee_details";
        String query2 = "SELECT * FROM address_details WHERE emp_id = ? AND flag = 'permanent'";
        String query3 = "SELECT * FROM address_details WHERE emp_id = ? AND flag = 'temporary'";

        try {
            List<Map<String, Object>> employeeRows = jdbcTemplate.queryForList(query);
            for (Map<String, Object> employeeRow : employeeRows) {
                Employee employee = new Employee();
                int employeeId = ((Number) employeeRow.get("emp_id")).intValue();
                employee.setFirstName((String) employeeRow.get("first_name"));
                employee.setMiddleName((String) employeeRow.get("middle_name"));
                employee.setLastName((String) employeeRow.get("last_name"));
                employee.setEmpID(((Number) employeeRow.get("emp_id")).intValue());
                employee.setMobileNumber(((Number) employeeRow.get("mobile_number")).longValue());
                employee.setEmailID((String) employeeRow.get("email_id"));

                // Fetch permanent address
                List<Map<String, Object>> permanentAddressRows = jdbcTemplate.queryForList(query2, employeeId);
                if (!permanentAddressRows.isEmpty()) {
                    Map<String, Object> permanentAddressRow = permanentAddressRows.get(0);
                    EmployeeAddress permanentAddress = new EmployeeAddress();
                    permanentAddress.setHouseName((String) permanentAddressRow.get("house_name"));
                    permanentAddress.setStreetName((String) permanentAddressRow.get("street_name"));
                    permanentAddress.setCityName((String) permanentAddressRow.get("city_name"));
                    permanentAddress.setStateName((String) permanentAddressRow.get("state_name"));
                    permanentAddress.setPinCode(((Number) permanentAddressRow.get("pin_code")).intValue());
//                    permanentAddress.setEmpID(employeeId);
                    permanentAddress.setFlag((String) permanentAddressRow.get("flag"));
                    employee.setPermanentAddress(permanentAddress);
                }

                // Fetch temporary address
                List<Map<String, Object>> temporaryAddressRows = jdbcTemplate.queryForList(query3, employeeId);
                if (!temporaryAddressRows.isEmpty()) {
                    Map<String, Object> temporaryAddressRow = temporaryAddressRows.get(0);
                    EmployeeAddress temporaryAddress = new EmployeeAddress();
                    temporaryAddress.setHouseName((String) temporaryAddressRow.get("house_name"));
                    temporaryAddress.setStreetName((String) temporaryAddressRow.get("street_name"));
                    temporaryAddress.setCityName((String) temporaryAddressRow.get("city_name"));
                    temporaryAddress.setStateName((String) temporaryAddressRow.get("state_name"));
                    temporaryAddress.setPinCode(((Number) temporaryAddressRow.get("pin_code")).intValue());
//                    temporaryAddress.setEmpID(employeeId);
                    temporaryAddress.setFlag((String) temporaryAddressRow.get("flag"));
                    employee.setTemporaryAddress(temporaryAddress);
                }

                employees.add(employee);

            }
        } catch (org.springframework.dao.DataAccessException e) {
            logger.error(resourceBundle.getString("fetch.fail"));
            throw new ConnectionFailureException(resourceBundle.getString("fetch.fail"));
        }
        logger.info(resourceBundle.getString("fetch.success"));
        return employees;
    }



    @Override
    public List<Employee> findEmployeesByPincode(int pincode) {
        List<Employee> employeeDetailsList = new ArrayList<>();
        try {
            String query = "SELECT e.first_name, e.middle_name, e.last_name, e.emp_id, e.mobile_number, e.email_id, " +
                    "a.house_name AS permHouseName, a.street_name AS permStreet, a.city_name AS permCity, a.state_name AS permState, a.pin_code AS permPincode, " +
                    "a2.house_name AS tempHouseName, a2.street_name AS tempStreet, a2.city_name AS tempCity, a2.state_name AS tempState, a2.pin_code AS tempPincode " +
                    "FROM employee_details e " +
                    "LEFT JOIN address_details a ON e.emp_id = a.emp_id AND a.flag = 'permanent' " +
                    "LEFT JOIN address_details a2 ON e.emp_id = a2.emp_id AND a2.flag = 'temporary' " +
                    "WHERE a.pin_code = ? OR a2.pin_code = ?";
            jdbcTemplate.query(query, new Object[]{pincode, pincode}, (ResultSet rs) -> {
                while (rs.next()) {
                    Employee employee = new Employee();
                    employee.setFirstName(rs.getString("first_name"));
                    employee.setMiddleName(rs.getString("middle_name"));
                    employee.setLastName(rs.getString("last_name"));
                    employee.setEmpID(rs.getInt("emp_id"));
                    employee.setMobileNumber(rs.getLong("mobile_number"));
                    employee.setEmailID(rs.getString("email_id"));

                    // Retrieve and set permanent address if available
                    if (rs.getString("emp_id") != null) {
                        EmployeeAddress permAddress = new EmployeeAddress();
                        permAddress.setHouseName(rs.getString("permHouseName"));
                        permAddress.setStreetName(rs.getString("permStreet"));
                        permAddress.setCityName(rs.getString("permCity"));
                        permAddress.setStateName(rs.getString("permState"));
                        permAddress.setPinCode(rs.getInt("permPincode"));
                        employee.setPermanentAddress(permAddress);
                    }

                    // Retrieve and set temporary address if available
                    if (rs.getString("emp_id") != null) {
                        EmployeeAddress tempAddress = new EmployeeAddress();
                        tempAddress.setHouseName(rs.getString("tempHouseName"));
                        tempAddress.setStreetName(rs.getString("tempStreet"));
                        tempAddress.setCityName(rs.getString("tempCity"));
                        tempAddress.setStateName(rs.getString("tempState"));
                        tempAddress.setPinCode(rs.getInt("tempPincode"));

                        employee.setTemporaryAddress(tempAddress);
                    }

                    employeeDetailsList.add(employee);
                }
                logger.info(resourceBundle.getString("fetch.success"));
                return employeeDetailsList; // Lambda expression requires a return value
            });
        } catch (org.springframework.dao.DataAccessException e) {
            logger.error(resourceBundle.getString("fetch.fail"));
            throw new ConnectionFailureException(resourceBundle.getString("fetch.fail"));
        }
        return employeeDetailsList;
    }


    public class EmployeeMapper implements RowMapper<Employee>{

        @Override
        public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
            Employee employee = new Employee();
            employee.setFirstName(rs.getString(1));
            employee.setMiddleName(rs.getString(2));
            employee.setLastName(rs.getString(3));
            employee.setEmpID(rs.getInt(4));
            employee.setMobileNumber(rs.getLong(5));
            employee.setEmailID(rs.getString(6));

            EmployeeAddress employeePermanentAddress = new EmployeeAddress();
            employeePermanentAddress.setHouseName(rs.getString(7));
            employeePermanentAddress.setStreetName(rs.getString(8));
            employeePermanentAddress.setCityName(rs.getString(9));
            employeePermanentAddress.setStateName(rs.getString(10));
            employeePermanentAddress.setPinCode(rs.getInt(11));
            employeePermanentAddress.setEmpID(rs.getInt(1));

            EmployeeAddress employeeTemporaryAddress = new EmployeeAddress();
            employeeTemporaryAddress.setHouseName(rs.getString(7));
            employeeTemporaryAddress.setStreetName(rs.getString(8));
            employeeTemporaryAddress.setCityName(rs.getString(9));
            employeeTemporaryAddress.setStateName(rs.getString(10));
            employeeTemporaryAddress.setPinCode(rs.getInt(11));
            employeeTemporaryAddress.setEmpID(rs.getInt(1));

            employee.setPermanentAddress(employeePermanentAddress);
            employee.setTemporaryAddress(employeeTemporaryAddress);
            return employee;
        }
    }


}
