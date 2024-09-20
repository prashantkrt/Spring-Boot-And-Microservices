package com.mylearning.dependecnyInjection.bean;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Primary // if not specific to bean using qualifier then this will be injected
@Qualifier("facebook")
@Component // spring will manage and create the bean and it a parent annotation of
// - service, controller and repository
public class FaceBook implements SocialMediaService{
    @Override
    public void getUserFeeds() {
        System.out.println("user feed is loaded!! in Facebook");
    }
}
