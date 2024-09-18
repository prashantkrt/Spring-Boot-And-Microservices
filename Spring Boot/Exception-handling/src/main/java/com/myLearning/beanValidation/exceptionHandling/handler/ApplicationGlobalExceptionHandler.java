package com.myLearning.beanValidation.exceptionHandling.handler;


import com.myLearning.beanValidation.exceptionHandling.dto.ErrorDto;
import com.myLearning.beanValidation.exceptionHandling.dto.ServiceResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

// for creating global Exception handler
@RestControllerAdvice
public class ApplicationGlobalExceptionHandler {

    // This exception comes when we have passed the bad request for the field
    // MethodArgumentNotValidException
    // like yeh tab aaega  "courseType": "kk" ( it should be Live or Recording) jab courseTypeValidation fail hoga

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ServiceResponse<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        ServiceResponse<String> response = new ServiceResponse<>();
        List<ErrorDto> errorDtoList = new ArrayList<>();
        // Jitney bhi MethodArgumentNotValidException wala exception aaya hoga sabko utha ke list me daalo
        ex.getBindingResult().getAllErrors().forEach(error -> {
            errorDtoList.add(new ErrorDto(error.getDefaultMessage()));
        });
        response.setResponse("Exception Occurred");
        response.setStatus(HttpStatus.BAD_REQUEST);
        response.setError(errorDtoList);
        return response;
    }

}
