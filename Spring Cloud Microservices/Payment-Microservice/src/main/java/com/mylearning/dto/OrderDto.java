package com.mylearning.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class OrderDto {
    private Integer id;
    private String name;
    private String category;
    private Double price;
    private Date orderDate;
    private String orderId;
    private String userId;
    private String paymentMode;
}
