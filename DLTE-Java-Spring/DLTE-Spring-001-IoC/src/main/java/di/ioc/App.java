package di.ioc;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.FileSystemResource;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        BeanFactory beanFactory=new XmlBeanFactory(new FileSystemResource("spring-dispatcher.xml"));
        Branch branch=beanFactory.getBean("branch", Branch.class);
        System.out.println(branch.getBranchContact()+" "+branch.getBranchName());
        Branch branchTow=beanFactory.getBean("branch",Branch.class);
        System.out.println(branch);
    }
}
