package com.mylearning.rabbitmqpubsub2.publisher;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQPublisher {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    // Publish message to exchange1 with routing key "routing.key1"
    public void sendMessageToExchange1(String messageBody) {
        String exchange = "exchange1";
        String routingKey = "routing.key1";
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setContentType("application/json");
        Message message = new Message(messageBody.getBytes(), messageProperties);
        rabbitTemplate.send(exchange, routingKey, message);
    }

    // Publish message to exchange2 with routing key "routing.key2"
    public void sendMessageToExchange2(String messageBody) {
        String exchange = "exchange2";
        String routingKey = "routing.key2";
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setContentType("application/json");
        Message message = new Message(messageBody.getBytes(), messageProperties);
        rabbitTemplate.send(exchange, routingKey, message);
    }
}
