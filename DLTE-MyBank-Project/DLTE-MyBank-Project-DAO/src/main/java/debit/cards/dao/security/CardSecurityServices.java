package debit.cards.dao.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CardSecurityServices implements UserDetailsService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    Logger logger = LoggerFactory.getLogger(CardSecurityServices.class);

    public CardSecurity signingUp(CardSecurity cardSecurity){
        jdbcTemplate.update("insert into mybank_app_customer (CUSTOMER_NAME, CUSTOMER_ADDRESS, CUSTOMER_STATUS, CUSTOMER_CONTACT, USERNAME, PASSWORD, ATTEMPTS) values(?,?,?,?,?,?,?)",new Object[]{cardSecurity.getCustomerName(),cardSecurity.getCustomerAddress(),cardSecurity.getCustomerStatus(),cardSecurity.getCustomerContact(),cardSecurity.getUsername(),cardSecurity.getPassword(), cardSecurity.getAttempts()});

        return cardSecurity;
    }
    //Listing the entire customer table details and performing the filter based on the username
    public CardSecurity findByUserName(String username) {
            List<CardSecurity> customerList = jdbcTemplate.query(
                    "SELECT * FROM mybank_app_customer",
                    new BeanPropertyRowMapper<>(CardSecurity.class));
            return filterByUserName(customerList,username);
        }

    public void updateAttempts(CardSecurity cardSecurity){
        jdbcTemplate.update("update mybank_app_customer set attempts = ? where username = ?",new Object[]{cardSecurity.getAttempts(),cardSecurity.getUsername()});
        logger.info("Attempts are Updated");
    }
    public void updateStatus(CardSecurity cardSecurity){
        jdbcTemplate.update("update mybank_app_customer set customer_status = 'block' where username = ?",new Object[]{cardSecurity.getUsername()});
        logger.info("Status has changed");
    }
   //For getting particular Account owner username
    public String getAccountOwnerUsername(Long accountNumber) {
        try {
            // Query to fetch the username of the account owner based on the account number
            String sql = "SELECT c.username FROM mybank_app_customer c JOIN mybank_app_account a ON c.customer_id = a.customer_id  JOIN mybank_app_debitcard d ON a.account_number = d.account_number WHERE d.account_number =  ?";
            String ownerUsername = jdbcTemplate.queryForObject(sql, new Object[]{accountNumber}, String.class);
            return ownerUsername;
        } catch (EmptyResultDataAccessException e) {
            return null;
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception for debugging
            return null;
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        CardSecurity cardSecurity = findByUserName(username);
        if(cardSecurity==null)
            throw new UsernameNotFoundException(username);
        return cardSecurity;
    }

    public CardSecurity filterByUserName( List<CardSecurity> customerList,String username){
        // Filter the list based on the provided username
        List<CardSecurity> filteredCustomers = customerList.stream()
                .filter(customer -> customer.getUsername().equals(username))
                .collect(Collectors.toList());
        if (!filteredCustomers.isEmpty()) {
            return filteredCustomers.get(0); // Return the first matching customer
        } else {
            return null; // Return null if no customer found
        }
    }

}
