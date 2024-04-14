package debit.cards.web.mybankdebitcard.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class CardSecurityConfig {

    @Autowired
    CardSecurityServices cardSecurityServices;

    AuthenticationManager authenticationManager;

    @Autowired
    CardFailureHandler cardFailureHandler;
    @Autowired
    CardSuccessHandler cardSuccessHandler;

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.httpBasic();
        httpSecurity.formLogin()
                .usernameParameter("username")
                .failureHandler(cardFailureHandler).
                successHandler(cardSuccessHandler);
        httpSecurity.csrf().disable();
        httpSecurity.authorizeRequests().antMatchers("/profile/register").permitAll();
        httpSecurity.authorizeRequests().anyRequest().authenticated();


        AuthenticationManagerBuilder builder = httpSecurity.getSharedObject(AuthenticationManagerBuilder.class);
        builder.userDetailsService(cardSecurityServices);
        authenticationManager = builder.build();
        httpSecurity.authenticationManager(authenticationManager);
        return httpSecurity.build();
    }
}