package com.mylearning.redispubsub.controller;

import com.mylearning.redispubsub.dto.PaymentDto;
import com.mylearning.redispubsub.producer.MessagePublisher;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/")
@RestController
public class PaymentController {

    private MessagePublisher publisher;

    @PostMapping("/publish")
    public String publishMessage(@RequestBody PaymentDto paymentDto) {
        publisher.publish("channelTopic",paymentDto);
        return "Message published: " + paymentDto.toString();
    }

}
