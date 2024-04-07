package debit.cards;

import debit.cards.dao.services.DebitCardServices;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.sql.SQLException;

@SpringBootApplication
public class DlteMyBankProjectDaoApplication {

    public static void main(String[] args) throws SQLException {

        ConfigurableApplicationContext context=  SpringApplication.run(DlteMyBankProjectDaoApplication.class, args);

        DebitCardServices debitCardServices=context.getBean(DebitCardServices.class);
        System.out.println(debitCardServices.getDebitCard());
    }

}
