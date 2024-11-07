package com.mylearning.repository;

import com.mylearning.entity.Engineer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EngineerRepository extends JpaRepository<Engineer, Integer> {
}
