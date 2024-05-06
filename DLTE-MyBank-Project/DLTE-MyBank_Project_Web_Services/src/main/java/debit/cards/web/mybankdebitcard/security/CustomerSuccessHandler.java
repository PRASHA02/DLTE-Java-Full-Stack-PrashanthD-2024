package debit.cards.web.mybankdebitcard.security;

import debit.cards.dao.security.Customer;
import debit.cards.dao.security.CustomerServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ResourceBundle;

@Component
public class CustomerSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    @Autowired
    CustomerServices customerServices;
    private static final ResourceBundle resourceBundle = ResourceBundle.getBundle("webservice");
    Logger logger= LoggerFactory.getLogger(CustomerSuccessHandler.class);

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        Customer customer= (Customer) authentication.getPrincipal();
        if(customer.getCustomerStatus().equals("active")  ){
            if (customer.getAttempts() > 1) {
                customer.setAttempts(1);
                customerServices.updateAttempts(customer);
            }
            else {
                super.setDefaultTargetUrl("/card/dashboard");
            }
        }
        else{
            logger.warn(resourceBundle.getString("max.admin"));
            super.setDefaultTargetUrl("/card/login/?errors="+ resourceBundle.getString("max.admin"));
        }
        super.onAuthenticationSuccess(request, response, authentication);
    }
}
