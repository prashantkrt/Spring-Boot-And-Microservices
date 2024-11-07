package com.mylearning.controller;


import com.mylearning.entity.Engineer;
import com.mylearning.entity.Project;
import com.mylearning.service.ProjectManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectManagerController {

    @Autowired
    private ProjectManagerService projectManagerService;

    @PostMapping("/save")
    public Project addNewProject(@RequestBody Project project) {
        return projectManagerService.saveProject(project);
    }

    @GetMapping("/fetchAll/project")
    public List<Project> getProjects() {
        return projectManagerService.getProjects();
    }

    @GetMapping("/fetchAll/engineers")
    public List<Engineer> getEngineers() {
        return projectManagerService.getEngineers();
    }

    @DeleteMapping("/remove/{projectId}")
    public String deleteProject(@PathVariable int projectId) {
        return projectManagerService.deleteProject(projectId);
    }
}
