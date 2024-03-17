package web;

import application.db.Middlewares.DatabaseTarget;
import application.db.Remotes.StorageTarget;
import application.db.Services.UserInfoServices;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.sql.SQLException;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class SoapAccount {
    private UserInfoServices userInfoServices;
    public SoapAccount() throws SQLException {
        StorageTarget storageTarget = new DatabaseTarget();
        userInfoServices =  new UserInfoServices(storageTarget);

        
    }
}
