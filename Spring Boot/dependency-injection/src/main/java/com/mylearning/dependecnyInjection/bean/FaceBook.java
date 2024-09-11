package com.mylearning.dependecnyInjection.bean;

import org.springframework.stereotype.Component;

@Component // spring will manage and create the bean and it a parent annotation of
// - service, controller and repository
public class FaceBook implements SocialMediaService{
    @Override
    public void getUserFeeds() {
        System.out.println("user feed is loaded!! in Facebook");
    }
}
