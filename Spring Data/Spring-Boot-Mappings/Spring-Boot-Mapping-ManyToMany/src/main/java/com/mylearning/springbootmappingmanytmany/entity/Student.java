package com.mylearning.springbootmappingmanytmany.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    private Integer id;
    private String name;
    private int age;
    private String department;

    //owner
    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonManagedReference
    @JoinTable(
            name = "student_course", // Name of the join table
            joinColumns = @JoinColumn(name = "student_id",referencedColumnName = "id"), // Foreign key for Student in the join table
            inverseJoinColumns = @JoinColumn(name = "course_id",referencedColumnName = "id") // Foreign key for Course in the join table
    )
    private List<Course> courses;
}