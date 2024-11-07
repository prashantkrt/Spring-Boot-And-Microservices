package com.mylearning.springbootmappingmanytmany.entity;

import jakarta.persistence.*;
import java.util.List;
import java.util.ArrayList;
/*
*  A Student can enroll in many Courses.
*  A Course can have many Students.
* */
@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany
    @JoinTable(
            name = "student_course", // Name of the join table
            joinColumns = @JoinColumn(name = "student_id"), // Foreign key for Student in the join table
            inverseJoinColumns = @JoinColumn(name = "course_id") // Foreign key for Course in the join table
    )
    private List<Course> courses = new ArrayList<>();

    // Getters and setters
}