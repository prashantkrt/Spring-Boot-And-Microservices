package com.myLearning.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="Employee_Table")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String dept;
    private double salary;
    @Column(length = 50, unique = true)
    @Email(message = "Email should be valid")
    private String email;
    @Column(unique = true)
    @NotBlank(message = "User name is mandatory")
    private String username;
    private String password;
    private String roles; // ROLE_HR , ROLE_MANAGER, ROLE_EMPLOYEE
}