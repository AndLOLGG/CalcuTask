package org.example.calcutask.RowMapper;

import org.example.calcutask.Model.Project;
import org.example.calcutask.Model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProjectRowMapper implements RowMapper<Project> {
    @Override
    public Project mapRow(ResultSet rs, int rowNum) throws SQLException {
        Project project = new Project();
        project.setProjectId(rs.getInt("id"));
        project.setProjectName(rs.getString("name"));
        project.setProjectDescription(rs.getString("description"));
        return project;
    }
}
