package console.repository;

import java.io.IOException;
import java.sql.SQLException;

//interface for input,output and filter methods
public interface EmployeeRepository {

    void inputData() throws SQLException;
    void outputData() throws SQLException, IOException;
    void filter() throws SQLException;

}
