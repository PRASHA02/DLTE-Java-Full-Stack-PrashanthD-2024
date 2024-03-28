package element.spring.web.demo.configs;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurationSupport;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
public class SoapServiceConfiguration extends WsConfigurerAdapter {

//    final endpoint:-http://localhost:8082/transRepo/transactions.wsdl

    //conversion of xsd to wsdl
    @Bean
    public ServletRegistrationBean servletRegistrationBean(ApplicationContext applicationContext){
        MessageDispatcherServlet messageDispatcherServlet = new MessageDispatcherServlet();
        messageDispatcherServlet.setTransformWsdlLocations(true);
        messageDispatcherServlet.setApplicationContext(applicationContext);
        return new ServletRegistrationBean(messageDispatcherServlet,"/transRepo/*");
    }


    //wsdl properties defined
    @Bean(name="transactions")
    public DefaultWsdl11Definition convertToWsdl(XsdSchema xsdSchema){
        DefaultWsdl11Definition defaultWsdl11Definition = new DefaultWsdl11Definition();
        defaultWsdl11Definition.setPortTypeName("TransactionsPort");
        defaultWsdl11Definition.setTargetNamespace("http://transactions.links");
        defaultWsdl11Definition.setLocationUri("/transRepo");
        defaultWsdl11Definition.setSchema(xsdSchema);
        return  defaultWsdl11Definition;
    }


    //xsd is mentioned
    @Bean
    public XsdSchema transactionSchema(){
        return new SimpleXsdSchema(new ClassPathResource("transactions.xsd"));
    }
}
