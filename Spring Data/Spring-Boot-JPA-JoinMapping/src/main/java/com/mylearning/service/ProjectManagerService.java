package com.mylearning.service;

import com.mylearning.entity.Engineer;
import com.mylearning.entity.Project;
import com.mylearning.repository.EngineerRepository;
import com.mylearning.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectManagerService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private EngineerRepository engineerRepository;


    public Project saveProject(Project project){
        return projectRepository.save(project);
    }

    public List<Project> getProjects(){
        return projectRepository.findAll();
    }

    public List<Engineer> getEngineers(){
        return engineerRepository.findAll();
    }

    public String deleteProject(int projectId){
        projectRepository.deleteById(projectId);
        return "project "+projectId+" deleted !!!";
    }
}
