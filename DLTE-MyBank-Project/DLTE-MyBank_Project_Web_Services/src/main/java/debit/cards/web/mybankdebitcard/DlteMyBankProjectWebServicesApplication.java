package debit.cards.web.mybankdebitcard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:webservice.properties")
public class DlteMyBankProjectWebServicesApplication {

    public static void main(String[] args) {
        SpringApplication.run(DlteMyBankProjectWebServicesApplication.class, args);
    }

}
