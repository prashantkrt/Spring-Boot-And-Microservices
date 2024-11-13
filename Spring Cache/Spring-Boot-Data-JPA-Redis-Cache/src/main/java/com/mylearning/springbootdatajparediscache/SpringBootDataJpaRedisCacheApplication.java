package com.mylearning.springbootdatajparediscache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SpringBootDataJpaRedisCacheApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootDataJpaRedisCacheApplication.class, args);
    }

}
