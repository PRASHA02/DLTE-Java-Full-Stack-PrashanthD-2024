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
public class CustomerServices implements UserDetailsService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    Logger logger = LoggerFactory.getLogger(CustomerServices.class);

    public Customer signingUp(Customer customer){
        jdbcTemplate.update("insert into mybank_app_customer (CUSTOMER_NAME, CUSTOMER_ADDRESS, CUSTOMER_STATUS, CUSTOMER_CONTACT, USERNAME, PASSWORD, ATTEMPTS) values(?,?,?,?,?,?,?)",new Object[]{customer.getCustomerName(), customer.getCustomerAddress(), customer.getCustomerStatus(), customer.getCustomerContact(), customer.getUsername(), customer.getPassword(), customer.getAttempts()});

        return customer;
    }
    //Listing the entire customer table details and performing the filter based on the username
    public Customer findByUserName(String username) {
            List<Customer> customerList = jdbcTemplate.query(
                    "SELECT * FROM mybank_app_customer",
                    new BeanPropertyRowMapper<>(Customer.class));
            return filterByUserName(customerList,username);
        }

    public void updateAttempts(Customer customer){
        jdbcTemplate.update("update mybank_app_customer set attempts = ? where username = ?",new Object[]{customer.getAttempts(), customer.getUsername()});
        logger.info("Attempts are Updated");
    }
    public void updateStatus(Customer customer){
        jdbcTemplate.update("update mybank_app_customer set customer_status = 'block' where username = ?",new Object[]{customer.getUsername()});
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
        Customer customer = findByUserName(username);
        if(customer ==null)
            throw new UsernameNotFoundException(username);
        return customer;
    }

    public Customer filterByUserName(List<Customer> customerList, String username){
        // Filter the list based on the provided username
        List<Customer> filteredCustomers = customerList.stream()
                .filter(customer -> customer.getUsername().equals(username))
                .collect(Collectors.toList());
        if (!filteredCustomers.isEmpty()) {
            return filteredCustomers.get(0); // Return the first matching customer
        } else {
            return null; // Return null if no customer found
        }
    }

}
