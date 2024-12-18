package com.myLearning.beanValidation.exceptionHandling.service;

import com.myLearning.beanValidation.exceptionHandling.dto.CourseRequestDto;
import com.myLearning.beanValidation.exceptionHandling.dto.CourseResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CourseService {
    public CourseResponseDto addCourse(CourseRequestDto courseRequestDto);

    public List<CourseResponseDto> getAllCourseList();

    public List<CourseResponseDto> getAllCourseById(Iterable<Integer> id);

    public CourseResponseDto getCourseById(int id);

    public CourseResponseDto updateCourse(int id, CourseRequestDto employeeDto);

    public String deleteCourseById(int id);
}
