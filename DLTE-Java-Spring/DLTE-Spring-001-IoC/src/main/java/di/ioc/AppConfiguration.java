package di.ioc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration//Classes annotated with these annotations are automatically detected by Spring's component scanning mechanism, and beans are created for them during application startup.
public class AppConfiguration {

    @Bean(name = "test")
    public Branch branch1(){
        Branch branch = new Branch();
        branch.setBankName("RBI");
        return branch;
    }




}
