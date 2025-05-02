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
        project.setProjectId(rs.getInt("project_id"));
        project.setProjectName(rs.getString("project_name"));
        project.setProjectDescription(rs.getString("project_description"));
        project.setCreatedDate(rs.getDate("created_date"));
        project.setCreatedBy(rs.getString("created_by"));
        project.setUserId(Integer.parseInt(rs.getString("user_id")));
        return project;
    }
}
