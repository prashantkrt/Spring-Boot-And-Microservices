package com.mylearning.springbootmockmvc2.service;

import com.mylearning.springbootmockmvc2.entity.User;
import com.mylearning.springbootmockmvc2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }
}
