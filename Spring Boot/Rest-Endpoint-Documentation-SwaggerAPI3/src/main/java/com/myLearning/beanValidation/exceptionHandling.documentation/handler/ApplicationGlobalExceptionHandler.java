package com.myLearning.beanValidation.exceptionHandling.documentation.handler;


import com.myLearning.beanValidation.exceptionHandling.documentation.dto.ErrorDto;
import com.myLearning.beanValidation.exceptionHandling.documentation.dto.ServiceResponse;
import com.myLearning.beanValidation.exceptionHandling.documentation.exception.CourseServiceBusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ApplicationGlobalExceptionHandler {

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ServiceResponse<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        ServiceResponse<?> response = new ServiceResponse<>();
        List<ErrorDto> errorDtoList = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            errorDtoList.add(new ErrorDto(((FieldError) error).getField(), error.getDefaultMessage()));
        });
        response.setStatus(HttpStatus.BAD_REQUEST);
        response.setError(errorDtoList);
        return response;
    }

    @ExceptionHandler(value = CourseServiceBusinessException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ServiceResponse<?> handleCourseServiceBusinessException(CourseServiceBusinessException ex) {
        ServiceResponse<?> response = new ServiceResponse<>();
        List<ErrorDto> errorDtoList = new ArrayList<>();
        errorDtoList.add(new ErrorDto(ex.getMessage()));
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        response.setError(errorDtoList);
        return response;
    }

}
