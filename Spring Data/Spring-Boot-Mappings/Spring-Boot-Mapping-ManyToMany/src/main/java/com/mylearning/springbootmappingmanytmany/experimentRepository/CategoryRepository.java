package com.mylearning.springbootmappingmanytmany.experimentRepository;

import com.mylearning.springbootmappingmanytmany.experiment.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
