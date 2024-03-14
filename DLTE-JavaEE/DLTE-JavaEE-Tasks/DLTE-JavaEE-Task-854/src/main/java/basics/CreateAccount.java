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

@WebServlet("/CreateAccount/*")
public class CreateAccount extends HttpServlet {

    UserInfoServices userInfoServices;
    @Override
    public void init() throws ServletException {
        StorageTarget storageTarget= null;
        try {
            storageTarget = new DatabaseTarget();
        } catch (SQLException e) {
            e.printStackTrace();
        }
            userInfoServices=new UserInfoServices(storageTarget);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

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
