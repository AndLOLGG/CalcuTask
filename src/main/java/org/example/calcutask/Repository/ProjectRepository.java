package org.example.calcutask.Repository;

import org.example.calcutask.Model.Project;
import org.example.calcutask.RowMapper.ProjectRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ProjectRepository {

    private final JdbcTemplate jdbcTemplate;

    public ProjectRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void addProject(Project project) {
        String sql = "INSERT INTO project (project_name, project_description, owner_id) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, project.getProjectName(), project.getProjectDescription(), project.getUserId());
    }

    public void deleteProject(int projectId) {
        String sql = "DELETE FROM project WHERE project_id = ?";
        jdbcTemplate.update(sql, projectId);
    }

    public Project findById(int projectId) {
        String sql = "SELECT * FROM project WHERE project_id = ?";
        return jdbcTemplate.queryForObject(sql, new ProjectRowMapper(), projectId);
    }

    public void update(Project project) {
        String sql = "UPDATE project SET project_name = ?, project_description = ? WHERE project_id = ?";
        jdbcTemplate.update(sql, project.getProjectName(), project.getProjectDescription(), project.getProjectId());
    }
}