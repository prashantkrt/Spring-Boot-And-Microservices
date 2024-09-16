package com.myLearning.beanValidation.dto;

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
    private String courseType;

    @Min(value = 1500, message = "Price value should be more than 1500/-")
    @Max(value = 5000, message = "Price value should not be more than 5000/-")
    private Double price;

    private boolean isCertificateAvailable;

    @Pattern(regexp = "^(0|91)?[6-9][0-9]{9}", message = "Please enter the valid mobile number")
    private String mobileNumber;

    @Email(message = "Email address is invalid")
    private String emailAddress;
}


/*
 * @NotNull ⇒ to say that a field must not be null
 * @NotEmpty ⇒ to say that a field must not be empty like list []
 * @NotBlank ⇒ combination of above two @NotNull + @NotEmpty
 * @Min and @Max => to say that a numerical field must is only valid when it's value is above or below a certain value
 * @Pattern => to say that a string is only valid when it matches a certain regular expression
 * @Email => to say string must be valid email
 * @Past => annotation in Java's Bean Validation API (like in Spring Boot) is used to validate that a date or time is in the past I
 * @PastOrPresent ⇒ validate a date that must be in the past or the present (i.e., it can be today's date)
 * */


/*
 * [abc] Single character of a, b or c
 * [^abc] Any character except a, b, or c (negation)
 * [a-z] Single character in a range: a to z
 * a|b Match either a or b
 * \d A digit 0 to 9
 * \w A word character [a-zA-Z_0-9]
 *   A lowercase letter (a to z)
 *   An uppercase letter (A to Z)
 *   An underscore (_)
 *   A digit (0 to 9)
 *
 * . Any single character
 * a{3} Exactly 3 as: aaa, X{n} X occurs n times only
 * a{3,} 3 or more a,  X{n,} X occurs n or more times
 * a{2,4} between 2 or 4 a: aa, aaa or aaaa, X{y,z} X occurs at least y times but less than z times
 * ^ The beginning of the line.
 * $ the end of the line
 * * Matches zero or more occurrence.  X* X occurs zero or more times.
 * + Matches one or more occurrences. X+ X occurs once or more times.
 * ? Matches zero or one occurrence (optional 0 or 1 occurrence): X?  X occurs once or not at all
 * */

