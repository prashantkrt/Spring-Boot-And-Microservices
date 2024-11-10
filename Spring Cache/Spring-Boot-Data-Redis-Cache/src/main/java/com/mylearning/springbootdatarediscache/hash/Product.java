package com.mylearning.springbootdatarediscache.hash;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Product implements Serializable {
    private String id;
    private String name;
    private double price;
}