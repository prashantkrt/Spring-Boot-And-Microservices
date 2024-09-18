package com.myLearning.beanValidation.exceptionHandling.documentation.controller;

import com.myLearning.beanValidation.exceptionHandling.documentation.dto.CourseRequestDto;
import com.myLearning.beanValidation.exceptionHandling.documentation.dto.CourseResponseDto;
import com.myLearning.beanValidation.exceptionHandling.documentation.dto.ServiceResponse;
import com.myLearning.beanValidation.exceptionHandling.documentation.service.CourseService;
import com.myLearning.beanValidation.exceptionHandling.documentation.utils.AppUtils;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
@Slf4j
public class CourseController {

    // http://localhost:8084/swagger-ui.html
    // http://localhost:8084/v3/api-docs

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping("addCourse")
    public ServiceResponse<CourseResponseDto> addCourse(@RequestBody @Valid CourseRequestDto courseDto) {
        log.info("Inside CourseController:addRequest api");
        log.info("CourseController:addRequest Request Payload -> {} ", AppUtils.getCourseFromDto(courseDto));
        ServiceResponse<CourseResponseDto> serviceResponse = new ServiceResponse<>();
        CourseResponseDto employeeResponseDto = courseService.addCourse(courseDto);
        serviceResponse.setResponse(employeeResponseDto);
        serviceResponse.setStatus(HttpStatus.CREATED);
        log.info("Exiting CourseController:addRequest api");
        log.info("CourseController:addRequest Response -> {} ", serviceResponse.toString());
        return serviceResponse;
    }

    @GetMapping("getAllCourseByIds")
    public ServiceResponse<List<CourseResponseDto>> getAllCourses(@RequestParam("courseIdList") Iterable<Integer> courseIds) {
        log.info("Inside CourseController:getAllCourses api");
        log.info("CourseController:getAllCourses Request Payload -> {} ", courseIds.toString());
        ServiceResponse<List<CourseResponseDto>> serviceResponse = new ServiceResponse<>();
        List<CourseResponseDto> courseResponseDtoList = courseService.getAllCourseById(courseIds);
        serviceResponse.setResponse(courseResponseDtoList);
        serviceResponse.setStatus(HttpStatus.CREATED);
        log.info("Exiting CourseController:getAllCourses api");
        return serviceResponse;
    }

    @GetMapping("getCourseById")
    public ServiceResponse<CourseResponseDto> getCourseById(@RequestParam("courseId") Integer id) {
        log.info("Inside CourseController:getCourseById api");
        ServiceResponse<CourseResponseDto> serviceResponse = new ServiceResponse<>();
        CourseResponseDto courseResponseDto = courseService.getCourseById(id);
        serviceResponse.setResponse(courseResponseDto);
        serviceResponse.setStatus(HttpStatus.CREATED);
        log.info("Exiting CourseController:getCourseById api");
        return serviceResponse;
    }

    @PutMapping("updateCourse/{courseId}")
    public ServiceResponse<CourseResponseDto> updateCourse(@PathVariable("courseId") Integer courseId, @RequestBody @Valid CourseRequestDto courseDto) {
        log.info("Inside CourseController:updateCourse api");
        ServiceResponse<CourseResponseDto> serviceResponse = new ServiceResponse<>();
        CourseResponseDto courseResponseDto = courseService.updateCourse(courseId, courseDto);
        serviceResponse.setResponse(courseResponseDto);
        serviceResponse.setStatus(HttpStatus.OK);
        log.info("Exiting CourseController:updateCourse api");
        return serviceResponse;
    }

    @DeleteMapping("deleteCourse")
    public ServiceResponse<?> deleteCourse(@RequestParam("courseId") Integer id) {
        log.info("Inside CourseController:deleteCourse api");
        return new ServiceResponse<>(courseService.deleteCourseById(id), HttpStatus.OK);
    }
}
