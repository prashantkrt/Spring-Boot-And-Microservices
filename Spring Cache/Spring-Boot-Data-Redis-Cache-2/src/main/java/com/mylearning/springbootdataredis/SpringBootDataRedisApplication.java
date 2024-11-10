package com.mylearning.springbootdataredis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SpringBootDataRedisApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootDataRedisApplication.class, args);
    }

}
