package com.myLearning.beanValidation.exceptionHandling.handler;

import com.myLearning.beanValidation.exceptionHandling.dto.ErrorDto;
import com.myLearning.beanValidation.exceptionHandling.dto.ServiceResponse;
import com.myLearning.beanValidation.exceptionHandling.exception.CourseServiceBusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

// for creating global Exception handler
@RestControllerAdvice
public class ApplicationGlobalExceptionHandler {

    // This exception comes when we have passed the bad request for the field
    // MethodArgumentNotValidException
    // any bad request will come up here as all comes under MethodArgumentNotValidException
    // like yeh tab aaega "courseType": "kk" (it should be Live or Recording) jab courseTypeValidation fail hoga

    // this is the way to handle the Validation exception which we use such as @NotNull, @NotEmpty, @AntValidation...
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST) // if not given, then 200 Ok will come
    public ServiceResponse<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        ServiceResponse<?> response = new ServiceResponse<>();
        List<ErrorDto> errorDtoList = new ArrayList<>();
        // Jitney bhi MethodArgumentNotValidException wala exception aaya hoga sabko utha ke list me daalo
        /*
        * fieldName and errorMessage
        *  {
              "username": "Username cannot be empty",
               "age": "Age should be at least 18"
           }
        * */
        ex.getBindingResult().getAllErrors().forEach(error -> {
            errorDtoList.add(new ErrorDto(((FieldError) error).getField(), error.getDefaultMessage()));
        });
        response.setStatus(HttpStatus.BAD_REQUEST);
        response.setError(errorDtoList);
        return response;
    }

    @ExceptionHandler(value = CourseServiceBusinessException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) // It's a service exception
    public ServiceResponse<?> handleCourseServiceBusinessException(CourseServiceBusinessException ex) {
        ServiceResponse<?> response = new ServiceResponse<>();
        List<ErrorDto> errorDtoList = new ArrayList<>();
        errorDtoList.add(new ErrorDto(ex.getMessage()));
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        response.setError(errorDtoList);
        return response;
    }

}
