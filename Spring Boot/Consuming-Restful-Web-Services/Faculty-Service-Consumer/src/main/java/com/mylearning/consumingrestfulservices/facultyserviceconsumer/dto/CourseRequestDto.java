package com.mylearning.consumingrestfulservices.facultyserviceconsumer.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.myLearning.beanValidation.exceptionHandling.documentation.annotation.CourseTypeValidation;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Schema(
        name = "CourseRequestDto",
        description = "CourseRequestDto information"
)
public class CourseRequestDto {

    @Schema(
            description = "Hold the name of the course",
            example = "Java"
    )
    @NotBlank(message = "Course Name cannot be null or empty")
    private String courseName;

    @Schema(
            description = "Hold the description of the course",
            example = "Java Backend"
    )
    @NotEmpty(message = "Description must be present")
    @Size(min = 5, max = 100, message = "Description must be between 5 and 100 characters")
    // or @Length(min = 5,max = 100,message = "")
    private String description;

    @Schema(
            description = "Hold the trainer name of the course",
            example = "John Doe"
    )
    @NotEmpty(message = "Trainer name should not be empty")
    private String traineeName;

    @Schema(
            description = "Hold the duration of the course",
            example = "2 months"
    )
    @NotNull(message = "course duration should not be null")
    private String duration;

    @Schema(
            description = "Hold the start date of the course",
            example = "12/12/2024"
    )
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @PastOrPresent(message = "Course Date can be past or present date only")
    // @Past(message = "Only past date is allowed")
    private Date startDate;

    @Schema(
            description = "Hold the course Type of the course",
            example = "Live"
    )
    @NotEmpty(message = "Course Type should not be empty or null")
    @CourseTypeValidation(message = "Course type should be live or recording")
    private String courseType;

    @Schema(
            description = "Hold the price of the course",
            example = "1700.00"
    )
    @Min(value = 1500, message = "Price value should be more than 1500/-")
    @Max(value = 5000, message = "Price value should not be more than 5000/-")
    private Double price;

    //optional
    @Schema(
            description = "Hold the certification detials of the course",
            example = "false"
    )
    private boolean isCertificateAvailable;

    @Schema(
            description = "Hold the mobile number",
            example = "0987654321"
    )
    @Pattern(regexp = "^(0|91)?[6-9][0-9]{9}", message = "Please enter the valid mobile number")
    private String mobileNumber;

    @Schema(
            description = "Hold the email",
            example = "bugabuga@bugabuga.com"
    )
    @Email(message = "Email address is invalid")
    private String emailAddress;
}

