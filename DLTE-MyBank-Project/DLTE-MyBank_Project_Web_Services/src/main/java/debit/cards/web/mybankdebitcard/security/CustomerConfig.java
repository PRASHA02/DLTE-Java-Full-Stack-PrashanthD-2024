package debit.cards.web.mybankdebitcard.security;

import debit.cards.dao.security.CustomerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
public class CustomerConfig {

    @Autowired
    CustomerServices customerServices;

    AuthenticationManager authenticationManager;

    @Autowired
    CustomerFailureHandler customerFailureHandler;
    @Autowired
    CustomerSuccessHandler customerSuccessHandler;

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    //CORS Configuration
    @Bean
    public CorsConfigurationSource corsConfigurationSource(){
        CorsConfiguration configuration=new CorsConfiguration();
        configuration.setAllowedOriginPatterns(Arrays.asList("http://127.0.0.1:5500/"));
        configuration.addAllowedMethod("*");
        configuration.addAllowedHeader("*");
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source=new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**",configuration);
        return source;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.httpBasic();

        httpSecurity.formLogin().loginPage("/card/login").
                usernameParameter("username").
                failureHandler(customerFailureHandler).
                successHandler(customerSuccessHandler);
        httpSecurity.csrf().disable();

        httpSecurity.authorizeRequests().antMatchers("http://localhost:8082/english?lang=en").permitAll();

        httpSecurity.cors();
        httpSecurity.authorizeRequests().antMatchers("/card/login/**").permitAll();

        httpSecurity.authorizeRequests().antMatchers("/images/**").permitAll();
        httpSecurity.authorizeRequests().antMatchers("/css/**").permitAll();
        httpSecurity.authorizeRequests().antMatchers("/profile/register").permitAll();
        httpSecurity.authorizeRequests().antMatchers("/v3/api-docs").permitAll();
        //httpSecurity.authorizeRequests().antMatchers("/debitcardrepo/debitcard.wsdl").permitAll();
        httpSecurity.authorizeRequests().anyRequest().authenticated();


        AuthenticationManagerBuilder builder = httpSecurity.getSharedObject(AuthenticationManagerBuilder.class);
        builder.userDetailsService(customerServices);
        authenticationManager = builder.build();
        httpSecurity.authenticationManager(authenticationManager);
        return httpSecurity.build();
    }
}
