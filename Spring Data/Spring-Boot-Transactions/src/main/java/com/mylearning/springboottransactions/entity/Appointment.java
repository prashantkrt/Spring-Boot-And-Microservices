package com.mylearning.springboottransactions.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;


import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name="Appointment_Booking_Table")
public class Appointment {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String appointmentId;
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd/MM/yyyy")
    private LocalDate appointmentDate;
    private String doctorName;
    private String disease;
    private String promoCode;
    private Integer patientId;
}
