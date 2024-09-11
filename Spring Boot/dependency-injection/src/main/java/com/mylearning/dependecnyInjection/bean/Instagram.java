package com.mylearning.dependecnyInjection.bean;

import org.springframework.stereotype.Component;

@Component
public class Instagram implements SocialMediaService {

    @Override
    public void getUserFeeds() {
        System.out.println("user feed is loaded!! in Instagram");
    }
}
