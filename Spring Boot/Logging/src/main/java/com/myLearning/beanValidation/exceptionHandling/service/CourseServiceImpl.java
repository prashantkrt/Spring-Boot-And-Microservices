package com.myLearning.beanValidation.exceptionHandling.service;

import com.myLearning.beanValidation.exceptionHandling.dto.CourseRequestDto;
import com.myLearning.beanValidation.exceptionHandling.dto.CourseResponseDto;
import com.myLearning.beanValidation.exceptionHandling.entity.Course;
import com.myLearning.beanValidation.exceptionHandling.exception.CourseServiceBusinessException;
import com.myLearning.beanValidation.exceptionHandling.repository.CourseRepo;
import com.myLearning.beanValidation.exceptionHandling.utils.AppUtils;
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
        try {
            Course course = AppUtils.getCourseFromDto(courseRequestDto);
            course = courseRepo.save(course);
            return AppUtils.getResponseFromCourse(course);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new CourseServiceBusinessException(e.getMessage());
        }
    }

    /*
    It's always good practice to handle the exception on the service layer
    * */
    @Override
    public List<CourseResponseDto> getAllCourseList() {
        try {
            return courseRepo.findAll().stream().map(i -> {
                CourseResponseDto dto = AppUtils.getResponseFromCourse(i);
                return dto;
            }).collect(Collectors.toList());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new CourseServiceBusinessException(e.getMessage());
        }
    }

    @Override
    public List<CourseResponseDto> getAllCourseById(Iterable<Integer> id) {
        try {
            return courseRepo.findAllById(id).stream()
                    .map(AppUtils::getResponseFromCourse).collect(Collectors.toList());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new CourseServiceBusinessException(e.getMessage());
        }
    }

    @Override
    public CourseResponseDto getCourseById(int id) {
        try {
            Course course = courseRepo.findById(id).orElseThrow(() -> new CourseServiceBusinessException("No course with the given Id"));
            return AppUtils.getResponseFromCourse(course);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new CourseServiceBusinessException(e.getMessage());
        }
    }

    @Override
    public CourseResponseDto updateCourse(int id, CourseRequestDto courseRequestDto) {
        try {
            Course course = courseRepo.findById(id).orElseThrow(() -> new CourseServiceBusinessException("No course with the given Id"));
            course.setCourseType(courseRequestDto.getCourseType());
            course.setCourseName(courseRequestDto.getCourseName());
            course.setDescription(courseRequestDto.getDescription());
            course.setStartDate(courseRequestDto.getStartDate());
            course.setCertificateAvailable(courseRequestDto.isCertificateAvailable());
            course.setTraineeName(courseRequestDto.getTraineeName());
            course.setPrice(courseRequestDto.getPrice());
            course.setMobileNumber(courseRequestDto.getMobileNumber());
            course.setEmailAddress(courseRequestDto.getEmailAddress());
            Course updatedCourse = courseRepo.save(course);
            return AppUtils.getResponseFromCourse(updatedCourse);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new CourseServiceBusinessException(e.getMessage());
        }
    }

    @Override
    public String deleteCourseById(int id) {
        try {
            courseRepo.deleteById(id);
            return "Deleted course with the given Id  " + id;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new CourseServiceBusinessException(e.getMessage());
        }
    }
}
