package com.mylearning.multidb.service;

import com.mylearning.multidb.model.userModel.User;
import com.mylearning.multidb.repository.db3.UserRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
