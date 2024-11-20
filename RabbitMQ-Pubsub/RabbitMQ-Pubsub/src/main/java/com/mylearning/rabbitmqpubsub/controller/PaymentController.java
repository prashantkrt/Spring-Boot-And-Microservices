package com.mylearning.rabbitmqpubsub.controller;

import com.mylearning.rabbitmqpubsub.dto.PaymentRequest;
import com.mylearning.rabbitmqpubsub.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/publish")
    public String publishMessage(@RequestBody PaymentRequest paymentRequest) {
        paymentService.publishPayment(paymentRequest);
        return "Payment message published successfully!";
    }

    @PostMapping("/send")
    public String sendMessage(@RequestBody String messageBody) {
        paymentService.sendRawMessage(messageBody);
        return "Raw message sent successfully!";
    }
}