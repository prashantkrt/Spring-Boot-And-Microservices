package com.myLearning.beanValidation.exceptionHandling.documentation.dto;

import com.myLearning.beanValidation.exceptionHandling.documentation.annotation.CourseTypeValidation;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class CourseRequestDto {

    @NotBlank(message = "Course Name cannot be null or empty")
    private String courseName;

    @NotEmpty(message = "Description must be present")
    @Size(min = 5, max = 100, message = "Description must be between 5 and 100 characters")
    // or @Length(min = 5,max = 100,message = "")
    private String description;

    @NotEmpty(message = "Trainer name should not be empty")
    private String traineeName;

    @NotNull(message = "course duration should not be null")
    private String duration;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @PastOrPresent(message = "Course Date can be past or present date only")
    // @Past(message = "Only past date is allowed")
    private Date startDate;

    @NotEmpty(message = "Course Type should not be empty or null")
    @CourseTypeValidation(message = "Course type should be live or recording")
    private String courseType;

    @Min(value = 1500, message = "Price value should be more than 1500/-")
    @Max(value = 5000, message = "Price value should not be more than 5000/-")
    private Double price;

    //optional
    private boolean isCertificateAvailable;

    @Pattern(regexp = "^(0|91)?[6-9][0-9]{9}", message = "Please enter the valid mobile number")
    private String mobileNumber;

    @Email(message = "Email address is invalid")
    private String emailAddress;
}

