package com.mylearning.springboottransactions.controller;

import com.mylearning.springboottransactions.dto.PatientAppointmentRequest;
import com.mylearning.springboottransactions.service.OnlineDoctorConsultingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class OnlineDoctorConsultingController {

    @Autowired
    private OnlineDoctorConsultingService onlineDoctorConsultingService;

    @PostMapping("/bookAppointment")
    public String bookDoctorsAppointment(@RequestBody PatientAppointmentRequest request){
        return onlineDoctorConsultingService.appointmentBooking(request);
    }
}
