package com.myLearning.beanValidation.exceptionHandling.dto;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Getter
@Setter
public class ServiceResponse<T> {
    private T response;
    private HttpStatus status;
    //as there are chances that we will get multiple error or list of error
    private List<ErrorDto> error;

    public ServiceResponse(T response, HttpStatus status) {
    }
}
