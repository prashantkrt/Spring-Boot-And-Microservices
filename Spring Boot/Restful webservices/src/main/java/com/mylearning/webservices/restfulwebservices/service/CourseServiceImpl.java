package com.mylearning.webservices.restfulwebservices.service;

import com.mylearning.webservices.restfulwebservices.dto.CourseDto;
import com.mylearning.webservices.restfulwebservices.entity.Course;
import java.util.List;

import com.mylearning.webservices.restfulwebservices.repository.CourseRepo;
import com.mylearning.webservices.restfulwebservices.util.CourseMapper;
import org.springframework.stereotype.Service;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepo repo;

    public CourseServiceImpl(CourseRepo repo) {
        this.repo = repo;
    }

    @Override
    public Course addCourses(CourseDto courseDto) {
        Course course = CourseMapper.getCourse(courseDto);
        return repo.save(course);
    }
}
