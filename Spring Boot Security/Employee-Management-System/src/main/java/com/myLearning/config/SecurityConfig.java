package com.myLearning.config;

import com.myLearning.service.EmployeeUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) // for method level security for role-based auth
public class SecurityConfig {

    @Bean
    public UserDetailsService userDetailsService() {

//        UserDetails userHR = User.withUsername("UserHR").password(passwordEncoder().encode("user")).roles("HR").build();
//        UserDetails userA = User.withUsername("UserA").password(passwordEncoder().encode("user")).roles("USER").build();
//        UserDetails UserManager1 = User.withUsername("UserManager1").password(passwordEncoder().encode("user")).roles("MANAGER").build();
//        UserDetails UserManager2 = User.withUsername("UserManager2").password(passwordEncoder().encode("user")).roles("MANAGER").build();
//        UserDetails UserUserManager = User.withUsername("UserUserManager").password(passwordEncoder().encode("user")).roles("USER", "MANAGER").build();
//        UserDetails UserAll = User.withUsername("UserAll").password(passwordEncoder().encode("user")).roles("MANAGER", "USER", "HR").build();
//
//        return new InMemoryUserDetailsManager(userHR, userA, UserManager1, UserManager2, UserUserManager, UserAll);
        return new EmployeeUserDetailsService();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/employees/welcome", "/employees/create").permitAll()
                .and()
                .authorizeRequests().antMatchers("/employees/**").authenticated()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider())
                .httpBasic();
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);//2^12 rounds
    }

    // just for learning even if you don't create bean for AuthManager it will work fine
    // filter(DelegatingFilterProxy) coverts request to Authentication,
    // Authentication has Credential(password) and Principal(currently authenticated user)
    // AuthManager has method ⇒ public Authentication authenticate(Authentication authentication) {}
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }


    // AuthenticationProvider for Username and password => DaoAuthenticationProvider
    // AuthenticationProvider for Oauth based
    // Authentication provider for token based
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }
}
