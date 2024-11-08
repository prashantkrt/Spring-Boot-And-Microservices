package com.mylearning.springboottransactions.dto;

import com.mylearning.springboottransactions.entity.Appointment;
import com.mylearning.springboottransactions.entity.Patient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PatientAppointmentRequest {
    private Patient patient;

    private Appointment appointment;
}
