package com.mylearning.springbootmappingonetoone.experimentRepository;

import com.mylearning.springbootmappingonetoone.experiment.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepo extends JpaRepository<Item, Integer> {
}
