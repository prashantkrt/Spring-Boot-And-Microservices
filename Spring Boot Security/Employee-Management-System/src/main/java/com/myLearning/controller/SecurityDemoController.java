package com.myLearning.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
public class SecurityDemoController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello-world from Spring Boot Security Application :)";
    }

    @GetMapping("/greet")
    public String greetings() {
        return "Hello greetings from Spring Boot Security Application :)";
    }

    @GetMapping("/nonSecure")
    public String nonSecure() {
        return "Hello this RestEnd point is publicly Available ";
    }
}
