package com.mylearning.springbootbatchfaulttolerance.repository;


import com.mylearning.springbootbatchfaulttolerance.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
