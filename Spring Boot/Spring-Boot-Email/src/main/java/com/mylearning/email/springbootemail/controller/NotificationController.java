package com.mylearning.email.springbootemail.controller;


import com.mylearning.email.springbootemail.dto.EmailRequest;
import com.mylearning.email.springbootemail.service.EmailService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class NotificationController {

    @Autowired
    private EmailService service;

    @PostMapping("/sendEmail")
    public String sendTextEmail(@RequestBody EmailRequest request) {
        return service.sendSimpleEmail(request);
    }

    @PostMapping("/sendAttachment")
    public String sendEmailWithAttachment(@RequestBody EmailRequest request) throws MessagingException {
        return service.sendEmailWithAttachment(request);
    }
}
