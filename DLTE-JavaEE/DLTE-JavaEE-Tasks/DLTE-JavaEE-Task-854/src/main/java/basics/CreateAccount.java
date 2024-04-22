package basics;

import application.db.Entities.Customer;
import application.db.Middlewares.DatabaseTarget;
import application.db.Remotes.StorageTarget;
import application.db.Services.UserInfoServices;
import com.google.gson.Gson;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/createAccount/*")
public class CreateAccount extends HttpServlet {

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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        try{
            Gson gson=new Gson();
            Customer customer = gson.fromJson(req.getReader(),Customer.class);
            userInfoServices.callAddInformation(customer);
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().println(customer.toString()+" has added to the records");
        }catch(Exception e){
            System.out.println(e);
        }
    }


}
