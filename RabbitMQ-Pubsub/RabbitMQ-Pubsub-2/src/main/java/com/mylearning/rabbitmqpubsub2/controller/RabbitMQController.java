package com.mylearning.rabbitmqpubsub2.controller;

import com.mylearning.rabbitmqpubsub2.publisher.RabbitMQPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rabbitmq")
public class RabbitMQController {

    @Autowired
    private RabbitMQPublisher rabbitMQPublisher;

    // Endpoint to send message to exchange1
    @PostMapping("/sendToExchange1")
    public String sendToExchange1(@RequestBody String message) {
        rabbitMQPublisher.sendMessageToExchange1(message);
        return "Message sent to exchange1 with routing key 'routing.key1'";
    }

    // Endpoint to send message to exchange2
    @PostMapping("/sendToExchange2")
    public String sendToExchange2(@RequestBody String message) {
        rabbitMQPublisher.sendMessageToExchange2(message);
        return "Message sent to exchange2 with routing key 'routing.key2'";
    }
}