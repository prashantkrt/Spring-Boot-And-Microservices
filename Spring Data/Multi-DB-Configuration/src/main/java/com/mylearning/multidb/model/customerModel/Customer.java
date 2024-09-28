package com.mylearning.multidb.model.customerModel;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "CUSTOMER_TABLE")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int cid;
    private String cname;
    private String customerAddress;

    public Customer(String cname, String customerAddress) {
        this.cname = cname;
        this.customerAddress = customerAddress;
    }
}
