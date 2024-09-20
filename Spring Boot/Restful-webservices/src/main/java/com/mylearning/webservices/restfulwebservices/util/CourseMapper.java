package com.mylearning.webservices.restfulwebservices.util;

import com.mylearning.webservices.restfulwebservices.dto.CourseDto;
import com.mylearning.webservices.restfulwebservices.entity.Course;

public class CourseMapper {

    public static CourseDto getCourseDto(Course course) {
        CourseDto courseDto = new CourseDto();
        courseDto.setDescription(course.getDescription());
        courseDto.setCertificateAvailable(course.isCertificateAvailable());
        courseDto.setDuration(course.getDuration());
        courseDto.setCourseName(course.getCourseName());
        courseDto.setStartDate(course.getStartDate());
        courseDto.setPrice(course.getPrice());
        courseDto.setTraineeName(course.getTraineeName());
        courseDto.setCourseType(course.getCourseType());
        return courseDto;
    }

    public static Course getCourse(CourseDto courseDto) {
        Course course = new Course();
        course.setCourseName(courseDto.getCourseName());
        course.setDescription(courseDto.getDescription());
        course.setCertificateAvailable(courseDto.isCertificateAvailable());
        course.setDuration(courseDto.getDuration());
        course.setStartDate(courseDto.getStartDate());
        course.setPrice(courseDto.getPrice());
        course.setTraineeName(courseDto.getTraineeName());
        course.setCourseType(courseDto.getCourseType());
        return course;
    }
}
