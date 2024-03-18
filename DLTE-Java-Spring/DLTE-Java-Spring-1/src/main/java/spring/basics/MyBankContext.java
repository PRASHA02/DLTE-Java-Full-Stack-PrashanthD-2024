package spring.basics;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyBankContext {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("myBank-branches.xml");
        Branch branch = applicationContext.getBean("branch4",Branch.class);
        System.out.println(branch.getBranchAccount());
    }
}
