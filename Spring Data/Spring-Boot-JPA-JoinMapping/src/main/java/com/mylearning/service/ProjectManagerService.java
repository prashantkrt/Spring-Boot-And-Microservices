package com.mylearning.service;


import com.mylearning.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectManagerService {

    @Autowired
    private ProjectRepository projectRepository;


}
