package org.example.calcutask.Repository;

import org.example.calcutask.Model.Project;
import org.example.calcutask.RowMapper.ProjectRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProjectRepository {
    @Autowired
    private JdbcTemplate template;

    public void save(Project project) {
        String sql = "INSERT INTO projects (name, description, owner_id) VALUES (?, ?, ?)";
        template.update(sql, project.getProjectName(), project.getProjectDescription(), project.getUserId());
    }

    public List<Project> findByUserId(int userId) {
        String sql = "SELECT * FROM projects WHERE owner_id = ?";
        return template.query(sql, new ProjectRowMapper(), userId);
    }
}

