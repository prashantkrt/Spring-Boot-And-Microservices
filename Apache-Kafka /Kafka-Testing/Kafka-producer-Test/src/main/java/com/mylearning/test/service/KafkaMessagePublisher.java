package com.mylearning.test.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
public class KafkaMessagePublisher {

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    public void sendMessage(String message) throws ExecutionException, InterruptedException {

        CompletableFuture<SendResult<String, Object>> future = kafkaTemplate.send("myTopicDemo", message);

        future.whenComplete((result, exception) -> {

            System.out.println("Partition number: -> "+result.getRecordMetadata().partition()); // prints the partition
            System.out.println("Offset number: -> "+result.getRecordMetadata().offset()); // prints the offset number
            System.out.println("Topic name: ->"+result.getRecordMetadata().topic()); // prints the topic

            if (exception == null) {
                System.out.println("Sent message: = [" + message + "]" + " [" + result.getRecordMetadata().offset() + "]");
            } else {
                System.out.println("Unable to send message = [" + message + "] due to : " + exception.getMessage());
            }
        });
    }
}
