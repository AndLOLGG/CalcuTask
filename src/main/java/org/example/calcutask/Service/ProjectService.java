package org.example.calcutask.Service;

import org.example.calcutask.Model.Project;
import org.example.calcutask.Repository.ProjectRepository;
import org.example.calcutask.RowMapper.ProjectRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private ProjectRepository projectRepository;

    public void addProject(Project project) {

        projectRepository.addProject(project);

        System.out.println("project saved successfully!");
    }

    public List<Project> getProjectsByUserId(int userId) {
        String sql = "SELECT * FROM project WHERE owner_id = ?";
        return jdbcTemplate.query(sql, new Object[]{userId}, new ProjectRowMapper());
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
