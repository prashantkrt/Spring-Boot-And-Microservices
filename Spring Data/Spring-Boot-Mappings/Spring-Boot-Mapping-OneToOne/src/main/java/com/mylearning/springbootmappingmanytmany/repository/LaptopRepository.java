package com.mylearning.springbootmappingmanytmany.repository;

import com.mylearning.springbootmappingmanytmany.entity.Laptop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LaptopRepository extends JpaRepository<Laptop, Integer> {
}
