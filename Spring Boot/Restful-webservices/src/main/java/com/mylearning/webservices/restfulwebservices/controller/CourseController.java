package com.mylearning.webservices.restfulwebservices.controller;

import com.mylearning.webservices.restfulwebservices.dto.CourseDto;
import com.mylearning.webservices.restfulwebservices.entity.Course;
import com.mylearning.webservices.restfulwebservices.service.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

// REST ⇒ Representational State Transfer
// layer 1 web layer
@RestController //ResController(json) => Controller(asks for model and view) + ResponseBody(json)
@RequestMapping("/course")
public class CourseController {
/*
*  In Rest APIs, idempotent means that making multiple identical request to
*  an API will have same effects as making single request
*
*  The Same request is made number of times then it will have the same impact as making the request just once.
*/

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }
    // Not idempotent. May create or modify resources for multiple attempts
    // submit data to the server for processing or create new resources.
    // Body (data to be processed or submitted).
    // Not idempotent. May create or modify resources.
    // Use for submitting data to the server, creating new resources, or performing operations that modify the server state.
    // It is not idempotent, meaning repeated requests might produce different outcomes.
    @PostMapping("addCourse")
    public ResponseEntity<Course> addCourse(@RequestBody CourseDto courseDto) {
        return new ResponseEntity<>(courseService.addCourses(courseDto), HttpStatus.CREATED);
    }

    // Retrieves data from the server.
    // Does not modify resources.
    // Idempotent
    @GetMapping("getAllCourseByIds")
    public ResponseEntity<List<Course>> getAllCourses(@RequestParam("courseIdList") List<Long> courseIds) {
        return new ResponseEntity<>(courseService.getAllCourses(courseIds), HttpStatus.OK);
    }

    // Retrieve data from the server.
    // Does not modify resources.
    // Idempotent
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
    // Idempotent
    @PutMapping("updateCourse")
    public ResponseEntity<Course> updateCourse(@RequestBody CourseDto courseDto) {
        return new ResponseEntity<>(courseService.updateCourse(courseDto), HttpStatus.LOCKED);
    }

    // The PATCH method is used to apply partial updates to a resource.
    // It allows you to modify only the parts of the resource that you need to change.
    // Partial update
    // Use PATCH to update only specific fields of a resource.
    // Idempotent: Not always
    @PatchMapping("courseNameUpdateByCourseId")
    public ResponseEntity<Course> updateCourse(@RequestParam("courseId") Long courseId, @RequestParam("courseName") String courseName) {
        return new ResponseEntity<>(null, HttpStatus.LOCKED);
    }

    // Hard Delete
    // To remove a resource from the server.
    // Idempotent: Yes
    @DeleteMapping("deleteCourse")
    public ResponseEntity<String> deleteCourse(@RequestParam("courseId") Long id) {
        return new ResponseEntity<>(courseService.deleteCourse(id), HttpStatus.LOCKED);
    }

}

    /*

    Please go through this: -

    In REST APIs, idempotent operations are those that can be performed multiple times without changing the result beyond the initial application.
    This means that making the same request once or multiple times has the same effect on the system.

    Here's a breakdown of REST methods and their idempotency:

    GET:
    Idempotent: Yes.
    It retrieves data without making any changes. Repeated calls return the same data (unless the data changes externally).

    PUT:
    Idempotent: Yes.
    It updates a resource by replacing it entirely with the provided payload. Repeated calls with the same data will produce the same result.

    DELETE:
    Idempotent: Yes.
    It deletes a resource. Once the resource is deleted, subsequent calls won’t change the system further (though you might get different responses like "404 Not Found").

    POST:
    Idempotent: No.
    It creates a resource, and multiple calls could result in creating multiple new resources (e.g., creating multiple entries in a database).

    PATCH:
    Idempotent: Not always.
    It partially updates a resource. Whether it’s idempotent depends on the implementation and the kind of update being made.
        {
          "name": "John Doe",
          "email": "john@example.com",
          "age": 30,
          "status": "active"
        }

        PATCH /users/123
         {
          "status": "inactive"
         }

    if you send this PATCH request multiple times, the status field will always be set to "inactive", regardless of how many times you repeat the request.
    Idempotent behavior: Whether you send the request once or ten times, the result will be the same: status will remain "inactive".

    Idempotency is important in systems where requests might be retried due to network issues or errors, as it ensures stability and consistency.
    */

//Summary of Security Considerations:

/*
Aspect	                   GET	                                   POST
Data Transmission	Sent in the URL (less secure)	              Sent in the body (more secure)
Visibility	        Visible in browser history, logs, bookmarks	  Not visible in browser history or bookmarks
Request Size	    Limited by URL length	                      No size limitation
Caching	            Can be cached	                              Typically not cached
Reusability	        Easily resubmitted or bookmarked	          Requires manual resubmission
*/


// Key Differences:
/*
Feature	                        GET	                                             POST
Purpose	                        Retrieve data	                               Send data (create/update)
Idempotency	                    Yes	                                           No
Safe	                        Yes (no changes to server)	                   No (modifies server state)
Caching	                        Can be cached	                               Not typically cached
Request Data	                Sent via URL parameters	                       Sent in the request body
Changes State	                No	                                           Yes
Use Case	                    Retrieve data (e.g., fetching resources)	   Create or update resources (e.g., form submission)
*/
