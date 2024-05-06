package debit.cards.web.mybankdebitcard.security;


import debit.cards.dao.security.Customer;
import debit.cards.dao.security.CustomerServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ResourceBundle;

@Component
public class CustomerFailureHandler extends SimpleUrlAuthenticationFailureHandler {

       private static final ResourceBundle resourceBundle = ResourceBundle.getBundle("webservice");
        @Autowired
        CustomerServices customerServices;

        Logger logger= LoggerFactory.getLogger(CustomerFailureHandler.class);

        @Override
        public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
            String username = request.getParameter("username");

            try {
                // Check if username is not null
                if (username != null && !username.isEmpty()) {
                    Customer customer = customerServices.findByUserName(username);

                    if (customer != null) {
                        if (!"block".equals(customer.getCustomerStatus())) {
                            if (customer.getAttempts() < customer.getMaxAttempt()) {
                                customer.setAttempts(customer.getAttempts() + 1);
                                customerServices.updateAttempts(customer);
                                logger.warn(resourceBundle.getString("invalid.attempts"));
                                int leftAttempts = 4;
                                exception = new LockedException(leftAttempts - customer.getAttempts() + " " + resourceBundle.getString("attempts.taken"));
                                String error = customer.getAttempts() + " " + exception.getMessage();
                                logger.warn(error);
                                setDefaultFailureUrl("/card/login/?error=" + exception.getMessage());
                            } else {
                                customerServices.updateStatus(customer);
                                logger.warn(resourceBundle.getString("account.suspend"));
                                exception = new LockedException(resourceBundle.getString("account.suspend"));
                                setDefaultFailureUrl("/card/login/?error=" + exception.getMessage());
                            }
                        } else {
                            logger.warn(resourceBundle.getString("contact.admin"));
                            exception = new LockedException(resourceBundle.getString("contact.admin"));
                            super.setDefaultFailureUrl("/card/login/?error=" + exception.getMessage());
                        }
                    } else {
                        logger.warn(resourceBundle.getString("contact.admin"));
                        exception = new LockedException(resourceBundle.getString("incorrect.username"));
                        super.setDefaultFailureUrl("/card/login/?error=" + exception.getMessage());
                    }
                } else {
                    // Handle case where username parameter is null or empty
                    logger.warn(resourceBundle.getString("invalid.username"));
                    exception = new LockedException(resourceBundle.getString("invalid.username"));
                    super.setDefaultFailureUrl("/card/login/?error=" + exception.getMessage());
                }
            } catch (UsernameNotFoundException e) {
                logger.info(e.toString());
                logger.warn(resourceBundle.getString("contact.admin"));
                exception = new LockedException(resourceBundle.getString("incorrect.username"));
                super.setDefaultFailureUrl("/card/login/?error=" + exception.getMessage());
            }

            super.onAuthenticationFailure(request, response, exception);
        }

}
