package org.example.calcutask.RowMapper;

import org.example.calcutask.Model.Subtask;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SubtaskRowMapper implements RowMapper<Subtask> {
    @Override
    public Subtask mapRow(ResultSet rs, int rowNum) throws SQLException {
        Subtask subtask = new Subtask();
        subtask.setSubtaskId(rs.getInt("task_id"));
        subtask.setSubtaskName(rs.getString("task_name"));
        subtask.setSubtaskDescription(rs.getString("task_description"));
        subtask.setSubtaskEstimatedHours(rs.getBigDecimal("task_estimated_hours"));
        subtask.setSubtaskStatus(rs.getString("task_status"));
        project.setProjectId(rs.getInt("project_id"));
        return subtask;
    }
}