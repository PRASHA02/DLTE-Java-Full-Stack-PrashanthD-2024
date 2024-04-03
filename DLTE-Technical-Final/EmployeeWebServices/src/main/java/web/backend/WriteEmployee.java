package web.backend;

import business.logic.App;
import com.google.gson.Gson;
import entity.backend.Employee;
import entity.backend.method.EmployeeInterface;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/writeEmployee")
public class WriteEmployee extends HttpServlet {

    @Override
    protected  void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Gson gson = new Gson();
        try {
            EmployeeInterface employeeInterface = new App();
            Employee employee = gson.fromJson(req.getReader(),Employee.class);
            employeeInterface.writeEmployeeDetails(employee);
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().println(employee.getEmpID()+" has added to the records");
            System.out.println(resp.toString());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
