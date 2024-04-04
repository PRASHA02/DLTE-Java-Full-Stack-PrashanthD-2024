package spring.element.autowiring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class ExecutionFieldInjection {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext();// type of Spring application context that accepts annotated classes for configuration.
        annotationConfigApplicationContext.scan("spring.element.autowiring");//scan methods of spring components Services,Components etc
        annotationConfigApplicationContext.refresh();//After specifying the packages to scan, you need to refresh the application context to load and register the beans found during component scanning.
        MyBankService myBankService = annotationConfigApplicationContext.getBean(MyBankService.class);
        System.out.println(myBankService.executeFindAll());
    }
}
