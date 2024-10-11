package com.mylearning.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/")
public class AuthController {

    @GetMapping("/info")
    public String getUserInfoFromGitHub(Principal principal) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(principal);
    }
}
