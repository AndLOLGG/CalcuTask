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
        template.update(sql, task.getTaskName(), task.getTaskDescription(), task.getProjectId(), task.getParentTaskId());
    }

    public List<Task> findByProjectId(int projectId) {
        String sql = "SELECT * FROM tasks WHERE project_id = ?";
        return template.query(sql, new TaskRowMapper(), projectId);
    }

    public void deleteById(int id) {
    }

    public void update(Task task) {
    }
}

