package org.example.calcutask.RowMapperTest;

import org.example.calcutask.Model.Subtask;
import org.example.calcutask.RowMapper.SubtaskRowMapper;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SubtaskRowMapperTest {

    @Test
    void testMapRow() throws SQLException {
        // Mock ResultSet
        ResultSet resultSet = mock(ResultSet.class);
        when(resultSet.getInt("subtask_id")).thenReturn(201);
        when(resultSet.getString("subtask_name")).thenReturn("Test Subtask");
        when(resultSet.getString("subtask_description")).thenReturn("This is a test subtask.");
        when(resultSet.getInt("subtask_estimated_hours")).thenReturn(5);
        when(resultSet.getString("subtask_status")).thenReturn("IN_PROGRESS");
        when(resultSet.getInt("task_id")).thenReturn(101);
        when(resultSet.getObject("assigned_user_id", Integer.class)).thenReturn(1);

        // Test the RowMapper
        SubtaskRowMapper rowMapper = new SubtaskRowMapper();
        Subtask subtask = rowMapper.mapRow(resultSet, 1);

        // Assertions
        assertAll(
                () -> assertEquals(201, subtask.getSubtaskId(), "Subtask ID mismatch"),
                () -> assertEquals("Test Subtask", subtask.getSubtaskName(), "Subtask Name mismatch"),
                () -> assertEquals("This is a test subtask.", subtask.getSubtaskDescription(), "Subtask Description mismatch"),
                () -> assertEquals(5, subtask.getSubtaskEstimatedHours(), "Subtask Estimated Hours mismatch"),
                () -> assertEquals("IN_PROGRESS", subtask.getSubtaskStatus(), "Subtask Status mismatch"),
                () -> assertEquals(101, subtask.getTaskId(), "Task ID mismatch"),
                () -> assertEquals(1, subtask.getAssignedUserId(), "Assigned User ID mismatch")
        );
    }
}