package com.mylearning.springbootmappingmanytmany.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "Address")
public class Address {
    @Id
    private Integer addressId;
    private String street;
    private String city;
    private String state;
    private String zip;
    private String country;

    // @ManyToOne side of the relationship is always the owning side and manages the foreign key,
    // so it doesn’t need or support mappedBy
    // always owner side
    @ManyToOne
    @JoinColumn(name="employee_id")
    private Employee employee; // this will manage the relationship


    @Override
    public String toString() {
        return "Address{" +
                "addressId=" + addressId +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zip='" + zip + '\'' +
                ", country='" + country + '\'' +
                ", employee=" + employee +
                '}';
    }
}
