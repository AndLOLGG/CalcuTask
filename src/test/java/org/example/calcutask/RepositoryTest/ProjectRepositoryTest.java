package org.example.calcutask.RepositoryTest;

import org.example.calcutask.CalcuTaskApplication;
import org.example.calcutask.Model.Project;
import org.example.calcutask.Repository.ProjectRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = CalcuTaskApplication.class)
@Sql(
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
        scripts = {"classpath:schema-h2.sql", "classpath:data-h2.sql"}
)
public class ProjectRepositoryTest {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void testFindById() {
        Project project = projectRepository.findById(1);
        assertNotNull(project);
        assertEquals("Website Redesign", project.getProjectName());
    }

    @Test
    public void testFindAllProjects() {
        List<Project> projects = projectRepository.findAllProjects();
        assertNotNull(projects);
        assertFalse(projects.isEmpty());
        assertTrue(projects.stream().anyMatch(p -> "API Refactoring".equals(p.getProjectName())));
    }

    @Test
    public void testAddProject() {
        Project newProject = new Project();
        newProject.setProjectName("New Project");
        newProject.setProjectDescription("A test project");
        newProject.setUserId(1);

        projectRepository.addProject(newProject);

        // Assuming last inserted ID is 4 (based on initial data)
        List<Project> projects = projectRepository.findAllProjects();
        assertTrue(projects.stream().anyMatch(p -> "New Project".equals(p.getProjectName())));
    }

    @Test
    public void testUpdateProject() {
        Project project = projectRepository.findById(2);
        project.setProjectName("Updated API Refactoring");
        projectRepository.update(project);

        Project updated = projectRepository.findById(2);
        assertEquals("Updated API Refactoring", updated.getProjectName());
    }

    @Test
    public void testDeleteProject() {
        // Delete dependent subtasks first
        jdbcTemplate.update("DELETE FROM SUBTASK WHERE TASK_ID IN (SELECT TASK_ID FROM TASK WHERE PROJECT_ID = ?)", 3);
        // Then delete dependent tasks
        jdbcTemplate.update("DELETE FROM TASK WHERE PROJECT_ID = ?", 3);
        // Then delete project access entries
        jdbcTemplate.update("DELETE FROM USER_PROJECT_ACCESS WHERE PROJECT_ID = ?", 3);

        // Now delete the project itself
        projectRepository.deleteProject(3);

        List<Project> projects = projectRepository.findAllProjects();
        assertFalse(projects.stream().anyMatch(p -> p.getProjectId() == 3));
    }

}
