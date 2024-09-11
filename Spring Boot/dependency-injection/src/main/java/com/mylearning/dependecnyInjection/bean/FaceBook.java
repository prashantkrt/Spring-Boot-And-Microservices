package com.mylearning.dependecnyInjection.bean;

import org.springframework.stereotype.Component;

@Component
public class FaceBook implements SocialMediaService{
    @Override
    public void getUserFeeds() {
        System.out.println("user feed is loaded!! in Facebook");
    }
}
