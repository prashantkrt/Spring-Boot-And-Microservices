package com.myLearning.beanValidation.controller;

import com.myLearning.beanValidation.dto.CourseRequestDto;
import com.myLearning.beanValidation.dto.CourseResponseDto;
import com.myLearning.beanValidation.dto.ServiceResponse;
import com.myLearning.beanValidation.service.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping("addCourse")
    public ServiceResponse<CourseResponseDto> addCourse(@RequestBody CourseRequestDto courseDto) {
        return null;
    }

    @GetMapping("getAllCourseByIds")
    public ServiceResponse<List<CourseResponseDto>> getAllCourses(@RequestParam("courseIdList") List<Long> courseIds) {
        return null;
    }

    @GetMapping("getCourseById")
    public ServiceResponse<CourseResponseDto> getCourseById(@RequestParam("courseId") Long id) {
        return null;
    }

    @PutMapping("updateCourse")
    public ServiceResponse<CourseResponseDto> updateCourse(@RequestBody CourseRequestDto courseDto) {
        return null;
    }

    @DeleteMapping("deleteCourse")
    public ServiceResponse<?> deleteCourse(@RequestParam("courseId") Long id) {
        return new ServiceResponse<>();
    }
}
