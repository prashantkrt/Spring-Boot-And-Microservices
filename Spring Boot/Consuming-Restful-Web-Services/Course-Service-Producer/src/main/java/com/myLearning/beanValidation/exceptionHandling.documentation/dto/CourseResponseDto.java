package com.myLearning.beanValidation.exceptionHandling.documentation.dto;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class CourseResponseDto {
    private String courseName;
    private String description;
    private String traineeName;
    private String duration;
    private Date startDate;
    private String courseType;
    private Double price;
    private boolean isCertificateAvailable;
    private String courseUniqueCode;
    private String mobileNumber;
    private String emailAddress;
}
