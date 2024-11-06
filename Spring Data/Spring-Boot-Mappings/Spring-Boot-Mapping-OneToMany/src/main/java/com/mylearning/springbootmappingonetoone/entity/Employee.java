package com.mylearning.springbootmappingonetoone.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="Employee_Table")
public class Employee {
    @Id
    private int employeeId;
    private String employeeName;
    private String employeeDepartment;
    private Double salary;

//    @OneToMany(mappedBy = "address")
    @OneToMany
    private List<Address> addressList;
}

