package com.mylearning.springboottransactions.service;

import com.mylearning.springboottransactions.dto.PatientAppointmentRequest;
import com.mylearning.springboottransactions.entity.Appointment;
import com.mylearning.springboottransactions.entity.Patient;
import com.mylearning.springboottransactions.repository.AppointmentRepository;
import com.mylearning.springboottransactions.repository.PatientRepository;
import com.mylearning.springboottransactions.utilty.PromoCodeValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
//@Transactional ⇒ when all the methods of this class need the transactional features,then we should write at the class
public class OnlineDoctorConsultingService {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Transactional
    public String appointmentBooking(PatientAppointmentRequest patientAppointmentRequest) {
        //Saving the patient Details
        Patient patient = patientAppointmentRequest.getPatient();
        Integer patientId = patientRepository.save(patient).getPatientId();
        //Fetching the appointment
        //Validating the promo
        //Saving
        Appointment appointment = patientAppointmentRequest.getAppointment();
        if (appointment.getPromoCode() != null) {
            if (PromoCodeValidator.validatePromoCode(appointment.getPromoCode())) {
                appointment.setPatientId(patientId);
                String appointmentNo = appointmentRepository.save(appointment).getAppointmentId();
                return "Hi " + patient.getName() +
                        " Your appointment booked successfully & appointment number is "
                        + appointmentNo;
            } else {
                throw new RuntimeException("Invalid PromoCode");
            }
        } else
            throw new RuntimeException("Invalid appointment");
    }
}
