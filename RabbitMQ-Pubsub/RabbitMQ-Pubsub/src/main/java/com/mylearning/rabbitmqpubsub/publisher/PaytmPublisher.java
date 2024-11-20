package com.mylearning.rabbitmqpubsub.publisher;


import com.mylearning.rabbitmqpubsub.dto.PaymentRequest;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

@Service
public class PaytmPublisher {

    public static final String PAYTM_EXCHANGE = "paytm_exchange";
    public static final String PAYTM_ROUTING_KEY = "paytm_routingKey";


    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void publish(PaymentRequest request) {
        request.setTransactionId(UUID.randomUUID().toString().split("-")[0]);
        request.setAmount(12000.00);
        request.setTransactionDate(new Date());
        request.setSourceAccount(UUID.randomUUID().toString().split("-")[0]+"Source Account");
        request.setDestinationAccount(UUID.randomUUID().toString().split("-")[0] + "Destination Account");
        //void convertAndSend(String exchange, String routingKey, Object message);
        rabbitTemplate.convertAndSend(PAYTM_EXCHANGE, PAYTM_ROUTING_KEY, request);
    }

    public void sendMessage(String messageBody) {
        String exchange = "my-exchange";
        String routingKey = "my-routing-key";
        MessageProperties messageProperties = new MessageProperties();
        //messageProperties.setContentType("text/plain");
        // Message message = new Message(messageBody.getBytes(), messageProperties);
        messageProperties.setContentType("application/json");
        Message message = new Message(messageBody.getBytes(), messageProperties);
        //void send(String exchange, String routingKey, Message message);
        rabbitTemplate.send(exchange, routingKey, message);
    }

}
