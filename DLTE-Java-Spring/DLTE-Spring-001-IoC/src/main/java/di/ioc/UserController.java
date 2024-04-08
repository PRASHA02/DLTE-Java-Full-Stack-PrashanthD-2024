package di.ioc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;


public class UserController{


        public UserServices userService;

        public UserController(UserServices userService){
            this.userService = userService;
        }

//          public void setUserService(UserServices userService) {
//            this.userService = userService;
//          }

        public void addUser(String username) {
            userService.addUser(username);
        }

}
