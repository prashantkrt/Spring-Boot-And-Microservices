package com.mylearning.consumingrestfulservices.facultyserviceconsumer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponseDTO {
    private String message;
    private int statusCode;
    private String timestamp;
}

