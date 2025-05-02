package org.example.calcutask.RowMapper;

import org.example.calcutask.Model.Project;
import org.example.calcutask.Model.Task;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TaskRowMapper implements RowMapper<Task> {
    @Override
    public Task mapRow(ResultSet rs, int rowNum) throws SQLException {
        Task task = new Task();
        task.setTaskId(rs.getInt("task_id"));
        task.setTaskName(rs.getString("task_name"));
        task.setTaskDescription(rs.getString("task_description"));
        task.setTaskEstimatedHours(rs.getBigDecimal("task_estimated_hours"));
        task.setTaskStatus(rs.getString("task_status"));
        task.setProjectId(rs.getInt("project_id"));
        return task;
    }
}