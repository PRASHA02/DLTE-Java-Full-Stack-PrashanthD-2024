package debit.cards.web.mybankdebitcard.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CardFailureHandler extends SimpleUrlAuthenticationFailureHandler {

        @Autowired
        CardSecurityServices cardSecurityServices;

        Logger logger= LoggerFactory.getLogger(CardFailureHandler.class);

        @Override
        public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
            String username = request.getParameter("username");
            CardSecurity cardSecurity = cardSecurityServices.findByUserName(username);
            if(cardSecurity!=null){
                if (!cardSecurity.getCustomerStatus().equals("block")) {
                    if(cardSecurity.getAttempts()< cardSecurity.getMaxAttempt()){
                        cardSecurity.setAttempts(cardSecurity.getAttempts()+1);
                        cardSecurityServices.updateAttempts(cardSecurity);
                        logger.warn("Invalid credentials and attempts taken");
                        exception=new LockedException("Attempts are taken");
                    }
                    else{
                        cardSecurityServices.updateStatus(cardSecurity);
                        exception=new LockedException("Max Attempts reached account is suspended");
                    }
                }
                else{
                    logger.warn("Account suspended contact admin to redeem");
                }
            }
            super.setDefaultFailureUrl("/login?error=true");
            super.onAuthenticationFailure(request, response, exception);
        }

}
