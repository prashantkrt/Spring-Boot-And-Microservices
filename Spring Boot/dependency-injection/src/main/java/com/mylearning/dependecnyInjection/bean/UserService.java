package com.mylearning.dependecnyInjection.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
/*
* Apart from this there is another way using java based configuration using
* AppConfig by creating manually bean creating @bean
* */
@Service  //business logic or service logic
public class UserService {

    @Autowired
    @Qualifier("orkut") //specific bean needed for different dependency injection
    private SocialMediaService socialService;

    // @Autowired // field injection by default primary one will be injected
    private SocialMediaService socialMediaService;

    //setter injection
    public void setSocialMediaService(SocialMediaService socialMediaService) {
        this.socialMediaService = socialMediaService;
    }

    //constructor injection most preferred
    public UserService(SocialMediaService socialMediaService) {
        this.socialMediaService = socialMediaService;
    }

    public void displayFeedS() {
        socialMediaService.getUserFeeds();
    }
}


//=> Constructor Injection is generally preferred because it guarantees that an object is created with all of its required dependencies, ensuring immutability and making the code easier to test.
//=> Setter Injection is suitable for optional dependencies but can lead to an object being in an inconsistent state if not handled properly.
//=> Field Injection is discouraged because it introduces hidden dependencies and makes the code harder to test and maintain.
