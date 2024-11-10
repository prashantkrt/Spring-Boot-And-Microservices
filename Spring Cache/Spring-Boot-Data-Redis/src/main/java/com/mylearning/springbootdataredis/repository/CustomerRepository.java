package com.mylearning.springbootdataredis.repository;

import com.mylearning.springbootdataredis.hash.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {
}
