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
@Entity(name="Category")
public class Category {
    @Id
    private Integer categoryId;
    private String categoryName;
    private String categoryDescription;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "category")
    private List<Product> products;

    @Override
    public String toString() {
        return "Category{" +
                "categoryId=" + categoryId +
                ", categoryName='" + categoryName + '\'' +
                ", categoryDescription='" + categoryDescription + '\'' +
                ", products=" + products +
                '}';
    }
}
