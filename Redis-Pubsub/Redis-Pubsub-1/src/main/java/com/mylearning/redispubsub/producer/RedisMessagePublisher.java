package com.mylearning.redispubsub.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisMessagePublisher {

    @Autowired
    private StringRedisTemplate redisTemplate;

    public void publish(String message) {
        redisTemplate.convertAndSend("myChannel", message);
    }
}
