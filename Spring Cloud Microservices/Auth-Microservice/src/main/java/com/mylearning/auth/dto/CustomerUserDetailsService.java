package com.mylearning.auth.dto;

import com.mylearning.auth.entity.UserCredentials;
import com.mylearning.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CustomerUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserCredentials> userCredentials = userRepository.findByUsername(username);
        return userCredentials.map(CustomerUserDetails::new).orElseThrow(() -> new RuntimeException("User details are invalid!!!"));
    }
}
