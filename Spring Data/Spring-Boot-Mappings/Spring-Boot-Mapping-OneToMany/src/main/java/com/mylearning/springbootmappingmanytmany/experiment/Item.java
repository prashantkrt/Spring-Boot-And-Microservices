package com.mylearning.springbootmappingmanytmany.experiment;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name="Item")
public class Item {
    @Id
    private Integer itemId;
    private String itemName;
    private String itemDescription;
    private Double itemPrice;

    // @ManyToOne side of the relationship is always the owning side and manages the foreign key,
    // so it doesn’t need or support mappedBy
    @ManyToOne(fetch = FetchType.EAGER)
    private Category category;

    @Override
    public String toString() {
        return "Item{" +
                "itemId=" + itemId +
                ", itemName='" + itemName + '\'' +
                ", itemDescription='" + itemDescription + '\'' +
                ", itemPrice=" + itemPrice +
                ", category=" + category +
                '}';
    }
}
