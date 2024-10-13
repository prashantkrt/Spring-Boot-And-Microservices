package com.mylearning.userservice.controller;


import com.mylearning.userservice.entity.User;
import com.mylearning.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public User registerNewUser(@RequestBody User user) {
        return userService.addNewUser(user);
    }

    @GetMapping("/{userId}")
    public User getUser(@PathVariable int userId) {
        return userService.getUser(userId);
    }

    @PutMapping("/{userId}/{amount}")
    public User updateUserBalance(@PathVariable int userId, @PathVariable double amount) {
        return userService.updateAccountStatus(userId, amount);
    }
}
