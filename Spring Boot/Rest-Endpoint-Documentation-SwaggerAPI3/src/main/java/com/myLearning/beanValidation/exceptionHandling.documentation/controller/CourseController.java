package com.myLearning.beanValidation.exceptionHandling.documentation.controller;

import com.myLearning.beanValidation.exceptionHandling.documentation.dto.CourseRequestDto;
import com.myLearning.beanValidation.exceptionHandling.documentation.dto.CourseResponseDto;
import com.myLearning.beanValidation.exceptionHandling.documentation.dto.ServiceResponse;
import com.myLearning.beanValidation.exceptionHandling.documentation.service.CourseService;
import com.myLearning.beanValidation.exceptionHandling.documentation.utils.AppUtils;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class CourseController {
    // http://localhost:8084/swagger-ui/index.html
    private final CourseService courseService;
    Logger logger = LoggerFactory.getLogger(CourseController.class);

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping("addCourse")
    public ServiceResponse<CourseResponseDto> addCourse(@RequestBody @Valid CourseRequestDto courseDto) {
        logger.info("Inside CourseController:addRequest api");
        logger.info("CourseController:addRequest Request Payload -> {} ", AppUtils.getCourseFromDto(courseDto));
        ServiceResponse<CourseResponseDto> serviceResponse = new ServiceResponse<>();
        CourseResponseDto employeeResponseDto = courseService.addCourse(courseDto);
        serviceResponse.setResponse(employeeResponseDto);
        serviceResponse.setStatus(HttpStatus.CREATED);
        logger.info("Exiting CourseController:addRequest api");
        logger.info("CourseController:addRequest Response -> {} ", serviceResponse.toString());
        return serviceResponse;
    }

    @GetMapping("getAllCourseByIds")
    public ServiceResponse<List<CourseResponseDto>> getAllCourses(@RequestParam("courseIdList") Iterable<Integer> courseIds) {
        logger.info("Inside CourseController:getAllCourses api");
        logger.info("CourseController:getAllCourses Request Payload -> {} ", courseIds.toString());
        ServiceResponse<List<CourseResponseDto>> serviceResponse = new ServiceResponse<>();
        List<CourseResponseDto> courseResponseDtoList = courseService.getAllCourseById(courseIds);
        serviceResponse.setResponse(courseResponseDtoList);
        serviceResponse.setStatus(HttpStatus.CREATED);
        logger.info("Exiting CourseController:getAllCourses api");
        return serviceResponse;
    }

    @GetMapping("getCourseById")
    public ServiceResponse<CourseResponseDto> getCourseById(@RequestParam("courseId") Integer id) {
        logger.info("Inside CourseController:getCourseById api");
        ServiceResponse<CourseResponseDto> serviceResponse = new ServiceResponse<>();
        CourseResponseDto courseResponseDto = courseService.getCourseById(id);
        serviceResponse.setResponse(courseResponseDto);
        serviceResponse.setStatus(HttpStatus.CREATED);
        logger.info("Exiting CourseController:getCourseById api");
        return serviceResponse;
    }

    @PutMapping("updateCourse/{courseId}")
    public ServiceResponse<CourseResponseDto> updateCourse(@PathVariable("courseId") Integer courseId, @RequestBody @Valid CourseRequestDto courseDto) {
        logger.info("Inside CourseController:updateCourse api");
        ServiceResponse<CourseResponseDto> serviceResponse = new ServiceResponse<>();
        CourseResponseDto courseResponseDto = courseService.updateCourse(courseId, courseDto);
        serviceResponse.setResponse(courseResponseDto);
        serviceResponse.setStatus(HttpStatus.OK);
        logger.info("Exiting CourseController:updateCourse api");
        //return new ServiceResponse<>(courseService.updateCourse(courseId, courseDto), HttpStatus.OK);
        return serviceResponse;
    }

    @DeleteMapping("deleteCourse")
    public ServiceResponse<?> deleteCourse(@RequestParam("courseId") Integer id) {
        logger.info("Inside CourseController:deleteCourse api");
        return new ServiceResponse<>(courseService.deleteCourseById(id), HttpStatus.OK);
    }

    @GetMapping("log")
    public String loggingLevel() {
        logger.trace("This is a TRACE message"); // more details about the internal flow in depth from start
        logger.debug("This is a DEBUG message"); // Information of the flow of the system
        logger.info("This is an INFO message"); // events occurring at runtime
        logger.warn("This is a WARN message"); // gives the warnings for the errors caused by deprecated APIs
        logger.error("This is an ERROR message"); // Runtime error, when ever runtime error comes and to track it
        return "This is a demo api for logging";
    }

}
