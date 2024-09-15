package com.myLearning.beanValidation.service;

import com.myLearning.beanValidation.dto.CourseRequestDto;
import com.myLearning.beanValidation.dto.CourseResponseDto;
import com.myLearning.beanValidation.entity.Course;
import com.myLearning.beanValidation.repository.CourseRepo;
import com.myLearning.beanValidation.utils.AppUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepo courseRepo;

    public CourseServiceImpl(CourseRepo courseRepo) {
        this.courseRepo = courseRepo;
    }

    @Override
    public CourseResponseDto addCourse(CourseRequestDto courseRequestDto) {
        Course course = AppUtils.getCourseFromDto(courseRequestDto);
        course = courseRepo.save(course);
        return AppUtils.getResponseFromCourse(course);
    }

    @Override
    public List<CourseResponseDto> getAllCourseList() {
        return courseRepo.findAll().stream().map(i -> {
            CourseResponseDto dto = AppUtils.getResponseFromCourse(i);
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public List<CourseResponseDto> getAllCourseById(Iterable<Integer> id) {
        return courseRepo.findAllById(id).stream()
                .map(AppUtils::getResponseFromCourse).collect(Collectors.toList());
    }

    @Override
    public CourseResponseDto getCourseById(int id) {
        Course course = courseRepo.findById(id).orElseThrow(()->new RuntimeException("No course with the given Id"));
        return AppUtils.getResponseFromCourse(course);
    }

    @Override
    public CourseResponseDto updateCourse(int id, CourseRequestDto courseRequestDto) {
        Course course = courseRepo.findById(id).orElseThrow(()->new RuntimeException("No course with the given Id"));
        course.setCourseType(courseRequestDto.getCourseType());
        course.setCourseName(courseRequestDto.getCourseName());
        course.setDescription(courseRequestDto.getDescription());
        course.setStartDate(courseRequestDto.getStartDate());
        course.setCertificateAvailable(courseRequestDto.isCertificateAvailable());
        course.setTraineeName(courseRequestDto.getTraineeName());
        course.setPrice(courseRequestDto.getPrice());
        Course updatedCourse = courseRepo.save(course);
        return AppUtils.getResponseFromCourse(updatedCourse);
    }

    @Override
    public String deleteCourseById(int id) {
        courseRepo.deleteById(id);
        return "Deleted course with the given Id  " + id;
    }
}
