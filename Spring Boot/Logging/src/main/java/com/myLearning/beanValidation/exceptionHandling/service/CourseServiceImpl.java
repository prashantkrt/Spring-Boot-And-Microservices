package com.myLearning.beanValidation.exceptionHandling.service;

import com.myLearning.beanValidation.exceptionHandling.dto.CourseRequestDto;
import com.myLearning.beanValidation.exceptionHandling.dto.CourseResponseDto;
import com.myLearning.beanValidation.exceptionHandling.entity.Course;
import com.myLearning.beanValidation.exceptionHandling.exception.CourseServiceBusinessException;
import com.myLearning.beanValidation.exceptionHandling.repository.CourseRepo;
import com.myLearning.beanValidation.exceptionHandling.utils.AppUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepo courseRepo;

    Logger logger = LoggerFactory.getLogger(CourseServiceImpl.class);

    public CourseServiceImpl(CourseRepo courseRepo) {
        this.courseRepo = courseRepo;
    }

    @Override
    public CourseResponseDto addCourse(CourseRequestDto courseRequestDto) {
        logger.info("CourseService::addCourse method execution started");
        try {
            logger.debug("CourseService::addCourse method input -> {}",courseRequestDto.toString());
            Course course = AppUtils.getCourseFromDto(courseRequestDto);
            course = courseRepo.save(course);
            logger.debug("Course entity from the Database -> {}", course.toString());
            CourseResponseDto responseDto = AppUtils.getResponseFromCourse(course);
            responseDto.setCourseUniqueCode(UUID.randomUUID().toString().split("-")[0]);
            logger.debug("CourseResponseDTO from the request {}", responseDto.toString());
            logger.info("CourseService::addCourse method execution Ended");
            return AppUtils.getResponseFromCourse(course);
        } catch (Exception e) {
            logger.error("CourseService::addCourse exception occur while persisting the course object to Database ->{}", e.getMessage());
            throw new CourseServiceBusinessException("CourseService::addCourse method failed "+e.getMessage());
        }
    }

    /*
    It's always good practice to handle the exception on the service layer
    * */
    @Override
    public List<CourseResponseDto> getAllCourseList() {
        logger.info("CourseService::getAllCourseList method execution started");
        try {
            return courseRepo.findAll().stream().map(i -> {
                CourseResponseDto dto = AppUtils.getResponseFromCourse(i);
                logger.info("CourseService::getAllCourseList method execution ended");
                return dto;
            }).collect(Collectors.toList());
        } catch (Exception e) {
            logger.error("CourseService::getAllCourseList exception occur while fetching the Course object from Database ->{}", e.getMessage());
            throw new CourseServiceBusinessException("CourseService::getAllCourseList method failed  "+e.getMessage());
        }
    }

    @Override
    public List<CourseResponseDto> getAllCourseById(Iterable<Integer> id) {
        logger.info("CourseService::getAllCourseById method execution started");
        try {
            List<Course> list = courseRepo.findAllById(id);
            logger.info("CourseService::getAllCourseById method execution ended");
            return list.stream().map(AppUtils::getResponseFromCourse).collect(Collectors.toList());
        } catch (Exception e) {
            logger.error("CourseService::getAllCourseById exception occur while fetching the Course object from Database ->{}", e.getMessage());
            throw new CourseServiceBusinessException("CourseService::getAllCourseById method failed "+e.getMessage());
        }
    }

    @Override
    public CourseResponseDto getCourseById(int id) {
        logger.info("CourseService::getCourseById method execution started");
        try {
            logger.debug("CourseService::getCourseById method input ->{}",id);
            Course course = courseRepo.findById(id).orElseThrow(() -> new CourseServiceBusinessException("No course with the given Id"));
            logger.info("CourseService::getCourseById method execution ended");
            return AppUtils.getResponseFromCourse(course);
        } catch (Exception e) {
            logger.error("CourseService::getCourseById exception occur while fetching the Course object from Database ->{}", e.getMessage());
            throw new CourseServiceBusinessException("CourseService::getCourseById method failed "+e.getMessage());
        }
    }

    @Override
    public CourseResponseDto updateCourse(int id, CourseRequestDto courseRequestDto) {
        try {
            logger.info("CourseService::updateCourse method execution started");
            logger.debug("CourseService::updateCourse method input ->{}",courseRequestDto.toString());
            Course course = courseRepo.findById(id).orElseThrow(() -> new CourseServiceBusinessException("No course with the given Id"));
            logger.debug("CourseService::updateCourse method Course from Database {}", course.toString());
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
            CourseResponseDto courseResponseDto = AppUtils.getResponseFromCourse(updatedCourse);
            logger.debug("CourseService::updateCourse method CourseResponseDto {}", courseResponseDto.toString());
            logger.info("CourseService::updateCourse method execution ended");
            return courseResponseDto;
        } catch (Exception e) {
            logger.error("CourseService::updateCourse exception occur while updating the Course object to Database ->{}", e.getMessage());
            throw new CourseServiceBusinessException("CourseService::updateCourse method failed "+e.getMessage());
        }
    }

    @Override
    public String deleteCourseById(int id) {
        try {
            logger.info("CourseService::deleteCourseById method execution started");
            logger.debug("CourseService::deleteCourseById method input {}",id);
            courseRepo.deleteById(id);
            logger.info("CourseService::deleteCourseById method execution ended");
            return "Deleted course with the given Id  " + id;
        } catch (Exception e) {
            logger.error("CourseService::deleteCourseById exception occur while deleting the Course object from Database ->{}", e.getMessage());
            throw new CourseServiceBusinessException("CourseService::deleteCourseById method failed "+e.getMessage());
        }
    }
}
