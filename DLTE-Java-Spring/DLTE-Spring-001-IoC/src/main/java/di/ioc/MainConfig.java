package di.ioc;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainConfig {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
        Branch branch = (Branch) context.getBean("test");
        System.out.println("Bank Name: "+branch.getBankName());
    }
}
