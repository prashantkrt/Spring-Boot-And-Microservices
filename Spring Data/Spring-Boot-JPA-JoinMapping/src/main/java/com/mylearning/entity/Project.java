package com.mylearning.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name="Project")
public class Project {

    @Id
    @Column(name="Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer projectId;
    private String projectName;
    private String projectDescription;
    private String projectLocation;
    private String projectStatus;
    private String projectType;
    private String projectCode;

    //Unidirectional
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(
            name = "Project_Id",
            referencedColumnName = "Id" // projectId yeh wala field jaega Engineer table me as foreign key!!!
    )
    private List<Engineer> engineer;
}
