package com.myLearning.beanValidation.utils;

import com.myLearning.beanValidation.dto.CourseRequestDto;
import com.myLearning.beanValidation.dto.CourseResponseDto;
import com.myLearning.beanValidation.entity.Course;

public class AppUtils {

    public static Course getCourseFromDto(CourseRequestDto courseRequestDto) {
        Course course = new Course();
        course.setCourseName(courseRequestDto.getCourseName());
        course.setCourseType(courseRequestDto.getCourseType());
        course.setDescription(courseRequestDto.getDescription());
        course.setStartDate(courseRequestDto.getStartDate());
        course.setCertificateAvailable(courseRequestDto.isCertificateAvailable());
        course.setTraineeName(courseRequestDto.getTraineeName());
        course.setPrice(courseRequestDto.getPrice());
        return course;
    }

    public static CourseResponseDto getResponseFromCourse(Course course) {
        CourseResponseDto courseResponseDto = new CourseResponseDto();
        courseResponseDto.setCourseType(course.getCourseType());
        courseResponseDto.setDescription(course.getDescription());
        courseResponseDto.setCourseName(course.getCourseName());
        courseResponseDto.setStartDate(course.getStartDate());
        courseResponseDto.setCertificateAvailable(course.isCertificateAvailable());
        courseResponseDto.setTraineeName(course.getTraineeName());
        courseResponseDto.setPrice(course.getPrice());
        return courseResponseDto;
    }
}