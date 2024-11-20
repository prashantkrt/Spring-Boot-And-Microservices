package com.mylearning.rabbitmqpubsub.repository;

import com.mylearning.rabbitmqpubsub.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
