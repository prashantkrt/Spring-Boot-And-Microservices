package com.mylearning;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mylearning.dto.PaymentRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;

@SpringBootApplication
@Slf4j
public class KafkaConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(KafkaConsumerApplication.class, args);
    }

    @KafkaListener(topics = "myLearning", groupId = "myLearning_Consumer_Group")
    public void receiveMessage(String message) {
        System.out.println("Message received: " + message);
        log.info("Message received from producer and consumed by the consumer : {}", message);
    }

    @KafkaListener(topics = "payment-topic")
    public void paymentConsumer1(PaymentRequest paymentRequest){
        System.out.println(paymentRequest);
        //log.info("paymentConsumer1 consumed message {} ", new ObjectMapper().writeValueAsString(paymentRequest));
        log.info("paymentConsumer1 consumed message {} ", paymentRequest.toString());
    }

}
