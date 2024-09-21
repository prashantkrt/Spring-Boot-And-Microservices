package com.mylearning.consumingrestfulservices.facultyserviceconsumer.controller;

import com.mylearning.consumingrestfulservices.facultyserviceconsumer.dto.CourseRequestDto;
import com.mylearning.consumingrestfulservices.facultyserviceconsumer.dto.CourseResponseDto;
import com.mylearning.consumingrestfulservices.facultyserviceconsumer.dto.ServiceResponse;
import com.mylearning.consumingrestfulservices.facultyserviceconsumer.services.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/")
public class FacultyController {

    @Autowired
    private FacultyService service;

    @PostMapping("/addNewCourse")
    public ServiceResponse<CourseResponseDto> addNewCourse(@RequestBody CourseRequestDto courseRequestDTO) {
        return service.addNewCourseToDashboard(courseRequestDTO);
    }

    @GetMapping("/fetchAllCourse")
    public ServiceResponse<List<CourseResponseDto>> getAllCourses() {
        return service.fetchAllCoursesFromDashboard();
    }


    @PutMapping("/updateCourse/{courseID}")
    public ServiceResponse<CourseResponseDto> updateCourse(@PathVariable Integer courseID,@RequestBody CourseRequestDto courseRequestDTO) {
     return service.updateCourseToDashboard(courseID, courseRequestDTO);
    }

}
