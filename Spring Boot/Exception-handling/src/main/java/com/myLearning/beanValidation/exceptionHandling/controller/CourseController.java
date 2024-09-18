package com.myLearning.beanValidation.exceptionHandling.controller;

import com.myLearning.beanValidation.exceptionHandling.dto.CourseRequestDto;
import com.myLearning.beanValidation.exceptionHandling.dto.CourseResponseDto;
import com.myLearning.beanValidation.exceptionHandling.dto.ServiceResponse;
import com.myLearning.beanValidation.exceptionHandling.service.CourseService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class CourseController {

    /*
     * @NotNull ⇒ to say that a field must not be null
     * @NotEmpty ⇒ to say that a field must not be empty like list []
     * @NotBlank ⇒ combination of above two @NotNull + @NotEmpty
     * @Min and @Max => to say that a numerical field must is only valid when it's value is above or below a certain value
     * @Pattern => to say that a string is only valid when it matches a certain regular expression
     * @Email => to say string must be valid email
     * */

    private final CourseService courseService;

    // constructor injection
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    /*
     * if we have the global exception handler then there is no sense to use try catch to handle the exception
     * I have used here just for learning purpose
     * */
    @PostMapping("addCourse")
    public ServiceResponse<CourseResponseDto> addCourse(@RequestBody @Valid CourseRequestDto courseDto) {
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

    /*
     * if we have the global exception handler then there is no sense to use try catch to handle the exception
     * I have used here just for learning purpose
     * */
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
    public ServiceResponse<CourseResponseDto> updateCourse(@PathVariable("courseId") Integer courseId, @RequestBody @Valid CourseRequestDto courseDto) {
        return new ServiceResponse<>(courseService.updateCourse(courseId, courseDto), HttpStatus.OK);
    }

    @DeleteMapping("deleteCourse")
    public ServiceResponse<?> deleteCourse(@RequestParam("courseId") Integer id) {
        return new ServiceResponse<>(courseService.deleteCourseById(id), HttpStatus.OK);
    }
}
