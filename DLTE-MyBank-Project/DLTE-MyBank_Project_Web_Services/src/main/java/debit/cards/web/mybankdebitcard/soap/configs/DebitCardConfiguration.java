package debit.cards.web.mybankdebitcard.soap.configs;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;

import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;


//http://localhost:8082/debitcardrepo/debitcard.wsdl
@EnableWs
@Configuration
public class DebitCardConfiguration extends WsConfigurerAdapter {

    @Bean
    //convert xsd to wsdl
    public ServletRegistrationBean servletRegistrationBean(ApplicationContext applicationContext){
        MessageDispatcherServlet messageDispatcherServlet = new MessageDispatcherServlet();
        messageDispatcherServlet.setTransformWsdlLocations(true);
        messageDispatcherServlet.setApplicationContext(applicationContext);
        return new ServletRegistrationBean(messageDispatcherServlet,"/debitcardrepo/*");
    }
    //wsdl properties
    @Bean(name = "debitcard")
    public DefaultWsdl11Definition convertToWsdl(XsdSchema xsdSchema){
        DefaultWsdl11Definition defaultWsdl11Definition = new DefaultWsdl11Definition();
        defaultWsdl11Definition.setPortTypeName("DebitCardPort");
        defaultWsdl11Definition.setTargetNamespace("http://debitcard.links");
        defaultWsdl11Definition.setLocationUri("/debitcardrepo");
        defaultWsdl11Definition.setSchema(xsdSchema);
        return defaultWsdl11Definition;
    }


    //xsd is mentioned
    @Bean
    public XsdSchema debitCardSchema(){
        return new SimpleXsdSchema(new ClassPathResource("debitcard.xsd"));
    }



}
