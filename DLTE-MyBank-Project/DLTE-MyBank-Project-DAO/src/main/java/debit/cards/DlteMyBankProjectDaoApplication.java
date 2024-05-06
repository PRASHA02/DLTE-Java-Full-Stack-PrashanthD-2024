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

        SpringApplication.run(DlteMyBankProjectDaoApplication.class, args);

    }





}
