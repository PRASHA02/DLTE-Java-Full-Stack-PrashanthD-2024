package spring.element.autowiring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class ExecutionFieldInjection {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext();
        annotationConfigApplicationContext.scan("spring.element.autowiring");
        annotationConfigApplicationContext.refresh();
        MyBankService myBankService = annotationConfigApplicationContext.getBean(MyBankService.class);
        System.out.println(myBankService.executeFindAll());
    }
}
