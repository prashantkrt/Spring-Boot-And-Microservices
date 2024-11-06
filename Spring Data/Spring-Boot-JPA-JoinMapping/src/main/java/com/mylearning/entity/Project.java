package com.mylearning.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name="Project")
public class Project {
    @Id
    @Column(name="Id")
    private Long projectId;
    private String projectName;
    private String projectDescription;
    private String projectLocation;
}
