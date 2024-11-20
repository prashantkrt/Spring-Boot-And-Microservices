package com.mylearning.rabbitmqpubsub.repository;

import com.mylearning.rabbitmqpubsub.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
