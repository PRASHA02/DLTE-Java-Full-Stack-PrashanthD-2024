package element.spring.boot.springboot.security;


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
public class UserSecureConfig {

    @Autowired
    UsersServices usersServices;

    AuthenticationManager authenticationManager;

    @Autowired
    OfficialFailureHandler officialsFailureHandler;
    @Autowired
    OfficialSuccessHandler officialsSuccessHandler;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.httpBasic();


        httpSecurity.authorizeRequests().antMatchers("/profile/register").permitAll();
        httpSecurity.authorizeRequests().antMatchers("/web/*").permitAll();
        httpSecurity.formLogin().loginPage("/web/").
                usernameParameter("username").
                failureHandler(officialsFailureHandler).
                successHandler(officialsSuccessHandler);
        httpSecurity.csrf().disable();

        httpSecurity.authorizeRequests().antMatchers("/profile/register").permitAll();
        httpSecurity.authorizeRequests().antMatchers("/transaction/findAmount/*").hasAuthority("customer");
        httpSecurity.authorizeRequests().antMatchers("/transaction/findTo/*").hasAuthority("customer");
        httpSecurity.authorizeRequests().antMatchers("/transaction/findBy/*").hasAuthority("customer");
        httpSecurity.authorizeRequests().antMatchers(HttpMethod.DELETE).hasAuthority("admin");
        httpSecurity.authorizeRequests().antMatchers(HttpMethod.PUT).hasAnyAuthority("manager","admin");

        httpSecurity.authorizeRequests().anyRequest().authenticated();


        AuthenticationManagerBuilder builder=httpSecurity.
                getSharedObject(AuthenticationManagerBuilder.class);
        builder.userDetailsService(usersServices);
        authenticationManager=builder.build();
        httpSecurity.authenticationManager(authenticationManager);

        return httpSecurity.build();
    }
}
