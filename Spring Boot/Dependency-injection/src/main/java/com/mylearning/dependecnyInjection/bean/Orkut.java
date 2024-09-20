package com.mylearning.dependecnyInjection.bean;

public class Orkut implements SocialMediaService{
    @Override
    public void getUserFeeds() {
        System.out.println("Orkut UserFeeds");
    }
}
