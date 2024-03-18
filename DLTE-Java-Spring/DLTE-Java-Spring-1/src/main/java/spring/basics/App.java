package spring.basics;

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

        BeanFactory beanFactory = new XmlBeanFactory(new FileSystemResource("myBank-branches.xml"));
        Branch branch = beanFactory.getBean("branch4",Branch.class);
        System.out.println(branch);
    }
}
