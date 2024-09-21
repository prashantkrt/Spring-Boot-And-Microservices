package com.mylearning.consumingrestfulservices.facultyserviceconsumer.services;

import com.mylearning.consumingrestfulservices.facultyserviceconsumer.dto.CourseRequestDto;
import com.mylearning.consumingrestfulservices.facultyserviceconsumer.dto.CourseResponseDto;
import com.mylearning.consumingrestfulservices.facultyserviceconsumer.dto.ServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FacultyService {

    private static final String BASE_URL = "http://localhost:8085/";

    @Autowired
    private RestTemplate restTemplate;

    //consuming the restful web services, of Course-Service Application
    public ServiceResponse<CourseResponseDto> addNewCourseToDashboard(CourseRequestDto courseRequestDto) {
        return restTemplate.postForObject(BASE_URL + "addCourse", courseRequestDto, ServiceResponse.class);
    }


}
