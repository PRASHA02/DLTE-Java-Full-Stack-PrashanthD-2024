package debit.cards.web.mybankdebitcard;

import debit.cards.dao.security.CardSecurity;
import debit.cards.dao.security.CardSecurityServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Scanner;

@SpringBootApplication
//@PropertySource("classpath:card.properties")
public class DlteMyBankProjectWebServicesApplication {

    public static void main(String[] args) {
        SpringApplication.run(DlteMyBankProjectWebServicesApplication.class, args);

    }

}
