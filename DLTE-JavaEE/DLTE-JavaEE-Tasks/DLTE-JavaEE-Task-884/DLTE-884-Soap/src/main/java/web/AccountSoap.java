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
import java.util.List;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class AccountSoap {
    private UserInfoServices userInfoServices;
    public AccountSoap() throws SQLException {
        StorageTarget storageTarget = new DatabaseTarget();
        userInfoServices = new UserInfoServices(storageTarget);
    }

    @WebMethod
    @WebResult(name="Customer")
    public GroupAccount getUsername(@WebParam(name="String") String username){
        GroupAccount groupAccount = new GroupAccount();
        List<Customer> customerList = (List<Customer>) userInfoServices.callFindusername(username);
        groupAccount.setCustomerList((Customer) customerList);
        return groupAccount;
    }
}
