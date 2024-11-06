package com.mylearning.springbootmappingonetoone.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name="Address")
public class Address {
    @Id
    private Integer addressId;
    private String street;
    private String city;
    private String state;
    private String zip;
    private String country;

    @ManyToOne
//    @JoinColumn(name="employee_id")
    private Employee employee; // this will manage the relationship
}
