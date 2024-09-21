package com.mylearning.consumingrestfulservices.facultyserviceconsumer.Handler;


import com.mylearning.consumingrestfulservices.facultyserviceconsumer.dto.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestControllerAdvice
public class GlobalExceptionHandler {
    /*
     * When Producer Course-Service Application gives status as INTERNAL_SERVER_ERROR
     * We are handling it.
     * The @ExceptionHandler(Exception.class) catches all unhandled exceptions and returns a 500 response.
     *
     * */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorResponseDTO> handleInternalServerError(Exception ex) {
        ErrorResponseDTO errorResponse = new ErrorResponseDTO(
                ex.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                getCurrentTime()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /*
     * When Producer Course-Service Application gives status, as BAD_REQUEST
     * We are handling it.
     * The @ExceptionHandler(IllegalArgumentException.class) catches IllegalArgumentException (commonly thrown for bad input)
     * and returns a 400 response
     * */
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponseDTO> handleBadRequest(IllegalArgumentException ex) {
        ErrorResponseDTO errorResponse = new ErrorResponseDTO(
                ex.getMessage(),
                HttpStatus.BAD_REQUEST.value(),
                getCurrentTime()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    private String getCurrentTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.now().format(formatter);
    }
}
