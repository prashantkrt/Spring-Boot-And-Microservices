package com.mylearning.redispubsub.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaymentDto {
    private String transactionId;
    private String sourceAccount;
    private String destinationAccount;
    private double amount;
    private Date transactionDate;
}
