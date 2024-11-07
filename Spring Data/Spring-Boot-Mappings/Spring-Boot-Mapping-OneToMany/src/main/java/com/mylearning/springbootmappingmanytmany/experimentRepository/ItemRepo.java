package com.mylearning.springbootmappingmanytmany.experimentRepository;

import com.mylearning.springbootmappingmanytmany.experiment.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepo extends JpaRepository<Item, Integer> {
}
