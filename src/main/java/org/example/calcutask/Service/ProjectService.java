package org.example.calcutask.Service;

import org.example.calcutask.Model.Project;
import org.example.calcutask.Model.Task;
import org.example.calcutask.Model.Subtask;
import org.example.calcutask.Repository.ProjectRepository;
import org.example.calcutask.Repository.TaskRepository;
import org.example.calcutask.Repository.SubtaskRepository;
import org.example.calcutask.RowMapper.ProjectRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private SubtaskRepository subtaskRepository;

    public void addProject(Project project) {
        projectRepository.addProject(project);
        System.out.println("project saved successfully!");
    }

    public List<Project> getProjectsByUserId(int userId) {
        String sql = "SELECT * FROM project WHERE owner_id = ?";
        return jdbcTemplate.query(sql, new Object[]{userId}, new ProjectRowMapper());
    }

    public void deleteProject(int projectId) {
        jdbcTemplate.update("DELETE FROM subtask WHERE task_id IN (SELECT task_id FROM task WHERE project_id = ?)", projectId);
        jdbcTemplate.update("DELETE FROM task WHERE project_id = ?", projectId);
        projectRepository.deleteProject(projectId);
    }

    public Project getProjectById(int projectId) {
        return projectRepository.findById(projectId);
    }

    public Project getFullProjectWithTasks(int projectId) {
        Project project = projectRepository.findById(projectId);
        List<Task> tasks = taskRepository.findByProjectId(projectId);
        for (Task task : tasks) {
            List<Subtask> subtasks = subtaskRepository.findByTaskId(task.getTaskId());
            task.setSubtasks(subtasks);
        }
        project.setTasks(tasks);
        return project;
    }

    public void updateProjectAndTasks(Project project) {
        projectRepository.update(project);
        if (project.getTasks() != null) {
            for (Task task : project.getTasks()) {
                taskRepository.update(task);
                if (task.getSubtasks() != null) {
                    for (Subtask subtask : task.getSubtasks()) {
                        subtaskRepository.update(subtask);
                    }
                }
            }
        }
    }
}
