package com.mylearning.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name="Engineers")
public class Engineer {
    @Id
    @Column(name="EmpId")
    private Long employeeId;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String department;
}
