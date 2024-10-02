package com.mylearning.consumer.consumer;

import com.mylearning.consumer.dto.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaMessageListener {

    @KafkaListener(topics = "myTopicDemo2", groupId = "DemoGroup-2")
    public void consume1(Customer customer) {
        log.info("Consumer 1 consumed the message -> {}", customer.toString() + 1);
    }

    @KafkaListener(topics = "myTopicDemo2", groupId = "DemoGroup-2")
    public void consume2(Customer customer) {
        log.info("Consumer 2 consumed the message -> {}", customer.toString() + 2);
    }

    @KafkaListener(topics = "myTopicDemo2", groupId = "DemoGroup-2")
    public void consume3(Customer customer) {
        log.info("Consumer 3 consumed the message -> {}", customer.toString() + 3);
    }

    @KafkaListener(topics = "myTopicDemo2", groupId = "DemoGroup-2")
    public void consume4(Customer customer) {
        log.info("Consumer 4 consumed the message -> {}", customer.toString() + 4);
    }
}
