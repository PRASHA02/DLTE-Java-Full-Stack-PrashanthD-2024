package debit.cards.web.mybankdebitcard.security;


import debit.cards.dao.security.Customer;
import debit.cards.dao.security.CustomerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/profile")
public class CustomerApi {

    @Autowired
    CustomerServices customerServices;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public Customer save(@RequestBody Customer customer){
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        return customerServices.signingUp(customer);
    }
}
