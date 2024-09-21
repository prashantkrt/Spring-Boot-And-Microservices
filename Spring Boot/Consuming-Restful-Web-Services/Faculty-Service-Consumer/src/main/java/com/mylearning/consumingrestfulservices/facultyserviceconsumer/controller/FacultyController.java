package com.mylearning.consumingrestfulservices.facultyserviceconsumer.controller;

import com.mylearning.consumingrestfulservices.facultyserviceconsumer.dto.CourseRequestDto;
import com.mylearning.consumingrestfulservices.facultyserviceconsumer.dto.CourseResponseDto;
import com.mylearning.consumingrestfulservices.facultyserviceconsumer.dto.ServiceResponse;
import com.mylearning.consumingrestfulservices.facultyserviceconsumer.services.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class FacultyController {

    @Autowired
    private FacultyService service;

    @PostMapping("/addNewCourse")
    public ServiceResponse<CourseResponseDto> addNewCourse(@RequestBody CourseRequestDto courseRequestDTO) {
        return service.addNewCourseToDashboard(courseRequestDTO);
    }

}
