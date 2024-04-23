package servlets;

import application.db.Middlewares.DatabaseTarget;
import application.db.Remotes.StorageTarget;
import application.db.Services.UserInfoServices;
import oracle.jdbc.driver.OracleDriver;
import org.slf4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.*;
import java.util.ResourceBundle;

@WebServlet("/login")
public class AuthorizeServlet extends HttpServlet {

    private StorageTarget storageTarget;
    private UserInfoServices userInfoServices;
    public ResourceBundle resourceBundle;
    @Override
    public void init() throws ServletException {
        try {
            storageTarget = new DatabaseTarget();
            userInfoServices = new UserInfoServices(storageTarget);
            resourceBundle = ResourceBundle.getBundle("app");
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        try{
            Driver driver = new OracleDriver();
            DriverManager.registerDriver(driver);
            Connection connection = DriverManager.getConnection(resourceBundle.getString("db.url"), resourceBundle.getString("db.user"), resourceBundle.getString("db.pass"));
            String query = "select * from user_info where username=? and password=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,username);
            preparedStatement.setString(2,password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                HttpSession httpSession = req.getSession();
                httpSession.setAttribute("username",username);
                httpSession.setAttribute("password",password);
                resp.sendRedirect("dashboard.jsp");
            }else{
                resp.sendRedirect("index.jsp");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
