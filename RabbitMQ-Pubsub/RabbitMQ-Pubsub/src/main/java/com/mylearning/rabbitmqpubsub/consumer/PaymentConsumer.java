package com.mylearning.rabbitmqpubsub.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class PaymentConsumer {

    @RabbitListener(queues = "my-queue") // For raw messages
    public void consumeRawMessage(String message) {
        System.out.println("Received Raw Message: " + message);
        // Process the raw message as needed
    }
}
