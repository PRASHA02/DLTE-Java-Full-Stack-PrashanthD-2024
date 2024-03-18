package basics;

import application.db.Entities.Customer;
import application.db.Middlewares.DatabaseTarget;
import application.db.Remotes.StorageTarget;
import application.db.Services.UserInfoServices;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/findAll/")
public class FindAll extends HttpServlet {
   public StorageTarget storageTarget;
    public UserInfoServices userInfoServices;

    @Override
    public void init() throws ServletException {
        try {
            storageTarget = new DatabaseTarget();
        } catch (SQLException e) {

        }
        userInfoServices = new UserInfoServices(storageTarget);
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("application/json");
        try{
            List<Customer> customerArrayList = userInfoServices.callFindAll();
            Gson gson = new Gson();

            String message = gson.toJson(customerArrayList);
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().println(message);
        }catch(Exception e){
            System.out.println(e);
        }



    }

}
