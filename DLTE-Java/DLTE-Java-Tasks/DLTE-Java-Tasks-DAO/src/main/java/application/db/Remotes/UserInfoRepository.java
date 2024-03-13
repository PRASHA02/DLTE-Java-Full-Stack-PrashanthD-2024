package application.db.Remotes;

import application.db.Entities.Customer;

import java.util.Date;


public interface UserInfoRepository {
    boolean validateUser(String username);
    void DepositAmountInto(String username,Long amount);
    void addInformation(Customer customer);
    boolean passwordValidate(String username, String password);


}
