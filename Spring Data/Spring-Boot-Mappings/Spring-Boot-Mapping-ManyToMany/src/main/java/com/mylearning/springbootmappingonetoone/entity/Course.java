package com.mylearning.springbootmappingonetoone.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/*
 *  A Student can enroll in many Courses.
 *  A Course can have many Students.
 * */
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String courseName;

    @ManyToMany(mappedBy = "courses") // Inverse side, referring to the courses field in Student
    private List<Student> students = new ArrayList<>();

}
