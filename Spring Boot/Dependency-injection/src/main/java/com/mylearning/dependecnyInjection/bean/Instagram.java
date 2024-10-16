package com.mylearning.dependecnyInjection.bean;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Qualifier("instagram")
@Component // spring will manage and create the bean and it a parent annotation of
// - service, controller and repository
public class Instagram implements SocialMediaService {

    @Override
    public void getUserFeeds() {
        System.out.println("user feed is loaded!! in Instagram");
    }
}
