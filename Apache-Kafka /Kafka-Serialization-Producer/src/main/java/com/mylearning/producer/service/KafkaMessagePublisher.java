package com.mylearning.producer.service;


import com.mylearning.producer.dto.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
public class KafkaMessagePublisher {
    /*
     * for String as key as well as Object we don't need to use serializer props in app.props file
     * for JSON we need to else it will give error
     *
     * Here we are using Object as parameter then we have to use serialization
     *
     * */
    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    public void sendMessage(Customer customer) throws ExecutionException, InterruptedException {

        // it has a return type in CompletableFuture
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
