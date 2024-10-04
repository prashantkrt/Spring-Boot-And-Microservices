package com.mylearning.controller;

import com.mylearning.dto.Customer;
import com.mylearning.service.KafkaMessagePublisher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;
import java.util.UUID;

@RestController
@RequestMapping("/")
@Slf4j
public class EventController {

    @Autowired
    private KafkaMessagePublisher kafkaMessagePublisher;

    @PostMapping("/publish")
    public ResponseEntity<?> publishMessage(@RequestBody Customer customer) {
        try {
            for (int i = 0; i < 5000; i++) {
                customer.setId(i);
                customer.setName("customer"+i);
                //customer.setEmail("customer"+ UUID.randomUUID().toString().split("-")[0] +"@gmlii.com");
                long tenDigitNumber = 1000000000L + (long)(new Random().nextDouble() * 9000000000L);
                customer.setContactName(String.valueOf(tenDigitNumber));
                kafkaMessagePublisher.sendMessage(customer);
                log.info("Messages produced -> {}", customer.toString() + i);
            }
            return ResponseEntity.ok("message published successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

}
