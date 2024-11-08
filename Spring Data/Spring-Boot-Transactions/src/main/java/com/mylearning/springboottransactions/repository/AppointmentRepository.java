package com.mylearning.springboottransactions.repository;

import com.mylearning.springboottransactions.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, String> {
}
