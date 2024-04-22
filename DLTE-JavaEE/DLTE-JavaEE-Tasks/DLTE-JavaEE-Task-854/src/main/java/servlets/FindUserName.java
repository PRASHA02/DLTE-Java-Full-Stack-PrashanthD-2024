package servlets;

import application.db.Entities.Customer;
import application.db.Exception.UserNotFoundException;
import application.db.Middlewares.DatabaseTarget;
import application.db.Remotes.StorageTarget;
import application.db.Services.UserInfoServices;
import com.google.gson.Gson;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/findUsername/*")
public class FindUserName extends HttpServlet {
    private StorageTarget storageTarget;
    private UserInfoServices userInfoServices;
    @Override
    public void init() throws ServletException {
        try {
            storageTarget = new DatabaseTarget();
        } catch (SQLException e) {

        }
        userInfoServices = new UserInfoServices(storageTarget);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            String username = req.getParameter("username");

            Customer customer = userInfoServices.callFindusername(username);
            RequestDispatcher dispatcher = req.getRequestDispatcher("viewByUsername.jsp");
            req.setAttribute("myCards",customer);
            dispatcher.include(req,resp);
        }catch(UserNotFoundException ex){
            resp.getWriter().println(ex);
        }catch (Exception ex){
            System.out.println(ex);
        }
    }
}
