package org.example.calcutask.Repository;

import org.example.calcutask.Model.Subtask;
import org.example.calcutask.RowMapper.SubtaskRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SubtaskRepository {

    @Autowired
    private JdbcTemplate template;

    public List<Subtask> findByTaskId(int taskId) {
        String sql = "SELECT * FROM subtask WHERE task_id = ?";
        return template.query(sql, new SubtaskRowMapper(), taskId);
    }

    public Subtask findById(int subtaskId) {
        String sql = "SELECT * FROM subtask WHERE subtask_id = ?";
        return template.queryForObject(sql, new SubtaskRowMapper(), subtaskId);
    }

    public void save(Subtask subtask) {
        String sql = "INSERT INTO subtask (subtask_name, subtask_description, subtask_estimated_hours, subtask_status, task_id) VALUES (?, ?, ?, ?, ?)";
        template.update(sql, subtask.getSubtaskName(), subtask.getSubtaskDescription(), subtask.getSubtaskEstimatedHours(), subtask.getSubtaskStatus(), subtask.getTaskId());
    }

    public void update(Subtask subtask) {
        String sql = "UPDATE subtask SET subtask_name = ?, subtask_description = ?, subtask_estimated_hours = ?, subtask_status = ?, task_id = ? WHERE subtask_id = ?";
        template.update(sql, subtask.getSubtaskName(), subtask.getSubtaskDescription(), subtask.getSubtaskEstimatedHours(), subtask.getSubtaskStatus(), subtask.getTaskId(), subtask.getSubtaskId());
    }

    public void deleteById(int subtaskId) {
        String sql = "DELETE FROM subtask WHERE subtask_id = ?";
        template.update(sql, subtaskId);
    }
}
