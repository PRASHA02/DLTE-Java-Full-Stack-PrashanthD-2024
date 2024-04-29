package element.spring.boot.springboot.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class OfficialSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    UsersServices service;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        Users myBankOfficials= (Users) authentication.getPrincipal();
        if(myBankOfficials.getStatus()!=0){
            if(myBankOfficials.getStatus()>1){
                myBankOfficials.setAttempts(1);
                service.updateAttempts(myBankOfficials);
            }
            super.setDefaultTargetUrl("/web/index");
        }
        else{
            logger.warn("Max attempts reached contact admin");
            super.setDefaultTargetUrl("/web/?error=contact admin");
        }
        super.onAuthenticationSuccess(request, response, authentication);
    }
}
