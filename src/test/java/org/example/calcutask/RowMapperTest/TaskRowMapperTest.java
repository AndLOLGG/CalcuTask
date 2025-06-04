package org.example.calcutask.RowMapperTest;

import org.example.calcutask.Model.Task;
import org.example.calcutask.RowMapper.TaskRowMapper;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TaskRowMapperTest {

    @Test
    void testMapRow() throws SQLException {
        // Mock ResultSet
        ResultSet resultSet = mock(ResultSet.class);
        when(resultSet.getInt("task_id")).thenReturn(301);
        when(resultSet.getString("task_name")).thenReturn("Test Task");
        when(resultSet.getString("task_description")).thenReturn("This is a test task.");
        when(resultSet.getBigDecimal("task_estimated_hours")).thenReturn(new BigDecimal("10.5"));
        when(resultSet.getString("task_status")).thenReturn("COMPLETED");
        when(resultSet.getInt("project_id")).thenReturn(101);

        // Test the RowMapper
        TaskRowMapper rowMapper = new TaskRowMapper();
        Task task = rowMapper.mapRow(resultSet, 1);

        // Assertions
        assertAll(
                () -> assertEquals(301, task.getTaskId(), "Task ID mismatch"),
                () -> assertEquals("Test Task", task.getTaskName(), "Task Name mismatch"),
                () -> assertEquals("This is a test task.", task.getTaskDescription(), "Task Description mismatch"),
                () -> assertEquals(new BigDecimal("10.5"), task.getTaskEstimatedHours(), "Task Estimated Hours mismatch"),
                () -> assertEquals("COMPLETED", task.getTaskStatus(), "Task Status mismatch"),
                () -> assertEquals(101, task.getProjectId(), "Project ID mismatch")
        );
    }
}