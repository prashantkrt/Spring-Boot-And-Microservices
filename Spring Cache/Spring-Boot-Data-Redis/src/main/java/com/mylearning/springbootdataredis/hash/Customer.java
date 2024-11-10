package com.mylearning.springbootdataredis.hash;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@RedisHash("CustomersInfo")
public class Customer {
    @Id
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd/MM/yyyy")
    private LocalDate dateOfBirth;
    private String phone;
}
