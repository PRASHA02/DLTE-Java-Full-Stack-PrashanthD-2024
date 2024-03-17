package web;


import application.db.Entities.Customer;
import application.db.Middlewares.DatabaseTarget;
import application.db.Remotes.StorageTarget;
import application.db.Services.UserInfoServices;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebService()
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class AccountSoap {
    private UserInfoServices userInfoServices;
    public AccountSoap() throws SQLException {
        StorageTarget storageTarget = new DatabaseTarget();
        userInfoServices = new UserInfoServices(storageTarget);
    }

    @WebMethod
    @WebResult(name="GroupAccount")
    public GroupAccount findAllUsername(@WebParam(name="String") String username){
        Customer customer =  userInfoServices.callFindusername(username);
        GroupAccount groupAccount = new GroupAccount();
        List<Customer> customerList = new ArrayList<>();
        customerList.add(customer);
        groupAccount.setCustomerList(customerList);
        return groupAccount;
    }
}
