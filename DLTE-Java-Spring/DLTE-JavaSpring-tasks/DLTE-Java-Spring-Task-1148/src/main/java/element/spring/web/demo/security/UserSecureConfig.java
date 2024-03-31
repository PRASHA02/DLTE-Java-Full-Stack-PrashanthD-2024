package element.spring.web.demo.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class UserSecureConfig {

    @Autowired
    UsersServices usersServices;

    AuthenticationManager authenticationManager;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.httpBasic();
        httpSecurity.formLogin();
        httpSecurity.csrf().disable();

        httpSecurity.authorizeRequests().antMatchers("/profile/register").permitAll();

        httpSecurity.authorizeRequests().antMatchers("/transRepo/newTransactionRequest/*").hasAuthority("admin");

        httpSecurity.authorizeRequests().antMatchers("/transRepo/filterBySenderRequest/*").hasAuthority("customer");
        httpSecurity.authorizeRequests().antMatchers("/transRepo/filterByReceiverRequest/*").hasAuthority("customer");
        httpSecurity.authorizeRequests().antMatchers("/transRepo/filterByAmountRequest/*").hasAuthority("customer");
        httpSecurity.authorizeRequests().antMatchers("/transRepo/removeByDateRequest/*").hasAuthority("admin");
        httpSecurity.authorizeRequests().antMatchers("/transRepo/updateRemarksTransactionRequest/*").hasAnyAuthority("manager","admin");

        httpSecurity.authorizeRequests().anyRequest().authenticated();


        AuthenticationManagerBuilder builder=httpSecurity.
                getSharedObject(AuthenticationManagerBuilder.class);
        builder.userDetailsService(usersServices);
        authenticationManager=builder.build();
        httpSecurity.authenticationManager(authenticationManager);

        return httpSecurity.build();
    }
}
