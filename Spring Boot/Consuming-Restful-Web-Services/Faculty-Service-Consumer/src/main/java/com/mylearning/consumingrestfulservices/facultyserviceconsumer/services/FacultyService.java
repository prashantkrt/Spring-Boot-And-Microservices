package com.mylearning.consumingrestfulservices.facultyserviceconsumer.services;

import com.mylearning.consumingrestfulservices.facultyserviceconsumer.dto.CourseRequestDto;
import com.mylearning.consumingrestfulservices.facultyserviceconsumer.dto.CourseResponseDto;
import com.mylearning.consumingrestfulservices.facultyserviceconsumer.dto.ServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class FacultyService {

    private static final String BASE_URL = "http://localhost:8085/";

    @Autowired
    private RestTemplate restTemplate;

    // consuming the restful web services, of Course-Service Application
    public ServiceResponse<CourseResponseDto> addNewCourseToDashboard(CourseRequestDto courseRequestDto) {
        return restTemplate.postForObject(BASE_URL + "addCourse", courseRequestDto, ServiceResponse.class);
    }

    public ServiceResponse<List<CourseResponseDto>> fetchAllCoursesFromDashboard() {
        return restTemplate.getForObject(BASE_URL + "/fetchAllCourses", ServiceResponse.class);
    }

    // For any path variable request
    // 2 ways: ⇒
    // 1. Using Path Variable with Placeholder
    // 2. Using String Concatenation
    public ServiceResponse<CourseResponseDto> fetchCourseByIdExample(Integer courseId) {
        // Using Path Variable with Placeholder:
        // return restTemplate.getForObject(BASE_URL + "getCourseById/{courseId}",ServiceResponse.class, courseId);

        // Using String Concatenation:
        return restTemplate.getForObject(BASE_URL + "/getCourse" + courseId, ServiceResponse.class);
    }


    // Query Param
    public ServiceResponse<CourseResponseDto> getCourseByCourseId(Integer courseId) {
        /*
        *  using map storing all the params in map and passing as argument
        *  Map<String, Object> requestMap = new HashMap<>();
        *  requestMap.put("courseId", courseId);
        *  return restTemplate.getForObject(BASE_URL + "course/search/request/?courseId={courseId}", ServiceResponse.class, requestMap);
        *
        *
        *
        *    Map<String, Object> queryParams = new HashMap<>();
        *    queryParams.put("courseId", courseId);
        *    queryParams.put("status", status);
        *    queryParams.put("category", category);

        *   Build the URL with query parameters
        *   String url = BASE_URL + "course/search/request/";

        *   return restTemplate.getForObject(url + "?courseId={courseId}&status={status}&category={category}",
                                          ServiceResponse.class,queryParams);

        *
        *
        * */

        // without map,
        /*
        *   ⇒ We can also pass multiple arguments
        *   // Construct the URL with placeholders for query parameters
               String url = BASE_URL + "course/search/request/?courseId={courseId}&status={status}&category={category}";

            // Call getForObject with the parameters directly
               return restTemplate.getForObject(url, ServiceResponse.class, courseId, status, category);
               }
        *
        *
        *
        *
        *
        * */
        //concatenation way
        //return restTemplate.getForObject(BASE_URL + "getCourseById?courseId="+courseId ,ServiceResponse.class);
        return restTemplate.getForObject(BASE_URL + "getCourseById?courseId={courseId}", ServiceResponse.class, courseId);
    }

    public void updateCourseInDashboard(int courseId, CourseRequestDto courseRequestDTO) {
        // put has the return type as void
        restTemplate.put(BASE_URL + "updateCourse/" + courseId, courseRequestDTO);
    }

    public void deleteCourseFromDashboard(int courseId) {
        // same for delete, it doesn't return anything
        restTemplate.delete(BASE_URL + "deleteCourse?courseId={courseId}", courseId);
    }

    // Taken from GPT for learning perspective
    // For RestTemplate, Exploring.
    // My own way
    // through this approach, we can return

    public ServiceResponse<CourseResponseDto> updateCourseToDashboard2(Integer id, CourseRequestDto courseRequestDto) {

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        // Create HttpEntity that includes the request body and headers
        HttpEntity<CourseRequestDto> requestEntity = new HttpEntity<>(courseRequestDto, headers);

        // Make the PUT request and get the response as an Employee object
        ResponseEntity<ServiceResponse> responseEntity = restTemplate.exchange(
                BASE_URL + "updateCourse/" + id,
                HttpMethod.PUT,
                requestEntity,
                ServiceResponse.class
        );
        ServiceResponse<CourseResponseDto> serviceResponse =  responseEntity.getBody();
        return serviceResponse;
    }



    public ServiceResponse<?> deleteCourseToDashboard2(Integer id) {

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        // Create HttpEntity that includes the request body and headers
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        // Make the PUT request and get the response as an Employee object
        ResponseEntity<?> responseEntity = restTemplate.exchange(
                BASE_URL + "deleteCourse?courseId=" + id, // Ensure the URL is correct
                HttpMethod.DELETE,
                requestEntity,
                ServiceResponse.class
        );
        ServiceResponse<?> serviceResponse = (ServiceResponse<?>) responseEntity.getBody();
        serviceResponse.setStatus(HttpStatus.resolve(responseEntity.getStatusCode().value()));
        return serviceResponse;

    }

}
