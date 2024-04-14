package console.technical.spring.remotes;

import org.springframework.stereotype.Repository;

import java.sql.SQLException;

//interface for input,output and filter methods
@Repository
public interface EmployeeRepository {

    void inputData() throws SQLException;
    void outputData() throws SQLException;
    void filter() throws SQLException;

}
