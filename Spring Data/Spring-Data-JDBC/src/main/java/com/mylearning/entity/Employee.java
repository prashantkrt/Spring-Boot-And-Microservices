package com.mylearning.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Employee {
    private Integer id;
    private String name;
    private String department;
    private String email;
    private String phone;
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd/MM/yy")
    private Date dateOfJoining;
}
