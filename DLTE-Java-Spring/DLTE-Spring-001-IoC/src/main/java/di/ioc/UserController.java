package di.ioc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {

        private UserServices userService;
        @Autowired
        public void setUserService(UserServices userService) {
            this.userService = userService;
        }

        public void addUser(String username) {
            userService.addUser(username);
        }

}
