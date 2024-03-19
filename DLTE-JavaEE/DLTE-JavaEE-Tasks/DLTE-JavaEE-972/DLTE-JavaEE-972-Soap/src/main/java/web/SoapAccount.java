package web;

import application.db.Entities.Customer;
import application.db.Middlewares.DatabaseTarget;
import application.db.Remotes.StorageTarget;
import application.db.Services.UserInfoServices;
import com.google.gson.Gson;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.servlet.http.HttpServletResponse;
import java.beans.Customizer;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class SoapAccount {

    public UserInfoServices userInfoServices;
    public SoapAccount() throws SQLException {
        StorageTarget storageTarget = new DatabaseTarget();
        userInfoServices = new UserInfoServices(storageTarget);
    }

    @WebMethod
    @WebResult(name = "findAll")
    public String FindAll(){
        List<Customer> customerArrayList = userInfoServices.callFindAll();
        Gson gson = new Gson();

        String message = gson.toJson(customerArrayList);
        return message;
    }
    @WebMethod
    @WebResult(name="findByUserName")
    public GroupAccount findUser(@WebParam(name = "String") String username){
        GroupAccount groupAccount = new GroupAccount();
        Customer customer = userInfoServices.callFindusername(username);
        List<Customer> customerList = new ArrayList<>();
        customerList.add(customer);
        groupAccount.setCustomerList(customerList);
        return groupAccount;
    }

    @WebMethod
    @WebResult(name="findByDateUserName")
    public String findByDateUserName(@WebParam(name="String") String username,@WebParam(name = "Date") String date){
        List<Customer> customerList = userInfoServices.callTransactionByDate(username,date);
        Gson gson = new Gson();
        String message = gson.toJson(customerList);
        return message;
    }

}
