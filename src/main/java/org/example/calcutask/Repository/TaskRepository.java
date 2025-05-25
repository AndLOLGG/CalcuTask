package org.example.calcutask.Repository;

import org.example.calcutask.Model.Subtask;
import org.example.calcutask.Model.Task;
import org.example.calcutask.RowMapper.SubtaskRowMapper;
import org.example.calcutask.RowMapper.TaskRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
public class TaskRepository {


    private final JdbcTemplate template;

    public TaskRepository(JdbcTemplate jdbcTemplate) {
        this.template = jdbcTemplate;
    }

    public void save(Task task) {
        String sql = "INSERT INTO task (task_name, task_description, task_estimated_hours, project_id) VALUES (?, ?, ?, ?)";
        template.update(sql, task.getTaskName(), task.getTaskDescription(), task.getTaskEstimatedHours(), task.getProjectId());
    }

    public List<Task> findByProjectId(int projectId) {
        String sql = "SELECT * FROM task WHERE project_id = ?";
        return template.query(sql, new TaskRowMapper(), projectId);
    }

    public List<Task> findByProjectIds(List<Integer> projectIds) {
        if (projectIds == null || projectIds.isEmpty()) {
            return new ArrayList<>();
        }

        String placeholders = String.join(",", Collections.nCopies(projectIds.size(), "?"));

        String sql = "SELECT * FROM task WHERE project_id IN (" + placeholders + ")";
        return template.query(sql, projectIds.toArray(), new TaskRowMapper());
    }

    public Task findByIdWithSubtask(int taskId) {
        String taskSql = "SELECT * FROM task WHERE task_id = ?";
        Task task = template.queryForObject(taskSql, new TaskRowMapper(), taskId);

        String subtaskSql = "SELECT * FROM subtask WHERE task_id = ?";
        List<Subtask> subtasks = template.query(subtaskSql, new SubtaskRowMapper(), taskId);

        task.setSubtasks(subtasks);
        return task;
    }

    public Task findById(int taskId) {
        String taskSql = "SELECT * FROM task WHERE task_id = ?";
        return template.queryForObject(taskSql, new TaskRowMapper(), taskId);
    }

    public void update(Task task) {
        String sql = "UPDATE task SET task_name = ?, task_description = ?, task_estimated_hours = ?, project_id = ? WHERE task_id = ?";
        template.update(sql, task.getTaskName(), task.getTaskDescription(), task.getTaskEstimatedHours(), task.getProjectId(), task.getTaskId());
    }

    public void deleteById(int taskId) {
        String sql = "DELETE FROM task WHERE task_id = ?";
        template.update(sql, taskId);
    }
}
