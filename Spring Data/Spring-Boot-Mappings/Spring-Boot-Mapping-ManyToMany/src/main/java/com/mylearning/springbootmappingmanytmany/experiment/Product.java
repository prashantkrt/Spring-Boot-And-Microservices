package com.mylearning.springbootmappingmanytmany.experiment;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/*
 *  Category can have many Products.
 *  Product can have many Categories.
 * */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name="ProductDetails")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productId;
    private String productName;
    private String productDescription;
    private Double productPrice;

    //owner
    @ManyToMany(mappedBy = "products")
    private List<Category> category = new ArrayList<>();

    public Product(String productName, String productDescription, double productPrice) {
        this.productName = productName;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
    }

    @Override
    public String toString() {
        return "Item{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", productDescription='" + productDescription + '\'' +
                ", productPrice=" + productPrice +
                ", category=" + category +
                '}';
    }
}
