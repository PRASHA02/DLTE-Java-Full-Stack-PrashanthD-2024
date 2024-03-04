package basics.project.remotes;

import basics.project.entities.UserInformation;

public interface UserInfoRepository {

     void validateUser(UserInformation userInformation);

     void deposit(UserInformation userInformation,Double addAmount);

     void updateBalance(UserInformation userInformation);

}
