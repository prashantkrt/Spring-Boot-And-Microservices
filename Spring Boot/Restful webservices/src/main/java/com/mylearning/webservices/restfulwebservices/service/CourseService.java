package com.mylearning.webservices.restfulwebservices.service;

import com.mylearning.webservices.restfulwebservices.dto.CourseDto;
import com.mylearning.webservices.restfulwebservices.entity.Course;
import java.util.List;
import org.springframework.stereotype.Service;

// layer 2 service
@Service
public interface CourseService {
    // to add to a database
    public Course addCourses(CourseDto courseDto);

    public List<Course> getAllCourses(List<Long> courseIds);

    public Course getCourseById(Long id);
}
