package com.mylearning.rabbitmqpubsub.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaymentRequest {
    private String transactionId;
    private String sourceAccount;
    private String destinationAccount;
    private double amount;
    private Date transactionDate;
}
