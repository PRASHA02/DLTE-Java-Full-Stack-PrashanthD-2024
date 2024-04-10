package console.technical.spring.repository;

import java.sql.SQLException;

//interface for input,output and filter methods
public interface EmployeeRepository {

    void inputData() throws SQLException;
    void outputData() throws SQLException;
    void filter() throws SQLException;

}
