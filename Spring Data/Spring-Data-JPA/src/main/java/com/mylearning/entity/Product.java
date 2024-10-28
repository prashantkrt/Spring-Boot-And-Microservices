package com.mylearning.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="Product_Table")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "productID")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "price")
    private double price;
    @Column(name="description")
    private String description;
    @Column(name = "productType")
    private String productType;
}
