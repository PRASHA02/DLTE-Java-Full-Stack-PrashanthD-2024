package debit.cards.web.mybankdebitcard.security;

import debit.cards.dao.exceptions.CustomerException;
import debit.cards.dao.security.CardSecurity;
import debit.cards.dao.security.CardSecurityServices;
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
public class CardFailureHandler extends SimpleUrlAuthenticationFailureHandler {

       private static final ResourceBundle resourceBundle = ResourceBundle.getBundle("card");
        @Autowired
        CardSecurityServices cardSecurityServices;

        Logger logger= LoggerFactory.getLogger(CardFailureHandler.class);

        @Override
        public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
            String username = request.getParameter("username");
            try{
                CardSecurity cardSecurity = cardSecurityServices.findByUserName(username);
                if(cardSecurity!=null){
                    if (!cardSecurity.getCustomerStatus().equals("block")) {
                        if(cardSecurity.getAttempts()< cardSecurity.getMaxAttempt()){
                            cardSecurity.setAttempts(cardSecurity.getAttempts()+1);
                            cardSecurityServices.updateAttempts(cardSecurity);
                            logger.warn(resourceBundle.getString("invalid.attempts"));
                            int leftAttempts=4;
                            exception = new LockedException(leftAttempts-cardSecurity.getAttempts() + " " +resourceBundle.getString("attempts.taken"));
                            String error = cardSecurity.getAttempts() + " " + exception.getMessage();
                            logger.warn(error);
                            setDefaultFailureUrl("/card/login/?error=" + exception.getMessage());
                        }
                        else{
                            cardSecurityServices.updateStatus(cardSecurity);
                            logger.warn(resourceBundle.getString("account.suspend"));
                            exception=new LockedException(resourceBundle.getString("account.suspend"));
                            setDefaultFailureUrl("/card/login/?error=" + exception.getMessage());
                        }
                    }
                    else{
                        logger.warn(resourceBundle.getString("contact.admin"));
                        exception = new LockedException(resourceBundle.getString("contact.admin"));
                        super.setDefaultFailureUrl("/card/login/?error=" + exception.getMessage());
                    }
                }else {
                    logger.warn(resourceBundle.getString("contact.admin"));
                    exception = new LockedException(resourceBundle.getString("incorrect.username"));
                    super.setDefaultFailureUrl("/card/login/?error=" + exception.getMessage());
                }
            }catch (UsernameNotFoundException e){
                logger.info(e.toString());
                logger.warn(resourceBundle.getString("contact.admin"));
                exception = new LockedException(resourceBundle.getString("incorrect.username"));
                super.setDefaultFailureUrl("/card/login/?error=" + exception.getMessage());
            }
            super.onAuthenticationFailure(request, response, exception);
        }

}
