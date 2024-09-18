package com.myLearning.beanValidation.exceptionHandling.controller;

import com.myLearning.beanValidation.exceptionHandling.dto.CourseRequestDto;
import com.myLearning.beanValidation.exceptionHandling.dto.CourseResponseDto;
import com.myLearning.beanValidation.exceptionHandling.dto.ServiceResponse;
import com.myLearning.beanValidation.exceptionHandling.service.CourseService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class CourseController {

    private final CourseService courseService;
    Logger logger = LoggerFactory.getLogger(CourseController.class);

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    /*
    * pattern
    * (default) =>  Date, Time, Logging Level, ProcessID, ThreadName, ClassName, Logger Message
    * 2024-09-18T16:05:30.228+05:30 ERROR 17263 --- [Bean-Validation] [nio-8083-exec-1] c.m.b.e.controller.CourseController      : This is an ERROR message
    *
    *
    * */
    @GetMapping("log")
    public String loggingLevel() {
        // by default in springBoot, only Info, Warn and error is enabled
        //        different logging levels:-
        //       -> -> -> -> ....
        //       TRACE < DEBUG < INFO < WARN < ERROR < FATAL(log4j)< OFF
        //       1. TRACE (most detailed)
        //       2. DEBUG (widely used) recommended
        //       3. INFO (widely used) recommended
        //       4. WARN
        //       5. ERROR (widely used)
        //       6. FATAL (most critical)
        //       7. OFF (disables logging)
        logger.trace("This is a TRACE message"); // more details about the internal flow in depth from start
        logger.debug("This is a DEBUG message"); // Information of the flow of the system
        logger.info("This is an INFO message"); // events occurring at runtime
        logger.warn("This is a WARN message"); // gives the warnings for the errors caused by deprecated APIs
        logger.error("This is an ERROR message"); // Runtime error, when ever runtime error comes and to track it
        return "This is a demo api for logging";

        /*
        * When the logging level is set to DEBUG,
        * it will capture log messages of that level and all higher-severity levels.
        *
        * What Will Be Captured:
           DEBUG: Detailed information intended for diagnosing issues.
           INFO: General operational messages that confirm the application is working as expected.
           WARN: Warnings about potentially harmful situations.
           ERROR: Error events that might still allow the application to continue running.
           FATAL: Critical issues that could lead to an application crash or failure.

         What Will Not Be Captured:
            TRACE (if available): More granular, verbose logging meant for deeper troubleshooting will not be captured since it is lower than DEBUG.
        * */
    }

    @PostMapping("addCourse")
    public ServiceResponse<CourseResponseDto> addCourse(@RequestBody @Valid CourseRequestDto courseDto) {
        ServiceResponse<CourseResponseDto> serviceResponse = new ServiceResponse<>();
        CourseResponseDto employeeResponseDto = courseService.addCourse(courseDto);
        serviceResponse.setResponse(employeeResponseDto);
        serviceResponse.setStatus(HttpStatus.CREATED);
        return serviceResponse;
    }

    @GetMapping("getAllCourseByIds")
    public ServiceResponse<List<CourseResponseDto>> getAllCourses(@RequestParam("courseIdList") Iterable<Integer> courseIds) {
        ServiceResponse<List<CourseResponseDto>> serviceResponse = new ServiceResponse<>();
        List<CourseResponseDto> courseResponseDtoList = courseService.getAllCourseById(courseIds);
        serviceResponse.setResponse(courseResponseDtoList);
        serviceResponse.setStatus(HttpStatus.CREATED);
        return serviceResponse;
    }

    @GetMapping("getCourseById")
    public ServiceResponse<CourseResponseDto> getCourseById(@RequestParam("courseId") Integer id) {
        ServiceResponse<CourseResponseDto> serviceResponse = new ServiceResponse<>();
        CourseResponseDto courseResponseDto = courseService.getCourseById(id);
        serviceResponse.setResponse(courseResponseDto);
        serviceResponse.setStatus(HttpStatus.CREATED);
        return serviceResponse;
    }

    @PutMapping("updateCourse/{courseId}")
    public ServiceResponse<CourseResponseDto> updateCourse(@PathVariable("courseId") Integer courseId, @RequestBody @Valid CourseRequestDto courseDto) {
        ServiceResponse<CourseResponseDto> serviceResponse = new ServiceResponse<>();
        CourseResponseDto courseResponseDto = courseService.updateCourse(courseId, courseDto);
        serviceResponse.setResponse(courseResponseDto);
        serviceResponse.setStatus(HttpStatus.OK);
        //return new ServiceResponse<>(courseService.updateCourse(courseId, courseDto), HttpStatus.OK);
        return serviceResponse;
    }

    @DeleteMapping("deleteCourse")
    public ServiceResponse<?> deleteCourse(@RequestParam("courseId") Integer id) {
        return new ServiceResponse<>(courseService.deleteCourseById(id), HttpStatus.OK);
    }
}
