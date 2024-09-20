package com.mylearning.dependecnyInjection;

import com.mylearning.dependecnyInjection.bean.SocialMediaService;
import com.mylearning.dependecnyInjection.bean.UserService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DependencyInjectionApplication implements CommandLineRunner {

    @Autowired
    @Qualifier("instagram") //specific bean
    private SocialMediaService socialMediaService;

    // no need of autowired with Resource
    @Resource(name="myInsta") // name based injection
    private SocialMediaService socialMedia;

    @Autowired  //Works only with Spring's @Autowired
    @Qualifier("myInsta") // name based injection
    private SocialMediaService social;

    public static void main(String[] args) {
        SpringApplication.run(DependencyInjectionApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
      socialMediaService.getUserFeeds();
    }
}
