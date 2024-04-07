package di.ioc;

import org.springframework.stereotype.Service;

@Service
public class UserServices {
    public void addUser(String username) {
        System.out.println("User added: " + username);
    }
}
