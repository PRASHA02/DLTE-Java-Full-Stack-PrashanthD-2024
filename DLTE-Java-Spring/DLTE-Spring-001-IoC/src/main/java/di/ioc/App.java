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
        Branch akashBranch=beanFactory.getBean("branch3", Branch.class);
        System.out.println(akashBranch.getBranchContact()+" "+akashBranch.getBranchName());
        Branch elroyBranch=beanFactory.getBean("branch5",Branch.class);
        System.out.println(elroyBranch);
    }
}