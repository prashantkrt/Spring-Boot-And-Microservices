package com.mylearning.consumer;

import com.mylearning.dto.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

@Service
@Slf4j
public class KafkaMessageListener {
    /*
     *
     * Intentionally making some exception check to throw the exception
     * which therefore makes the data loss case scenario for which we have to make retry mechanism
     * */
    @KafkaListener(topics = "myTopicDemo", groupId = "DemoGroup")
    public void consume1(Customer customer) {
        log.info("Inside consumer1 method");
        try {
            List<String> restrictedEmailAddress = Stream.of("abc@abc.com", "laddo@laddo.com", "xyz@xyz.com").toList();
            if (restrictedEmailAddress.contains(customer.getEmail())) {
                throw new RuntimeException("Invalid email address");
            }
            log.info("Consumer 1 consumed the message -> {}", customer.toString() + 1);
        } catch (RuntimeException e) {
            log.error(e.getMessage());
        }
        log.info("exited of consumer1 method");
    }

    @KafkaListener(topics = "myTopicDemo", groupId = "DemoGroup")
    public void consume2(Customer customer) {
        log.info("Inside consumer2 method");
        try {
            List<String> restrictedEmailAddress = Stream.of("abc@abc.com", "laddo@laddo.com", "xyz@xyz.com").toList();
            if (restrictedEmailAddress.contains(customer.getEmail())) {
                throw new RuntimeException("Invalid email address");
            }
            log.info("Consumer 2 consumed the message -> {}", customer.toString() + 2);
        } catch (RuntimeException e) {
            log.error(e.getMessage());
        }
        log.info("exited of consumer2 method");
    }

    @KafkaListener(topics = "myTopicDemo", groupId = "DemoGroup")
    public void consume3(Customer customer) {
        log.info("Inside consumer3 method");
        try {
            List<String> restrictedEmailAddress = Stream.of("abc@abc.com", "laddo@laddo.com", "xyz@xyz.com").toList();
            if (restrictedEmailAddress.contains(customer.getEmail())) {
                throw new RuntimeException("Invalid email address");
            }
            log.info("Consumer 3 consumed the message -> {}", customer.toString() + 3);
        } catch (RuntimeException e) {
            log.error(e.getMessage());
        }
        log.info("exited of consumer3 method");
    }

    @KafkaListener(topics = "myTopicDemo", groupId = "DemoGroup")
    public void consume4(Customer customer) {
        log.info("Inside consumer4 method");
        try {
            List<String> restrictedEmailAddress = Stream.of("abc@abc.com", "laddo@laddo.com", "xyz@xyz.com").toList();
            if (restrictedEmailAddress.contains(customer.getEmail())) {
                throw new RuntimeException("Invalid email address");
            }
            log.info("Consumer 4 consumed the message -> {}", customer.toString() + 4);
        } catch (RuntimeException e) {
            log.error(e.getMessage());
        }
        log.info("exited of consumer4 method");
    }
}
