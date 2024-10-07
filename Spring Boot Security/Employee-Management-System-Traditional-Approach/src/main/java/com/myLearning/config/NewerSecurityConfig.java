package com.myLearning.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class NewerSecurityConfig {

    @Bean
    public UserDetailsService userDetailsService() {

        UserDetails userA = User.withUsername("UserA").password(passwordEncoder().encode("user")).roles("USER").build();
        UserDetails userB = User.withUsername("UserB").password(passwordEncoder().encode("user")).roles("USER", "ADMIN").build();
        UserDetails userC = User.withUsername("UserC").password(passwordEncoder().encode("user")).roles("USER", "ADMIN").build();
        UserDetails userD = User.withUsername("UserD").password(passwordEncoder().encode("user")).roles("USER", "ADMIN").build();
        UserDetails userE = User.withUsername("UserE").password(passwordEncoder().encode("user")).roles("USER").build();

        return new InMemoryUserDetailsManager(userA, userB, userC, userD, userE);
    }

    @Bean
   public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
       http.authorizeRequests()
                .antMatchers("/nonSecure").permitAll()
                .and()
                .authorizeRequests().antMatchers("/hello","/greet").authenticated()
                .and()
                .httpBasic();
       return http.build();
   }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
