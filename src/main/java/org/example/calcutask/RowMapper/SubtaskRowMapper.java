package org.example.calcutask.RowMapper;

import org.example.calcutask.Model.Subtask;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SubtaskRowMapper implements RowMapper<Subtask> {
    @Override
    public Subtask mapRow(ResultSet rs, int rowNum) throws SQLException {
        Subtask subtask = new Subtask();
        subtask.setSubtaskId(rs.getInt("subtask_id"));
        subtask.setSubtaskName(rs.getString("subtask_name"));
        subtask.setSubtaskDescription(rs.getString("subtask_description"));
        subtask.setSubtaskEstimatedHours(rs.getInt("subtask_estimated_hours"));
        subtask.setSubtaskStatus(rs.getString("subtask_status"));
        subtask.setTaskId(rs.getInt("task_id"));
        subtask.setAssignedUserId(rs.getObject("assigned_user_id", Integer.class));
        subtask.setAssignedUsername(rs.getString("username"));
        return subtask;
    }
}
