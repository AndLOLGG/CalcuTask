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

    // Henter subtasks + tilknyttet brugernavn (hvis påtaget)
    public List<Subtask> findByTaskId(int taskId) {
        String sql = """
            SELECT s.*, u.username
            FROM subtask s
            LEFT JOIN user u ON s.assigned_user_id = u.user_id
            WHERE s.task_id = ?
        """;
        return template.query(sql, new SubtaskRowMapper(), taskId);
    }

    public Subtask findById(int subtaskId) {
        String sql = "SELECT * FROM subtask WHERE subtask_id = ?";
        return template.queryForObject(sql, new SubtaskRowMapper(), subtaskId);
    }

    public void save(Subtask subtask) {
        String sql = "INSERT INTO subtask (subtask_name, subtask_description, subtask_estimated_hours, subtask_status, task_id) VALUES (?, ?, ?, ?, ?)";
        template.update(sql,
                subtask.getSubtaskName(),
                subtask.getSubtaskDescription(),
                subtask.getSubtaskEstimatedHours(),
                subtask.getSubtaskStatus(),
                subtask.getTaskId()
        );
        updateTaskEstimatedHours(subtask.getTaskId());
    }

    public void update(Subtask subtask) {
        String sql = "UPDATE subtask SET subtask_name = ?, subtask_description = ?, subtask_estimated_hours = ? WHERE subtask_id = ?";
        template.update(sql,
                subtask.getSubtaskName(),
                subtask.getSubtaskDescription(),
                subtask.getSubtaskEstimatedHours(),
                subtask.getSubtaskId()
        );
        updateTaskEstimatedHours(subtask.getTaskId());
    }

    public void deleteById(int subtaskId) {
        // Find taskId først, så vi kan opdatere tasken bagefter
        String getTaskIdSql = "SELECT task_id FROM subtask WHERE subtask_id = ?";
        Integer taskId = template.queryForObject(getTaskIdSql, Integer.class, subtaskId);

        String sql = "DELETE FROM subtask WHERE subtask_id = ?";
        template.update(sql, subtaskId);

        if (taskId != null) {
            updateTaskEstimatedHours(taskId);
        }
    }

    public void statusAndAssignSubtaskToUser(int subtaskId, int userId, String subtaskStatus) {
        String sql = "UPDATE subtask SET assigned_user_id = ?, subtask_status = ? WHERE subtask_id = ?";
        template.update(sql, userId, subtaskStatus, subtaskId);
    }

    public void releaseSubtaskFromUser(int subtaskId, String status) {
        String sql = "UPDATE subtask SET assigned_user_id = NULL, subtask_status = ? WHERE subtask_id = ?";
        template.update(sql, status, subtaskId);
    }

    public void updateStatus(int subtaskId, String status) {
        String sql = "UPDATE subtask SET subtask_status = ? WHERE subtask_id = ?";
        template.update(sql, status, subtaskId);
    }

    // 🔁 Summerer estimated_hours for alle subtasks og opdaterer task
    private void updateTaskEstimatedHours(int taskId) {
        String sql = """
            UPDATE task
            SET task_estimated_hours = (
                SELECT IFNULL(SUM(subtask_estimated_hours), 0)
                FROM subtask
                WHERE task_id = ?
            )
            WHERE task_id = ?
        """;
        template.update(sql, taskId, taskId);
    }
}
