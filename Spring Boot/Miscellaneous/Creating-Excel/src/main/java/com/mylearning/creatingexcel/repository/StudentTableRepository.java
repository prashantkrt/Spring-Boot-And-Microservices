package com.mylearning.creatingexcel.repository;

import com.mylearning.creatingexcel.model.StudentTable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentTableRepository extends JpaRepository<StudentTable, Integer> {
}
