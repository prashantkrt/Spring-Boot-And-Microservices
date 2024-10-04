package com.mylearning.service;


import com.mylearning.dto.Customer;
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

    public void sendMessage(Customer customer) throws ExecutionException, InterruptedException {

        CompletableFuture<SendResult<String, Object>> future = kafkaTemplate.send("myTopicDemo2", customer);
        future.whenComplete((result, exception) -> {

            System.out.println("Partition number: -> " + result.getRecordMetadata().partition()); // prints the partition
            System.out.println("Offset number: -> " + result.getRecordMetadata().offset()); // prints the offset number
            System.out.println("Topic name: ->" + result.getRecordMetadata().topic()); // prints the topic

            if (exception == null) {
                System.out.println("Sent message: = [" + customer.toString() + "]" + " [" + result.getRecordMetadata().offset() + "]");
            } else {
                System.out.println("Unable to send message = [" + customer.toString() + "] due to : " + exception.getMessage());
            }
        });
    }
}
