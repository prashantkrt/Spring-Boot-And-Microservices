package com.mylearning.springboottransactions.service;

import com.mylearning.springboottransactions.repository.AppointmentRepository;
import com.mylearning.springboottransactions.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OnlineDoctorConsultingService {
    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;




}
