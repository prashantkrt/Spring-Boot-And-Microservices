package com.myLearning.beanValidation.exceptionHandling.utils;

import com.myLearning.beanValidation.exceptionHandling.dto.CourseRequestDto;
import com.myLearning.beanValidation.exceptionHandling.dto.CourseResponseDto;
import com.myLearning.beanValidation.exceptionHandling.entity.Course;

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
        course.setEmailAddress(courseRequestDto.getEmailAddress());
        course.setMobileNumber(courseRequestDto.getMobileNumber());
        return course;
    }

    public static CourseResponseDto getResponseFromCourse(Course course) {
        CourseResponseDto courseResponseDto = new CourseResponseDto();
        courseResponseDto.setCourseType(course.getCourseType());
        courseResponseDto.setDescription(course.getDescription());
        courseResponseDto.setStartDate(course.getStartDate());
        courseResponseDto.setCourseName(course.getCourseName());
        courseResponseDto.setCertificateAvailable(course.isCertificateAvailable());
        courseResponseDto.setTraineeName(course.getTraineeName());
        courseResponseDto.setPrice(course.getPrice());
        courseResponseDto.setEmailAddress(course.getEmailAddress());
        courseResponseDto.setMobileNumber(course.getMobileNumber());
        return courseResponseDto;
    }
}
