package com.mylearning.springbootmappingonetoone.repository;

import com.mylearning.springbootmappingonetoone.entity.Laptop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LaptopRepository extends JpaRepository<Laptop, Integer> {
}
