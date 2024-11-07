package com.mylearning.springbootmappingmanytmany.experiment;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name="Category")
public class Category {
    @Id
    private Integer categoryId;
    private String categoryName;
    private String categoryDescription;

    // we cannot apply mappedBy in @ManyToOne
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "category")
    private Set<Item> items;

    @Override
    public String toString() {
        return "Category{" +
                "categoryId=" + categoryId +
                ", categoryName='" + categoryName + '\'' +
                ", categoryDescription='" + categoryDescription + '\'' +
                ", products=" + items +
                '}';
    }
}
