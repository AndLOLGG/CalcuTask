package org.example.calcutask.RowMapperTest;

import org.example.calcutask.Model.Project;
import org.example.calcutask.RowMapper.ProjectRowMapper;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProjectRowMapperTest {

    @Test
    void testMapRow() throws SQLException {
        // Mock ResultSet
        ResultSet resultSet = mock(ResultSet.class);
        when(resultSet.getInt("project_id")).thenReturn(101);
        when(resultSet.getString("project_name")).thenReturn("Test Project");
        when(resultSet.getString("project_description")).thenReturn("This is a test project.");
        when(resultSet.getInt("owner_id")).thenReturn(1);

        // Test the RowMapper
        ProjectRowMapper rowMapper = new ProjectRowMapper();
        Project project = rowMapper.mapRow(resultSet, 1);

        // Assertions
        assertAll(
                () -> assertEquals(101, project.getProjectId(), "Project ID mismatch"),
                () -> assertEquals("Test Project", project.getProjectName(), "Project Name mismatch"),
                () -> assertEquals("This is a test project.", project.getProjectDescription(), "Project Description mismatch"),
                () -> assertEquals(1, project.getUserId(), "Owner ID mismatch")
        );
    }
}