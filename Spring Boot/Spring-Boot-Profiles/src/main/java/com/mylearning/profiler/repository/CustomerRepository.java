package com.mylearning.profiler.repository;

import com.mylearning.profiler.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}
