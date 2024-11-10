package com.mylearning.springbootdataredis.hash;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
@RedisHash("Product") // This annotation marks this class to be saved in Redis with the name "Product"
public class Product {

    @Id
    private String id; // Redis will use this as the key

    private String name;
    private double price;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dateAdded;

}