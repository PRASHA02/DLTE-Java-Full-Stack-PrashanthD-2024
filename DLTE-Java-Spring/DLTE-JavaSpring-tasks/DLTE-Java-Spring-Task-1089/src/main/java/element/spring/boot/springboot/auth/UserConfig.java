//package element.spring.boot.springboot.auth;
//
//import element.spring.boot.springboot.security.OfficialFailureHandler;
//import element.spring.boot.springboot.security.OfficialSuccessHandler;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.AuthenticationFailureHandler;
//import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
//
////levels of security is mentioned in this
//@Configuration
//public class UserConfig {
//
//    @Autowired
//    MyBankUserServices service;
//
//    AuthenticationManager authenticationManager;
//
//
//
//
//    @Bean
//    public PasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder();
//    }
//
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
//        httpSecurity.httpBasic();
//
//        httpSecurity.csrf().disable();
//
//        httpSecurity.authorizeRequests().antMatchers("/profile/register").permitAll();// 3rd level
//        httpSecurity.authorizeRequests().antMatchers("/web/").permitAll();
//        httpSecurity.logout().permitAll();
//        httpSecurity.formLogin();
//
//        //httpSecurity.authorizeRequests().antMatchers(HttpMethod.POST).hasAuthority("admin");
//
//        httpSecurity.authorizeRequests().anyRequest().authenticated();//3rd level
//
//
//        AuthenticationManagerBuilder builder=httpSecurity.
//                getSharedObject(AuthenticationManagerBuilder.class);
//        builder.userDetailsService(service);
//        authenticationManager=builder.build();
//        httpSecurity.authenticationManager(authenticationManager);
//
//        return httpSecurity.build();
//    }
//}
