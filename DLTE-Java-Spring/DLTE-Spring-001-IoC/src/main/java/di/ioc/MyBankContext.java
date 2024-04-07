package di.ioc;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyBankContext {
    public static void main(String[] args) {
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("mybank-branches.xml");
        Branch branch=applicationContext.getBean("bname", Branch.class);
        System.out.println(branch.getIfsCode()+" "+branch.getBranchName());
    }
}
