package com.mylearning.userservice.service;

import com.mylearning.userservice.entity.User;
import com.mylearning.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User addNewUser(User user) {
        return userRepository.save(user);
    }

    public User getUser(int userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("user not found with id " + userId));
    }

    public User updateAccountStatus(int userId, double usedAmount) {
        User userDetailsFromDB = getUser(userId);
        userDetailsFromDB.setAvailableAmount(userDetailsFromDB.getAvailableAmount() - usedAmount);
        return userRepository.save(userDetailsFromDB);
    }
}
