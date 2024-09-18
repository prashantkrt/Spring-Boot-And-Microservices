package com.myLearning.beanValidation.exceptionHandling.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDto {
    private String fieldName;
    private String errorMessage;

    public ErrorDto(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}

//
// Example : -
//{
//        "response": null,
//        "status": "BAD_REQUEST",
//        "error": [
//        {
//        "fieldName": "mobileNumber",
//        "errorMessage": "Please enter the valid mobile number"
//        },
//        {
//        "fieldName": "courseType",
//        "errorMessage": "Course type should be live or recording"
//        },
//        {
//        "fieldName": "emailAddress",
//        "errorMessage": "Email address is invalid"
//        },
//        {
//        "fieldName": "price",
//        "errorMessage": "Price value should not be more than 5000/-"
//        },
//        {
//        "fieldName": "traineeName",
//        "errorMessage": "Trainer name should not be empty"
//        }
//        ]
//        }
