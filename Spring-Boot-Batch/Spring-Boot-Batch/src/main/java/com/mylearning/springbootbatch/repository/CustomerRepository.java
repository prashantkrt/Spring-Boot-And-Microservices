package com.mylearning.springbootbatch.repository;

import com.mylearning.springbootbatch.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
