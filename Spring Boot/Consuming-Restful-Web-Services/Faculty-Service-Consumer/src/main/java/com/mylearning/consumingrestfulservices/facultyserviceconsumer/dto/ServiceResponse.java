package com.mylearning.consumingrestfulservices.facultyserviceconsumer.dto;

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
    private List<ErrorResponseDTO> error;

    public ServiceResponse(T response, HttpStatus status) {
    }
}
