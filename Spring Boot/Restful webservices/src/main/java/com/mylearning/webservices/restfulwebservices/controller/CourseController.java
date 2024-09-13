package com.mylearning.webservices.restfulwebservices.controller;

import com.mylearning.webservices.restfulwebservices.dto.CourseDto;
import com.mylearning.webservices.restfulwebservices.entity.Course;
import com.mylearning.webservices.restfulwebservices.service.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// layer 1 web layer
@RestController //ResController(json) => Controller(asks for model and view) + ResponseBody(json)
@RequestMapping("/course")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    // submit data to the server for processing or create new resources.
    // Body (data to be processed or submitted).
    // Not idempotent. May create or modify resources.
    // Use for submitting data to the server, creating new resources, or performing operations that modify the server state.
    // It is not idempotent, meaning repeated requests might produce different outcomes.
    @PostMapping("addCourse")
    public ResponseEntity<Course> addCourse(@RequestBody CourseDto courseDto) {
        return new ResponseEntity<>(courseService.addCourses(courseDto), HttpStatus.CREATED);
    }

    //retrieve data from the server.
    //Does not modify resources.Idempotent.
    @GetMapping("getAllCourseByIds")
    public ResponseEntity<List<Course>> getAllCourses(@RequestParam("courseIdList") List<Long> courseIds) {
        return new ResponseEntity<>(courseService.getAllCourses(courseIds), HttpStatus.OK);
    }

    //retrieve data from the server.
    @GetMapping("getCourseById")
    public ResponseEntity<Course> getCourseById(@RequestParam("courseId") Long id) {
        return new ResponseEntity<>(courseService.getCourseById(id), HttpStatus.OK);
    }

    /*
    * Put and Patch
    *
    * Both PUT and PATCH are generally idempotent, but PUT is strictly required to be idempotent.
    *
    * */

    // Modifying the resource
    // Replaces the entire resource.
    // Use PUT to replace or update an entire resource.
    @PutMapping("updateCourse")
    public ResponseEntity<Course> updateCourse(@RequestBody CourseDto courseDto) {
        return new ResponseEntity<>(courseService.updateCourse(courseDto), HttpStatus.LOCKED);
    }

    // The PATCH method is used to apply partial updates to a resource.
    // It allows you to modify only the parts of the resource that you need to change.
    // Partial update
    // Use PATCH to update only specific fields of a resource.
    @PatchMapping("courseNameUpdateByCourseId")
    public ResponseEntity<Course> updateCourse(@RequestParam("courseId") Long courseId, @RequestParam("courseName") String courseName) {
        return new ResponseEntity<>(null, HttpStatus.LOCKED);
    }

    //Hard Delete
    //To remove a resource from the server.
    @DeleteMapping("deleteCourse")
    public ResponseEntity<String> deleteCourse(@RequestParam("courseId") Long id) {
        return new ResponseEntity<>(courseService.deleteCourse(id), HttpStatus.LOCKED);
    }

}
