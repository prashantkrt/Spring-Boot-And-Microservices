package com.mylearning.springbootmappingonetoone.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name="Laptop_Table")
public class Laptop {
    @Id
    private Integer laptopId;
    private String modelNumber;
    private String brand;

    @OneToOne
    @JoinColumn(name="Student_Id") // here it is mandatory to same
    private Student student; // will maintain the table
}
