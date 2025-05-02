package org.example.calcutask.RowMapper;

import org.example.calcutask.Model.Project;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProjectRowMapper implements RowMapper<Project> {
    @Override
    public Project mapRow(ResultSet rs, int rowNum) throws SQLException {
        Project project = new Project();
        project.setProjectId(rs.getInt("projectId"));
        project.setProjectName(rs.getString("projectName"));
        project.setProjectDescription(rs.getString("projectDescription"));
        project.setCreatedDate(rs.getDate("createdDate"));
        project.setCreatedBy(rs.getString("createdBy"));
        return project;
    }
}
