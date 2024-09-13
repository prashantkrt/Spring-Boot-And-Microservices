package com.mylearning.webservices.restfulwebservices.dto;

import java.util.Date;


public class CourseDto {

    private String courseName;
    private String description;
    private String traineeName;
    private String duration; // days
    private Date startDate;
    private String courseType;  // Live or Recording
    private Double price;
    private boolean isCertificateAvailable;

    public CourseDto() {
    }

    public CourseDto(String courseName, String description, String traineeName, String duration, Date startDate, String courseType, Double price, boolean isCertificateAvailable) {
        this.courseName = courseName;
        this.description = description;
        this.traineeName = traineeName;
        this.duration = duration;
        this.startDate = startDate;
        this.courseType = courseType;
        this.price = price;
        this.isCertificateAvailable = isCertificateAvailable;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTraineeName() {
        return traineeName;
    }

    public void setTraineeName(String traineeName) {
        this.traineeName = traineeName;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getCourseType() {
        return courseType;
    }

    public void setCourseType(String courseType) {
        this.courseType = courseType;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public boolean isCertificateAvailable() {
        return isCertificateAvailable;
    }

    public void setCertificateAvailable(boolean certificateAvailable) {
        isCertificateAvailable = certificateAvailable;
    }
}
