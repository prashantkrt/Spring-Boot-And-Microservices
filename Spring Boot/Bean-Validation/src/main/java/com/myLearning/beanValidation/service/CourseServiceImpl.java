package com.myLearning.beanValidation.service;

import com.myLearning.beanValidation.dto.CourseRequestDto;
import com.myLearning.beanValidation.dto.CourseResponseDto;
import com.myLearning.beanValidation.repository.CourseRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepo courseRepo;

    public CourseServiceImpl(CourseRepo courseRepo) {
        this.courseRepo = courseRepo;
    }

    @Override
    public CourseRequestDto addCourse(CourseRequestDto courseRequestDto) {
        return null;
    }

    @Override
    public List<CourseRequestDto> getAllCourseList() {
        return List.of();
    }

    @Override
    public List<CourseResponseDto> getAllCourseById(Iterable<Long> id) {
        return List.of();
    }

    @Override
    public CourseResponseDto getCourseById(int id) {
        return null;
    }

    @Override
    public CourseResponseDto updateCourse(int id, CourseRequestDto employeeDto) {
        return null;
    }

    @Override
    public String deleteCourseById(int id) {
        return "";
    }
}
