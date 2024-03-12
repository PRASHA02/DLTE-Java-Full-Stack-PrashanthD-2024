package application.db.Remotes;

import application.db.Entities.Customer;


public interface UserInfoRepository {
    boolean validateUser(String username);
    void DepositAmountInto(String username,Long amount);
    void addInformation(Customer customer);
     boolean passwordValidate(String username, String password);

}
