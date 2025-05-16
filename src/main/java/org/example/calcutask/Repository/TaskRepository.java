package org.example.calcutask.Repository;

import org.example.calcutask.Model.Subtask;
import org.example.calcutask.RowMapper.SubtaskRowMapper;
import org.example.calcutask.RowMapper.TaskRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.example.calcutask.Model.Task;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class TaskRepository {
    @Autowired
    private JdbcTemplate template;

    public void save(Task task) {
        String sql = "INSERT INTO task (task_name, task_description, task_estimated_hours, project_id) VALUES (?, ?, ?, ?)";
        template.update(sql, task.getTaskName(), task.getTaskDescription(), task.getTaskEstimatedHours(), task.getProjectId());
    }

    public List<Task> findByProjectId(int projectId) {
        String sql = "SELECT * FROM task WHERE project_id = ?";
        return template.query(sql, new TaskRowMapper(), projectId);
    }

    public Task findByIdWithSubtask(int taskId) {
        // Fetch the task
        String taskSql = "SELECT * FROM task WHERE task_id = ?";
        Task task = template.queryForObject(taskSql, new TaskRowMapper(), taskId);

        // Fetch associated subtasks
        String subtaskSql = "SELECT * FROM subtask WHERE task_id = ?";
        List<Subtask> subtasks = template.query(subtaskSql, new SubtaskRowMapper(), taskId);

        // Set subtasks in the task
        task.setSubtasks(subtasks);
        return task;
    }

    public void deleteById(int id) {
        String sql = "DELETE FROM task WHERE task_id = ?";
        int rowsAffected = template.update(sql, id);
        if (rowsAffected == 0) {
            throw new IllegalArgumentException("No task found with id: " + id);
        }
    }

    public void update(Task task) {
        String sql = "UPDATE task SET task_name = ?, task_description = ?, task_estimated_hours = ?, project_id = ? WHERE task_id = ?";
        template.update(sql, task.getTaskName(), task.getTaskDescription(), task.getTaskEstimatedHours(), task.getProjectId(), task.getTaskId());
    }
}