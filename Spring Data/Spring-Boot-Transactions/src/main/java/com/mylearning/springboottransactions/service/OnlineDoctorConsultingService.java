package com.mylearning.springboottransactions.service;

import com.mylearning.springboottransactions.dto.PatientAppointmentRequest;
import com.mylearning.springboottransactions.entity.Appointment;
import com.mylearning.springboottransactions.entity.Patient;
import com.mylearning.springboottransactions.repository.AppointmentRepository;
import com.mylearning.springboottransactions.repository.PatientRepository;
import com.mylearning.springboottransactions.utilty.PromoCodeValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
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
        someMethod();
        readData();
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


    //@Transactional(propagation = Propagation.REQUIRED) by default will follow the same transaction
    //Now this won't work in the existing transaction it will have its own
    @Transactional(propagation = Propagation.REQUIRES_NEW, timeout = 10)
    public void someMethod() {
        //Some logic
    }

    //@Transactional(timeout = 5) specifies that the transaction should complete within 5 seconds, or it will roll back.
    @Transactional(readOnly = true, timeout = 20)
    public void readData() {
        // some logic to just fetch the data
    }


    //Defines the level of isolation between transactions to avoid issues like dirty reads or lost updates.
    //@Transactional(isolation = Isolation.SERIALIZABLE) //Ensures full isolation by preventing dirty reads, non-repeatable reads, and phantom reads.
    //@Transactional(isolation = Isolation.READ_UNCOMMITTED) //Allows dirty reads, meaning data changes from other uncommitted transactions can be read.
    @Transactional(isolation = Isolation.READ_COMMITTED) //Prevents dirty reads; only committed changes are visible.
    public String logicalMethod() {
        return null;
    }
}
