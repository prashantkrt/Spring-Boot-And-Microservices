package com.myLearning.beanValidation.service;

import com.myLearning.beanValidation.dto.CourseRequestDto;
import com.myLearning.beanValidation.dto.CourseResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public interface CourseService {
    public CourseResponseDto addCourse(CourseRequestDto courseRequestDto);

    public List<CourseResponseDto> getAllCourseList();

    public List<CourseResponseDto> getAllCourseById(Iterable<Integer> id);

    public CourseResponseDto getCourseById(int id);

    public CourseResponseDto updateCourse(int id, CourseRequestDto employeeDto);

    public String deleteCourseById(int id);
}
