package com.myLearning.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) // for method level security for role-based auth
public class SecurityConfig {

    @Bean
    public UserDetailsService userDetailsService() {

        UserDetails userHR = User.withUsername("UserHR").password(passwordEncoder().encode("user")).roles("HR").build();
        UserDetails userA = User.withUsername("UserA").password(passwordEncoder().encode("user")).roles("USER").build();
        UserDetails UserManager1 = User.withUsername("UserManager1").password(passwordEncoder().encode("user")).roles("MANAGER").build();
        UserDetails UserManager2 = User.withUsername("UserManager2").password(passwordEncoder().encode("user")).roles("MANAGER").build();
        UserDetails UserUserManager = User.withUsername("UserUserManager").password(passwordEncoder().encode("user")).roles("USER", "MANAGER").build();
        UserDetails UserAll = User.withUsername("UserAll").password(passwordEncoder().encode("user")).roles("MANAGER", "USER", "HR").build();

        return new InMemoryUserDetailsManager(userHR, userA, UserManager1, UserManager2, UserUserManager, UserAll);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/demo/nonSecure", "/employees/welcome").permitAll()
                .and()
                .authorizeRequests().antMatchers("/hello", "/greet", "/employees/**").authenticated()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .httpBasic();
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
