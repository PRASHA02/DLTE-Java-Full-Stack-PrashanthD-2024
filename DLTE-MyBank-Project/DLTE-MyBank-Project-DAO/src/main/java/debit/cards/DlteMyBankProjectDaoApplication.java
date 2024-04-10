package debit.cards;

import debit.cards.dao.entities.DebitCard;
import debit.cards.dao.services.DebitCardServices;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.sql.SQLException;
import java.sql.SQLOutput;
import java.util.Date;

@SpringBootApplication
public class DlteMyBankProjectDaoApplication {

    public static void main(String[] args) throws SQLException {

        ConfigurableApplicationContext context=  SpringApplication.run(DlteMyBankProjectDaoApplication.class, args);

        DebitCardServices debitCardServices=context.getBean(DebitCardServices.class);
//        System.out.println(debitCardServices.getDebitCard());
        DebitCard debitCard = new DebitCard(3692468135796670L,78909876543530L,
                123670,
                123,
                1234,
        new Date(2024,04,3),
        "active",
        100000.0,
        40000000.0);
        System.out.println(debitCardServices.updateDebitLimit(debitCard));
    }



}
