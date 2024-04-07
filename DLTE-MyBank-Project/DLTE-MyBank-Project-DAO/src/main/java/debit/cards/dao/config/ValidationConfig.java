package debit.cards.dao.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

//These are used to generate bean definitions and manage them.
@Configuration
public class ValidationConfig {
    //method and register the returned object as a bean in the application context.
    @Bean
    public MessageSource messageSource(){
        ResourceBundleMessageSource resourceBundleMessageSource = new ResourceBundleMessageSource();
        resourceBundleMessageSource.setBasename("application");
        resourceBundleMessageSource.setDefaultEncoding("UTF-8");
        return  resourceBundleMessageSource;
    }

    public LocalValidatorFactoryBean getValidator(MessageSource messageSource){
        LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
        localValidatorFactoryBean.setValidationMessageSource(messageSource);
        return localValidatorFactoryBean;
    }

}

