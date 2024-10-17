package com.mylearning.userservice.controller;


import com.mylearning.userservice.entity.User;
import com.mylearning.userservice.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public User registerNewUser(@RequestBody User user) {
        log.info("Registering new user: {}", user);
        return userService.addNewUser(user);
    }

    @GetMapping("/{userId}")
    public User getUser(@PathVariable int userId) {
        log.info("Getting user: {}", userId);
        return userService.getUser(userId);
    }

    @PutMapping("/{userId}/{amount}")
    public User updateUserBalance(@PathVariable int userId, @PathVariable double amount) {
        log.info("Updating user balance: {}", userId);
        return userService.updateAccountStatus(userId, amount);
    }
}
