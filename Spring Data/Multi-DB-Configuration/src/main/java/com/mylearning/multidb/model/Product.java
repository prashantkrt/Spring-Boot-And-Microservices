package com.mylearning.multidb.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="PRODUCT_DESCRITPION")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer pid;
    private String productName;
    private String description;


    public Product(String productName, String description) {
        this.productName = productName;
        this.description = description;
    }
}
