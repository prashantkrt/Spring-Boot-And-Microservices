package com.mylearning.rabbitmqpubsub2.service;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQListenerService {

    // Listener for queue1 bound to exchange1
    @RabbitListener(queues = "queue1")
    public void listenToQueue1(String message) {
        System.out.println("Received message from queue1: " + message);
    }

    // Listener for queue2 bound to exchange2
    @RabbitListener(queues = "queue2")
    public void listenToQueue2(String message) {
        System.out.println("Received message from queue2: " + message);
    }
}