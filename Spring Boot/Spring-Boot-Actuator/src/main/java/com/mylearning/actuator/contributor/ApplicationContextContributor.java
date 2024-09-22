package com.mylearning.actuator.contributor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;


@Component
public class ApplicationContextContributor implements InfoContributor {

    //My Custom info contributor

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public void contribute(Info.Builder builder) {
        Map<String, Object> contextMap = new HashMap<>();
        contextMap.put("bean-definition-count", applicationContext.getBeanDefinitionCount());
        contextMap.put("bean-names", applicationContext.getBeanDefinitionNames());
        contextMap.put("application-startup-time", applicationContext.getStartupDate());
        builder.withDetail("context", contextMap);
    }
}

/*
* By BuildInfoContributor
* build.artifact=Spring-Boot-Actuator
* build.group=com.myLearning.actuator
* build.name=Spring-Boot-Actuator
* build.time=2024-09-22T14\:02\:45.007Z
* build.version=0.0.1-SNAPSHOT
*
* */
