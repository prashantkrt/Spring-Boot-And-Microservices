package com.mylearning.producer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KafkaSerializationProducerApplication {

    public static void main(String[] args) {
        SpringApplication.run(KafkaSerializationProducerApplication.class, args);
    }

}
