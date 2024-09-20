package com.mylearning.consumingrestfulservices.facultyserviceconsumer.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FacultyService {

    @Autowired
    private RestTemplate restTemplate;



}
