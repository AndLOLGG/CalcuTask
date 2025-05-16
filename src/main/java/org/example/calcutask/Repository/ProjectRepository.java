package org.example.calcutask.Repository;

import org.example.calcutask.Model.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProjectRepository {

    @Autowired
    private JdbcTemplate template;

    public void save(Project project) {
        String sql = "INSERT INTO projects (name, description) VALUES (?, ?)";
        template.update(sql, project.getProjectName(), project.getProjectDescription());
    }

    public List<Project> findAll() {
        String sql = "SELECT * FROM projects";
        return template.query(sql, (rs, rowNum) -> {
            Project project = new Project();
            project.setId(rs.getInt("id"));
            project.setName(rs.getString("name"));
            project.setDescription(rs.getString("description"));
            return project;
        });
    }
}