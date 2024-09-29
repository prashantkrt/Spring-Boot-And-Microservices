package com.mylearning.produce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class PaytmController {

    @Value("${paytm.producer.topic.name}")
    private String topicName;

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @RequestMapping(value = "/publish/{message}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public void sendMessage(@PathVariable("message") String message) {
        kafkaTemplate.send(topicName, message);
    }

}
