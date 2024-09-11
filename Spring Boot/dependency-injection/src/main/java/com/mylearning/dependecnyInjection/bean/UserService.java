package com.mylearning.dependecnyInjection.bean;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    // @Autowired // field injection
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


//Constructor Injection is generally preferred because it guarantees that an object is created with all of its required dependencies, ensuring immutability and making the code easier to test.
//Setter Injection is suitable for optional dependencies but can lead to an object being in an inconsistent state if not handled properly.
//Field Injection is discouraged because it introduces hidden dependencies and makes the code harder to test and maintain.
