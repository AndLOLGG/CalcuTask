package org.example.calcutask.Service;

import org.example.calcutask.Model.Project;
import org.example.calcutask.Repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public void addProject(Project project) {

        projectRepository.addProject(project);

        System.out.println("project saved successfully!");
    }

    public List<Project> getProjectsByUserId(int userId) {
        return projectRepository.getProjectsByUserId(userId);
    }
/**
 her refereres der til nogle metoder i projectRepository, der endnu ikke er lavet
    public void deleteProject(int id) {
        projectRepository.deleteById(id);
    }

    public void updateProject(Project project) {
        projectRepository.update(project);
    }
  **/

}
