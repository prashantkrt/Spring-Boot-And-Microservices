package com.mylearning.repository;

import com.mylearning.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface ProjectRepository extends JpaRepository<Project,Integer> {

    @Query(value = "SELECT p.projectName, p.projectCode, p.projectDescription, e.name, e.email FROM Project p JOIN Engineer e ON p.id = e.project_id", nativeQuery = true)
    public List<Object[]> getProjectSpecificInformationWithSQL();

    //p.employee is used instead of Engineer e since we are referring to the employee relationship directly in Project.
    //There’s no need for ON since JPQL automatically understands the join based on the defined relationship.
    @Query(value = "SELECT p.projectName, p.projectCode, p.projectDescription, e.name ,e.email FROM Project p JOIN p.engineer e", nativeQuery = false)
    public List<Object[]> getProjectSpecificInformationWithJPQL();
}
