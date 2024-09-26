package com.mylearning.scheduler.springbootscheduler.repository;

import com.mylearning.scheduler.springbootscheduler.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<Order, Integer> {
}
