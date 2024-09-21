package com.mylearning.consumingrestfulservices.facultyserviceconsumer.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd-MM-yyyy")
    private Date startDate;
    private String courseType;
    private Double price;
    private boolean isCertificateAvailable;
    private String mobileNumber;
    private String emailAddress;
}

