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
import java.util.ArrayList;
import java.util.List;

@WebServlet("/readAll")
public class ReadAllEmployee extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        Gson gson = new Gson();

        try {

//            EmployeeInterface employeeInterface = new App();
            EmployeeInterface employeeInterface = null;
            resp.setContentType("application/json");
            List<Employee> employee = employeeInterface.displayEmployeeDetails();

            String response = gson.toJson(employee);
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().println(response);

        } catch (Exception e) {
            Employee emptyEmployee = new Employee();
            String response = gson.toJson(emptyEmployee);
            resp.getWriter().println(response);
        }

    }
}
