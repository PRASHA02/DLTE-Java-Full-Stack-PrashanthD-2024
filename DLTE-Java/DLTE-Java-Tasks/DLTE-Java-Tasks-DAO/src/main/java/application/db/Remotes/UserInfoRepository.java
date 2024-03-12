package application.db.Remotes;


import application.db.Entities.Customer;

import java.sql.SQLException;

public interface UserInfoRepository {
    Customer validateUser(String username,String password) throws SQLException;
    void DepositAmountInto(String username,String password,Long amount);
    void addInformation(Customer customer);
}
