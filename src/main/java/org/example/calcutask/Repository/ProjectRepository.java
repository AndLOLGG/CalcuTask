package org.example.calcutask.Repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ProjectRepository {
    @Autowired
    private JdbcTemplate template;

    public void save(Project project) {
        String sql = "INSERT INTO projects (name, description, owner_id) VALUES (?, ?, ?)";
        template.update(sql, project.getName(), project.getDescription(), project.getOwnerId());
    }

    public List<Project> findByUserId(int userId) {
        String sql = "SELECT * FROM projects WHERE owner_id = ?";
        return template.query(sql, new ProjectRowMapper(), userId);
    }
}

