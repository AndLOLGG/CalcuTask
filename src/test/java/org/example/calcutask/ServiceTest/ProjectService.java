package org.example.calcutask.ServiceTest;

import org.example.calcutask.Model.Project;
import org.example.calcutask.Model.Task;
import org.example.calcutask.Model.Subtask;
import org.example.calcutask.Repository.ProjectRepository;
import org.example.calcutask.Repository.TaskRepository;
import org.example.calcutask.Repository.SubtaskRepository;
import org.example.calcutask.RowMapper.ProjectRowMapper;
import org.example.calcutask.Service.ProjectService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProjectServiceTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @Mock
    private ProjectRepository projectRepository;

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private SubtaskRepository subtaskRepository;

    @InjectMocks
    private ProjectService projectService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddProject() {
        Project project = new Project();
        doNothing().when(projectRepository).addProject(project);

        projectService.addProject(project);

        verify(projectRepository, times(1)).addProject(project);
    }

    @Test
    void testGetProjectsByUserId() {
        int userId = 1;
        List<Project> projects = Arrays.asList(new Project(), new Project());
        String sql = "SELECT project.* FROM project JOIN user_project_access ON project.project_id = user_project_access.project_id WHERE user_project_access.user_id = ?";
        when(jdbcTemplate.query(eq(sql), any(Object[].class), any(ProjectRowMapper.class))).thenReturn(projects);

        List<Project> result = projectService.getProjectsByUserId(userId);

        assertEquals(projects, result);
        verify(jdbcTemplate, times(1)).query(eq(sql), any(Object[].class), any(ProjectRowMapper.class));
    }

    @Test
    void testDeleteProject() {
        int projectId = 1;
        when(jdbcTemplate.update(anyString(), eq(projectId))).thenReturn(1); // Mock the update method to return 1 (rows affected)
        doNothing().when(projectRepository).deleteProject(projectId);

        projectService.deleteProject(projectId);

        verify(jdbcTemplate, times(3)).update(anyString(), eq(projectId));
        verify(projectRepository, times(1)).deleteProject(projectId);
    }

    @Test
    void testGetProjectById() {
        int projectId = 1;
        Project project = new Project();
        when(projectRepository.findById(projectId)).thenReturn(project);

        Project result = projectService.getProjectById(projectId);

        assertEquals(project, result);
        verify(projectRepository, times(1)).findById(projectId);
    }

    @Test
    void testGetFullProjectWithTasks() {
        int projectId = 1;
        Project project = new Project();
        Task task = new Task();
        Subtask subtask = new Subtask();
        task.setSubtasks(Collections.singletonList(subtask));
        when(projectRepository.findById(projectId)).thenReturn(project);
        when(taskRepository.findByProjectId(projectId)).thenReturn(Collections.singletonList(task));
        when(subtaskRepository.findByTaskId(task.getTaskId())).thenReturn(Collections.singletonList(subtask));

        Project result = projectService.getFullProjectWithTasks(projectId);

        assertEquals(project, result);
        assertEquals(1, result.getTasks().size());
        assertEquals(1, result.getTasks().get(0).getSubtasks().size());
        verify(projectRepository, times(1)).findById(projectId);
        verify(taskRepository, times(1)).findByProjectId(projectId);
        verify(subtaskRepository, times(1)).findByTaskId(task.getTaskId());
    }

    @Test
    void testGrantAccessToProject() {
        int userId = 1;
        String sql = "INSERT INTO user_project_access (user_id, project_id, access_type) VALUES (?, LAST_INSERT_ID(), 'EDIT')";
        when(jdbcTemplate.update(sql, userId)).thenReturn(1); // Mock the update method to return 1 (rows affected)

        projectService.grantAccessToProject(userId);

        verify(jdbcTemplate, times(1)).update(sql, userId);
    }

    @Test
    void testGetUserAccessType() {
        int userId = 1;
        int projectId = 1;
        String sql = "SELECT access_type FROM user_project_access WHERE user_id = ? AND project_id = ?";
        when(jdbcTemplate.queryForObject(sql, String.class, userId, projectId)).thenReturn("EDIT");

        String result = projectService.getUserAccessType(userId, projectId);

        assertEquals("EDIT", result);
        verify(jdbcTemplate, times(1)).queryForObject(sql, String.class, userId, projectId);
    }
}