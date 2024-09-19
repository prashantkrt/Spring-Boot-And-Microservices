package com.myLearning.beanValidation.exceptionHandling.documentation.controller;

import com.myLearning.beanValidation.exceptionHandling.documentation.dto.CourseRequestDto;
import com.myLearning.beanValidation.exceptionHandling.documentation.dto.CourseResponseDto;
import com.myLearning.beanValidation.exceptionHandling.documentation.dto.ErrorDto;
import com.myLearning.beanValidation.exceptionHandling.documentation.dto.ServiceResponse;
import com.myLearning.beanValidation.exceptionHandling.documentation.service.CourseService;
import com.myLearning.beanValidation.exceptionHandling.documentation.utils.AppUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//tagging each controller type by its tag ⇒ this comes under CourseController
@Tag(
        name = "Course Controller",
        description = "CRUD RestApi to create , Read , Update and Delete"
)
@RestController
@RequestMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class CourseController {

    // http://localhost:8084/swagger-ui.html
    // http://localhost:8084/v3/api-docs

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @Operation(
            summary = "adding the course details using course details as input",
            description = "This api adds the course details to H2 DB "
    )
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

    @Operation(
            summary = "fetching the course details using course id list as input",
            description = "This api fetch the course details from H2 DB "
    )
    // define your own api response provide the details with code ,description etc
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP STATUS OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP STATUS INTERNAL SERVER ERROR"
//                    content = @Content(schema = @Schema(implementation = ErrorDto.class))
            )
    }
    )
    @GetMapping("getAllCourseByIds")
    public ServiceResponse<List<CourseResponseDto>> getAllCourses(@RequestParam(value = "courseIdList", required = true) Iterable<Integer> courseIds) {
        log.info("Inside CourseController:getAllCourses api");
        log.info("CourseController:getAllCourses Request Payload -> {} ", courseIds.toString());
        ServiceResponse<List<CourseResponseDto>> serviceResponse = new ServiceResponse<>();
        List<CourseResponseDto> courseResponseDtoList = courseService.getAllCourseById(courseIds);
        serviceResponse.setResponse(courseResponseDtoList);
        serviceResponse.setStatus(HttpStatus.CREATED);
        log.info("Exiting CourseController:getAllCourses api");
        return serviceResponse;
    }


    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "HTTP STATUS CREATED"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "HTTP STATUS BAD REQUEST"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP STATUS INTERNAL SERVER ERROR"
            )
    })
    @Operation(
            summary = "fetching the course details using course id as input",
            description = "This api fetch the course details from H2 DB "
    )
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

    @Operation(
            summary = "Updating the course details using course Id as input and required field as input",
            description = "This api updates the course details to H2 DB "
    )
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

    @Operation(
            summary = "deleting the course details using course id as input",
            description = "This api deletes the course details from H2 DB "
    )
    @DeleteMapping("deleteCourse")
    public ServiceResponse<?> deleteCourse(@RequestParam("courseId") Integer id) {
        log.info("Inside CourseController:deleteCourse api");
        return new ServiceResponse<>(courseService.deleteCourseById(id), HttpStatus.OK);
    }
}
