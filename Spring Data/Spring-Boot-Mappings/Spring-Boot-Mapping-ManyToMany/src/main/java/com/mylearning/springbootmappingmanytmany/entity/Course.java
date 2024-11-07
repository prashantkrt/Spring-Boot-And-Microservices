package com.mylearning.springbootmappingmanytmany.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/*
 *  A Student can enroll in many Courses.
 *  A Course can have many Students.
 * */
@Entity(name = "Course_TBL")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String courseName;
    private String teacherName;
    private int modules;
    private double fees;

    @ManyToMany(mappedBy = "courses") // Inverse side, referring to the courses field in Student
    @JsonBackReference
    private List<Student> students = new ArrayList<>();
}
