package com.mylearning.springbootmappingmanytmany.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.ArrayList;
/*
*  A Student can enroll in many Courses.
*  A Course can have many Students.
* */

@Entity(name="STUDENT_TBL")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int age;
    private String department;

    //owner
    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinTable(
            name = "student_course", // Name of the join table
            joinColumns = @JoinColumn(name = "student_id",referencedColumnName = "id"), // Foreign key for Student in the join table
            inverseJoinColumns = @JoinColumn(name = "course_id",referencedColumnName = "id") // Foreign key for Course in the join table
    )
    private List<Course> courses;
}