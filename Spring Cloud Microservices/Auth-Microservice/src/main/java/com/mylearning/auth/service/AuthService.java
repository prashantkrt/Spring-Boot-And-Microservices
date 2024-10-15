package com.mylearning.auth.service;

import com.mylearning.auth.entity.UserCredentials;
import com.mylearning.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    public String register(UserCredentials userCredentials) {
        userCredentials.setPassword(passwordEncoder.encode(userCredentials.getPassword()));
        userRepository.save(userCredentials);
        return "User successfully registered";
    }

    public String generateToken(String username) {
        return jwtService.generateJwtToken(username);
    }

    public void validateToken(String token) {
        jwtService.validateToken(token);
    }


}
