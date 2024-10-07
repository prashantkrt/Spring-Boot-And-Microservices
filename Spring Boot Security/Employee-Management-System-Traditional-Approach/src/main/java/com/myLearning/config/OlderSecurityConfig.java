package com.myLearning.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/*
 * Legacy Approach for Security suitable for spring 2x version
 *
 * */

//@Configuration
//@EnableWebSecurity
//public class OlderSecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//
//        auth.inMemoryAuthentication()
//                .withUser("UserA")
//                .password(getPasswordEncoder().encode("user"))
//                .roles("USER","ADMIN");
//
//        auth.inMemoryAuthentication()
//                .withUser("UserB")
//                .password(getPasswordEncoder().encode("user"))
//                .roles("USER","ADMIN");
//
//        auth.inMemoryAuthentication()
//                .withUser("admin")
//                .password(getPasswordEncoder().encode("admin"))
//                .roles("ADMIN");
//    }
//
//    // there is a direct class use it
//    @Bean
//    public PasswordEncoder getPasswordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .antMatchers("/nonSecure").permitAll()
//                .and()
//                .authorizeRequests().antMatchers("/hello","/greet").authenticated()
//                .and()
//                //.formLogin()
//                .httpBasic();
//          /*
//          *
//          * form login proper login page will be coming
//          * httpBasic pop up will come and ask for username and pass
//          * */
//    }
//}
