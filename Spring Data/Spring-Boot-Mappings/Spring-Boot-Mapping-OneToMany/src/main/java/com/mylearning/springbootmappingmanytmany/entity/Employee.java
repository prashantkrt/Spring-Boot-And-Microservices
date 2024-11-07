package com.mylearning.springbootmappingmanytmany.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "Employee_Table")
public class Employee {
    @Id
    private int employeeId;
    private String employeeName;
    private String employeeDepartment;
    private Double salary;

    @OneToMany(mappedBy = "employee",cascade = CascadeType.ALL)
    private List<Address> addressList;

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId=" + employeeId +
                ", employeeName='" + employeeName + '\'' +
                ", employeeDepartment='" + employeeDepartment + '\'' +
                ", salary=" + salary +
                ", addressList=" + addressList +
                '}';
    }
}

