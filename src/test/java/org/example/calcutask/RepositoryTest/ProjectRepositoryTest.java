package org.example.calcutask.RepositoryTest;

import org.example.calcutask.Model.Project;
import org.example.calcutask.Repository.ProjectRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@JdbcTest
@Sql(scripts = "/schema-h2.sql")
class ProjectRepositoryTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private ProjectRepository projectRepository;

    @BeforeEach
    void setUp() {
        projectRepository = new ProjectRepository(jdbcTemplate);

        // Arrange
        Project project = new Project();
        project.setProjectName("Test Project");
        project.setProjectDescription("Test Description");
        project.setUserId(1);

        // Act
        projectRepository.addProject(project);
    }

    @Test
    void testAddProject() {
        // Arrange
        Project project = new Project();
        project.setProjectName("New Project");
        project.setProjectDescription("New Description");
        project.setUserId(2);

        // Act
        projectRepository.addProject(project);
        List<Project> projects = projectRepository.findAllProjects();

        // Assert
        assertEquals(2, projects.size());
    }

    @Test
    void testFindById() {
        // Arrange
        List<Project> projects = projectRepository.findAllProjects();
        int id = projects.get(0).getProjectId();

        // Act
        Project project = projectRepository.findById(id);

        // Assert
        assertNotNull(project);
        assertEquals("Test Project", project.getProjectName());
    }

    @Test
    void testUpdateProject() {
        // Arrange
        List<Project> projects = projectRepository.findAllProjects();
        Project project = projects.get(0);
        project.setProjectName("Updated Project");
        project.setProjectDescription("Updated Description");

        // Act
        projectRepository.update(project);
        Project updated = projectRepository.findById(project.getProjectId());

        // Assert
        assertEquals("Updated Project", updated.getProjectName());
        assertEquals("Updated Description", updated.getProjectDescription());
    }

    @Test
    void testDeleteProject() {
        // Arrange
        List<Project> projects = projectRepository.findAllProjects();
        int id = projects.get(0).getProjectId();

        // Act
        projectRepository.deleteProject(id);
        List<Project> remaining = projectRepository.findAllProjects();

        // Assert
        assertTrue(remaining.isEmpty());
    }

    @Test
    void testFindAllProjects() {
        // Act
        List<Project> projects = projectRepository.findAllProjects();

        // Assert
        assertEquals(1, projects.size());
    }
}
