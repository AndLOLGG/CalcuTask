package org.example.calcutask.Repository;

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
        String sql = "INSERT INTO tasks (name, description, project_id, parent_task_id) VALUES (?, ?, ?, ?)";
        template.update(sql, task.getTaskName(), task.getTaskDescription(), task.getProjectId() /** , task.getParentTaskId()**/);
    }

    public List<Task> findByProjectId(int projectId) {
        String sql = "SELECT * FROM tasks WHERE project_id = ?";
        return template.query(sql, new TaskRowMapper(), projectId);
    }

    public Task findByIdWithSubtasks(int taskId) {
        // Fetch the task
        String taskSql = "SELECT * FROM tasks WHERE task_id = ?";
        Task task = template.queryForObject(taskSql, new TaskRowMapper(), taskId);

        // Fetch associated subtasks
        String subtaskSql = "SELECT * FROM subtasks WHERE task_id = ?";
        List<Subtask> subtasks = template.query(subtaskSql, new SubtaskRowMapper(), taskId);

        // Set subtasks in the task
        task.setSubtasks(subtasks);
        return task;
    }

    public void deleteById(int id) {
        String sql = "DELETE FROM tasks WHERE task_id = ?";
        template.update(sql, id);
    }

    public void update(Task task) {
        String sql = "UPDATE tasks SET name = ?, description = ?, project_id = ? WHERE task_id = ?";
        template.update(sql, task.getTaskName(), task.getTaskDescription(), task.getProjectId(), task.getTaskId());
    }
}

