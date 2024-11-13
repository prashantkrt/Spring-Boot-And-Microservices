package com.mylearning.redispubsub.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mylearning.redispubsub.dto.PaymentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class MessagePublisher {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public void publish(String topic, PaymentDto request) {
        try {
            stringRedisTemplate.convertAndSend(topic, new ObjectMapper().writeValueAsString(request));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
