package com.myLearning.beanValidation.exceptionHandling.dto;

import lombok.*;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Getter
@Setter
public class ServiceResponse<T> {
    private T response;
    private HttpStatus status;
}
