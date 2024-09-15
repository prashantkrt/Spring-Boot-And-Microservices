package com.myLearning.beanValidation.controller;

import com.myLearning.beanValidation.dto.CourseRequestDto;
import com.myLearning.beanValidation.dto.CourseResponseDto;
import com.myLearning.beanValidation.dto.ServiceResponse;
import com.myLearning.beanValidation.service.CourseService;
import org.springframework.http.HttpStatus;
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
        ServiceResponse<CourseResponseDto> serviceResponse = new ServiceResponse<>();
        try {
            CourseResponseDto employeeResponseDto = courseService.addCourse(courseDto);
            serviceResponse.setResponse(employeeResponseDto);
            serviceResponse.setStatus(HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            serviceResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return serviceResponse;
    }

    @GetMapping("getAllCourseByIds")
    public ServiceResponse<List<CourseResponseDto>> getAllCourses(@RequestParam("courseIdList") Iterable<Integer> courseIds) {
        ServiceResponse<List<CourseResponseDto>> serviceResponse = new ServiceResponse<>();
        try {
            List<CourseResponseDto> courseResponseDtoList = courseService.getAllCourseById(courseIds);
            serviceResponse.setResponse(courseResponseDtoList);
            serviceResponse.setStatus(HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            serviceResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return serviceResponse;
    }

    @GetMapping("getCourseById")
    public ServiceResponse<CourseResponseDto> getCourseById(@RequestParam("courseId") Integer id) {
        ServiceResponse<CourseResponseDto> serviceResponse = new ServiceResponse<>();
        try {
            CourseResponseDto courseResponseDto = courseService.getCourseById(id);
            serviceResponse.setResponse(courseResponseDto);
            serviceResponse.setStatus(HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            serviceResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return serviceResponse;
    }

    @PutMapping("updateCourse/{courseId}")
    public ServiceResponse<CourseResponseDto> updateCourse(@PathVariable("courseId") Integer courseId, @RequestBody CourseRequestDto courseDto) {
        return new ServiceResponse<>(courseService.updateCourse(courseId, courseDto), HttpStatus.OK);
    }

    @DeleteMapping("deleteCourse")
    public ServiceResponse<?> deleteCourse(@RequestParam("courseId") Integer id) {
        return new ServiceResponse<>(courseService.deleteCourseById(id), HttpStatus.OK);
    }
}
