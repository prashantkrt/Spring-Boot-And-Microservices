package com.mylearning.webservices.restfulwebservices.controller;

import com.mylearning.webservices.restfulwebservices.dto.CourseDto;
import com.mylearning.webservices.restfulwebservices.entity.Course;
import com.mylearning.webservices.restfulwebservices.service.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// layer 1 web layer
@RestController
@RequestMapping("/course")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping("addCourse")
    public ResponseEntity<Course> addCourse(@RequestBody CourseDto courseDto) {
        return new ResponseEntity<>(courseService.addCourses(courseDto), HttpStatus.OK);
    }

    @GetMapping("getAllCourseByIds")
    public ResponseEntity<List<Course>> getAllCourses(@RequestParam("courseIdList") List<Long> courseIds) {
        return new ResponseEntity<>(courseService.getAllCourses(courseIds), HttpStatus.OK);
    }

    @GetMapping("getCourseById")
    public ResponseEntity<Course> getCourseById(@RequestParam("courseId") Long id) {
        return new ResponseEntity<>(courseService.getCourseById(id), HttpStatus.OK);
    }

}
