package com.mylearning.springbootmappingonetoone.repository;

import com.mylearning.springbootmappingonetoone.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Integer> {
}
