package di.ioc;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;
@Configuration
@ComponentScan(basePackages = "di.ioc")
public class MainApp {

        public static void main(String[] args) {
            ApplicationContext context = new ClassPathXmlApplicationContext("test.xml");
            UserController userController = (UserController) context.getBean("userController");
            // Call the method on UserController
            userController.addUser("Prashanth");
        }


}
