package com.mylearning.redispubsub;

import com.mylearning.redispubsub.producer.RedisMessagePublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RedisPubsubApplication implements CommandLineRunner {

    @Autowired
    private RedisMessagePublisher publisher;


    public static void main(String[] args) {
        SpringApplication.run(RedisPubsubApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        publisher.publish("Hello, Redis!");
    }
}
