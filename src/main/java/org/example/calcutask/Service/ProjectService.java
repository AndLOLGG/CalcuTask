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
        String sql = "SELECT project.* FROM project JOIN user_project_access ON project.project_id = user_project_access.project_id WHERE user_project_access.user_id = ?";
        return jdbcTemplate.query(sql, new Object[]{userId}, new ProjectRowMapper());
    }

    public void deleteProject(int projectId) {
        // Fjern relationer i koblingstabel
        jdbcTemplate.update("DELETE FROM user_project_access WHERE project_id = ?", projectId);

        // Fjern subtasks
        jdbcTemplate.update("DELETE FROM subtask WHERE task_id IN (SELECT task_id FROM task WHERE project_id = ?)", projectId);

        // Fjern tasks
        jdbcTemplate.update("DELETE FROM task WHERE project_id = ?", projectId);

        // Fjern projektet
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
    public void grantAccessToProject(int userId) {
        String sql = "INSERT INTO user_project_access (user_id, project_id, access_type) VALUES (?, LAST_INSERT_ID(), 'EDIT')";
        jdbcTemplate.update(sql, userId);
    }
    public String getUserAccessType(int userId, int projectId) {
        String sql = "SELECT access_type FROM user_project_access WHERE user_id = ? AND project_id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, String.class, userId, projectId);
        } catch (Exception e) {
            return "NONE"; // fallback hvis der ikke findes adgang
        }
    }


}
