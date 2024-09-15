package com.myLearning.beanValidation.dto;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class CourseRequestDto {
    private String courseName;
    private String description;
    private String traineeName;
    private String duration;
    private Date startDate;
    private String courseType;
    private Double price;
    private boolean isCertificateAvailable;
    private String emailAddress;
    private String mobileNumber;
}
