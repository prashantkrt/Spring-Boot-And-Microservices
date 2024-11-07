package com.mylearning.springbootmappingmanytmany.entity;

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

    // Parent
    // Owning Side: The @JoinColumn(name="Student_Id")
    // annotation on the Laptop entity indicates that Laptop is responsible for managing the foreign key column in the database (Student_Id in the Laptop table).
    // No mappedBy on Laptop: Since Laptop does not use mappedBy, it’s considered the owner, while Student has mappedBy = "student", indicating that it is the inverse side and does not control the foreign key.
    @OneToOne
    @JoinColumn(name="Student_Id") // here it is mandatory to same
    private Student student; // will maintain the table

    // Override the toString() method to avoid circular references
    @Override
    public String toString() {
        return "Laptop{id=" + laptopId + ", model='" + modelNumber + "', brand='" + brand + "'}";
    }


}
