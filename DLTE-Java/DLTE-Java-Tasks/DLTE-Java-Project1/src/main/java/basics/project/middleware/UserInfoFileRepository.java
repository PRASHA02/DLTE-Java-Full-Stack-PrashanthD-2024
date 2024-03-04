package basics.project.middleware;

import basics.project.entities.UserInformation;
import basics.project.remotes.UserInfoRepository;

public class UserInfoFileRepository implements UserInfoRepository {
    @Override
    public void validateUser(UserInformation userInformation) {

    }

    @Override
    public void deposit(UserInformation userInformation, Double addAmount) {

    }

    @Override
    public void updateBalance(UserInformation userInformation) {

    }
}
