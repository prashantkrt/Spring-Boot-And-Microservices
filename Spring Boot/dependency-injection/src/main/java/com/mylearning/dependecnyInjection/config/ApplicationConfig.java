package com.mylearning.dependecnyInjection.config;

import com.mylearning.dependecnyInjection.bean.Instagram;
import com.mylearning.dependecnyInjection.bean.Orkut;
import com.mylearning.dependecnyInjection.bean.SocialMediaService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    //using Java based configuration bean creation for dependency injection
    @Bean
    @Qualifier("orkut")
    public SocialMediaService getOrkut() {
        return new Orkut();
    }

    @Bean(name = "myInsta")
    public SocialMediaService getInstagram() {
        return new Instagram();
    }
}
