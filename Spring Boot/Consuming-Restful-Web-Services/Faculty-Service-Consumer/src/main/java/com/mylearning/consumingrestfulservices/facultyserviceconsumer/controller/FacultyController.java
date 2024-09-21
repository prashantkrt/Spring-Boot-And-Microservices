package com.mylearning.consumingrestfulservices.facultyserviceconsumer.controller;

import com.mylearning.consumingrestfulservices.facultyserviceconsumer.dto.CourseRequestDto;
import com.mylearning.consumingrestfulservices.facultyserviceconsumer.dto.CourseResponseDto;
import com.mylearning.consumingrestfulservices.facultyserviceconsumer.dto.ServiceResponse;
import com.mylearning.consumingrestfulservices.facultyserviceconsumer.services.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @GetMapping("/fetchCourseById")
    public ServiceResponse<?> getCourseById(@RequestParam int id) {
        return service.getCourseByCourseId(id);
    }


    @PutMapping("/updateCourse/{courseID}")
    public ServiceResponse<?> updateCourse(@PathVariable Integer courseID,@RequestBody CourseRequestDto courseRequestDTO) {
      service.updateCourseInDashboard(courseID, courseRequestDTO);
      ServiceResponse<String> response = new ServiceResponse<>();
      response.setResponse("Successfully updated course");
      response.setStatus(HttpStatus.CREATED);
      return response;
    }

    @PutMapping("/updateCourse2/{courseID}")
    public ServiceResponse<?> updateCourseInDashboard(@PathVariable Integer courseID, @RequestBody CourseRequestDto courseRequestDTO) {
        return service.updateCourseToDashboard2(courseID, courseRequestDTO);
    }


    @DeleteMapping("/delete1/{courseID}")
    public ServiceResponse<?> deleteCourse(@PathVariable Integer courseID) {
        service.deleteCourseFromDashboard(courseID);
        ServiceResponse<String> response = new ServiceResponse<>();
        response.setResponse("Successfully deleted course");
        response.setStatus(HttpStatus.OK);
        return response;
    }

    @DeleteMapping("/delete2/{courseID}")
    public ServiceResponse<?> deleteCourseFromDashboard(@PathVariable Integer courseID) {
        return  service.deleteCourseToDashboard2(courseID);
    }

}
