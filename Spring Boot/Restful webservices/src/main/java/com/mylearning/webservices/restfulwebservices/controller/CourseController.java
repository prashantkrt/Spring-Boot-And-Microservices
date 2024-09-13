package com.mylearning.webservices.restfulwebservices.controller;

import com.mylearning.webservices.restfulwebservices.dto.CourseDto;
import com.mylearning.webservices.restfulwebservices.entity.Course;
import com.mylearning.webservices.restfulwebservices.service.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// layer 1 web layer
@RestController
@RequestMapping("/course")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping("addCourse")
    public ResponseEntity<Course> addCourse(CourseDto courseDto) {
       return  new ResponseEntity<>(courseService.addCourses(courseDto), HttpStatus.OK);
    }


}
