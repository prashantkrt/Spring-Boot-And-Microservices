package com.mylearning.consumer.consumer;


import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaMessageListener {

    @KafkaListener(topics = "myTopicDemo", groupId = "DemoGroup-1")
    public void consume1(String message) {
        log.info("Consumer consumed the message -> {}", message);
    }

    @KafkaListener(topics = "myTopicDemo", groupId = "DemoGroup-1")
    public void consume2(String message) {
        log.info("Consumer consumed the message -> {}", message);
    }

    @KafkaListener(topics = "myTopicDemo", groupId = "DemoGroup-1")
    public void consume3(String message) {
        log.info("Consumer consumed the message -> {}", message);
    }

    @KafkaListener(topics = "myTopicDemo", groupId = "DemoGroup-1")
    public void consume4(String message) {
        log.info("Consumer consumed the message -> {}", message);
    }
}
