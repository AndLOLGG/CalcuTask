package org.example.calcutask.Repository;

import org.example.calcutask.Model.Project;
import org.example.calcutask.RowMapper.ProjectRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProjectRepository {

    private final JdbcTemplate jdbcTemplate;

    public ProjectRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
//Test version af addProject
public void addProject(Project project) {
    String sql = "INSERT INTO projects (name, description, owner_id) VALUES (?, ?, ?)";
    jdbcTemplate.update(sql,
            project.getProjectName(),
            project.getProjectDescription(),
            project.getUserId());
}
// Metoden nedunder er den "rigtige" addProject, når vi skal bruge owner/user_id.
//    public void addProject(Project project) {
//        String sql = "INSERT INTO projects (name, description, owner_id) VALUES (?, ?, ?)";
//        jdbcTemplate.update(sql, project.getProjectName(), project.getProjectDescription(), project.getUserId());
//    }
    public List<Project> getProjectsByUserId(Integer userId) {
        String sql = "SELECT * FROM projects WHERE owner_id = ?";
        return jdbcTemplate.query(sql, new Object[]{userId}, new ProjectRowMapper());
    }

//    public List<Project> findByUserId(int userId) {
//        String sql = "SELECT * FROM projects WHERE owner_id = ?";
//        return template.query(sql, new ProjectRowMapper(), userId);
//    }
}

