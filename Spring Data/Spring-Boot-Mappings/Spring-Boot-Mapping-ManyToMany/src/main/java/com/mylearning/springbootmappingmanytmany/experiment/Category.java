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
 *
 * */
//@AllArgsConstructor
//@NoArgsConstructor
//@Data
//@Entity(name="CategoryDetails")
//public class Category {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer categoryId;
//    private String categoryName;
//    private String categoryDescription;
//
//    //owner
//    //@ManyToMany(cascade = CascadeType.ALL)
//    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
//    private List<Product> products = new ArrayList<>();
//
//    public Category(String categoryName, String categoryDescription) {
//        this.categoryName = categoryName;
//        this.categoryDescription = categoryDescription;
//    }
//
//    @Override
//    public String toString() {
//        return "Category{" +
//                "categoryId=" + categoryId +
//                ", categoryName='" + categoryName + '\'' +
//                ", categoryDescription='" + categoryDescription + '\'' +
//                ", products=" + products +
//                '}';
//    }
//}
