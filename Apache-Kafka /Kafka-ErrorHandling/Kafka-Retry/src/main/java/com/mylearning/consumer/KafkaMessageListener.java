package com.mylearning.consumer;

import com.mylearning.dto.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.DltHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.retry.annotation.Backoff;
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

    // by default attempts isset to 3 N-1 order if n = 3 then 2 times retry
    @RetryableTopic(attempts = "4",  backoff = @Backoff(delay = 1000, multiplier = 2)) // Initial delay of 1000 ms, doubled after each retry
    @KafkaListener(topics = "myTopicDemo", groupId = "DemoGroup")
    public void consume1(Customer customer, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic, @Header(KafkaHeaders.OFFSET) long offset) {
        log.info("Inside consumer1 method");
        try {
            List<String> restrictedEmailAddress = Stream.of("abc@abc.com", "laddo@laddo.com", "xyz@xyz.com").toList();
            if (restrictedEmailAddress.contains(customer.getEmail())) {
                throw new RuntimeException("Invalid email address");
            }
            log.info("Consumer 1 consumed the message -> {}", customer.toString() + 1);
        } catch (RuntimeException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
        log.info("exited of consumer1 method");
    }

    /*  A Dead Letter Topic is used to store messages that fail to be processed successfully by a consumer, even after retries.
        When a message cannot be processed due to issues like data corruption, deserialization errors, or business logic failures, it is moved to the DLT to avoid blocking the main topic's processing.
     */
    // Any error will come here and handled by DLT
    @DltHandler
    public void listenDLT(Customer customer, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic, @Header(KafkaHeaders.OFFSET) long offset) {
        log.info("DTL receives data {}, from topic {} from offset {}", customer.toString(), topic, offset);
    }

    @RetryableTopic(attempts = "4") // 3 N-1
    @KafkaListener(topics = "myTopicDemo", groupId = "DemoGroup")
    public void consume2(Customer customer,@Header(KafkaHeaders.RECEIVED_TOPIC) String topic, @Header(KafkaHeaders.OFFSET) long offset) {
        log.info("Inside consumer2 method");
        try {
            List<String> restrictedEmailAddress = Stream.of("abc@abc.com", "laddo@laddo.com", "xyz@xyz.com").toList();
            if (restrictedEmailAddress.contains(customer.getEmail())) {
                throw new RuntimeException("Invalid email address");
            }
            log.info("Consumer 2 consumed the message -> {}", customer.toString() + 2);
        } catch (RuntimeException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
        log.info("exited of consumer2 method");
    }

    @RetryableTopic(attempts = "4") // 3 N-1
    @KafkaListener(topics = "myTopicDemo", groupId = "DemoGroup")
    public void consume3(Customer customer, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic, @Header(KafkaHeaders.OFFSET) long offset) {
        log.info("Inside consumer3 method");
        try {
            List<String> restrictedEmailAddress = Stream.of("abc@abc.com", "laddo@laddo.com", "xyz@xyz.com").toList();
            if (restrictedEmailAddress.contains(customer.getEmail())) {
                throw new RuntimeException("Invalid email address");
            }
            log.info("Consumer 3 consumed the message -> {}", customer.toString() + 3);
        } catch (RuntimeException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
        log.info("exited of consumer3 method");
    }

    @RetryableTopic(attempts = "4")// 3 N-1
    @KafkaListener(topics = "myTopicDemo", groupId = "DemoGroup")
    public void consume4(Customer customer, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic, @Header(KafkaHeaders.OFFSET) long offset) {
        log.info("Inside consumer4 method");
        try {
            List<String> restrictedEmailAddress = Stream.of("abc@abc.com", "laddo@laddo.com", "xyz@xyz.com").toList();
            if (restrictedEmailAddress.contains(customer.getEmail())) {
                throw new RuntimeException("Invalid email address");
            }
            log.info("Consumer 4 consumed the message -> {}", customer.toString() + 4);
        } catch (RuntimeException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
        log.info("exited of consumer4 method");
    }


    @KafkaListener(topics = "your-topic-name", groupId = "my-group-id")
    public void consume(String message,
                        @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
                        // @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition,
                        @Header(KafkaHeaders.OFFSET) long offset,
                        @Header(KafkaHeaders.RECEIVED_TIMESTAMP) long timestamp)
    // @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) String key) {
    {

        System.out.println("Received message: " + message);
        System.out.println("From topic: " + topic);
        // System.out.println("From partition: " + partition);
        System.out.println("Message offset: " + offset);
        System.out.println("Message timestamp: " + timestamp);
        // System.out.println("Message key: " + key);
    }
}
