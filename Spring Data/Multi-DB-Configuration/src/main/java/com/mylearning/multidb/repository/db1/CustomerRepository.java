package com.mylearning.multidb.repository.db1;

import com.mylearning.multidb.model.customerModel.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}
