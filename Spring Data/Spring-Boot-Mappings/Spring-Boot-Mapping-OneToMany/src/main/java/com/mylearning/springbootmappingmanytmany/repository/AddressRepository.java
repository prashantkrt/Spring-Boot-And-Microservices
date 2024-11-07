package com.mylearning.springbootmappingmanytmany.repository;

import com.mylearning.springbootmappingmanytmany.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Integer> {
}
