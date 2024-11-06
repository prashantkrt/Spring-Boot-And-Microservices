package com.mylearning.springbootmappingonetoone.experiment;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

/*
 *  Category can have many Products.
 *  Product can have many Categories.
 * */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name="Product")
public class Product {
    @Id
    private Integer productId;
    private String productName;
    private String productDescription;
    private Double productPrice;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Category> category;

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
