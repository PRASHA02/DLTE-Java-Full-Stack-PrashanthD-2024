package web.backend;

import business.logic.App;
import com.google.gson.Gson;
import entity.backend.Employee;
import entity.backend.method.EmployeeInterface;

import javax.jws.WebParam;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/filterPinCode")
public class FilterEmployee extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Gson gson = new Gson();
        try {
            EmployeeInterface employeeInterface = new App();
            resp.setContentType("application/json");
            String pinCodeParam = req.getParameter("pinCode");
            if (pinCodeParam != null && !pinCodeParam.isEmpty()) {
                Integer pinCode = Integer.parseInt(pinCodeParam);
                List<Employee> employee = employeeInterface.findEmployeesByPincode(pinCode);
                String response = gson.toJson(employee);
                resp.setStatus(HttpServletResponse.SC_OK);
                resp.getWriter().println(response);
            } else {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.getWriter().println("Pincode parameter is missing or invalid.");
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
