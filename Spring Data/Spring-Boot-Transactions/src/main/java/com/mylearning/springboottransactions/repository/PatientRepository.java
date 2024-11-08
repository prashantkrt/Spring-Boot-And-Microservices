package com.mylearning.springboottransactions.repository;

import com.mylearning.springboottransactions.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Integer> {
}
