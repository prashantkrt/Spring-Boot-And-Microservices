package com.mylearning.dto;

import com.mylearning.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class OrderResponseDto {
    private String message;
    private Order order;
    private PaymentDto paymentResponse;
    private UserDto userInfo;
}
