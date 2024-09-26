package com.mylearning.creatingexcel.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "Student")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class StudentTable {
    @Id
    @Column(name = "ID",length = 10,nullable = false, updatable = false)
    private Long id;

    @Column(name = "Name",length = 100,nullable = false)
    private String name;

    @Column(name = "Gender",length = 10,nullable = false)
    private String gender;

    @Column(name = "Age",length = 10,nullable = false)
    private int age;
}
