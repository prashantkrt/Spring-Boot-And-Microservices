package com.mylearning.springbootmappingonetoone.experimentRepository;

import com.mylearning.springbootmappingonetoone.experiment.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category, Integer> {
}
