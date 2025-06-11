package org.example.calcutask.RepositoryTest;

import org.example.calcutask.CalcuTaskApplication;
import org.example.calcutask.Model.Project;
import org.example.calcutask.Repository.ProjectRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = CalcuTaskApplication.class)
@Sql(
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
        scripts = {"classpath:schema-h2.sql", "classpath:data-h2.sql"}
)
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
        assertEquals(5, projects.size());
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
        assertEquals("Website Redesign", project.getProjectName()); // Matcher din SQL seed-data
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
        int idToDelete = projects.get(0).getProjectId();

        // Clean up dependencies before deleting the project
        jdbcTemplate.update("DELETE FROM SUBTASK WHERE TASK_ID IN (SELECT TASK_ID FROM TASK WHERE PROJECT_ID = ?)", idToDelete);
        jdbcTemplate.update("DELETE FROM TASK WHERE PROJECT_ID = ?", idToDelete);
        jdbcTemplate.update("DELETE FROM USER_PROJECT_ACCESS WHERE PROJECT_ID = ?", idToDelete);

        // Act
        projectRepository.deleteProject(idToDelete);
        List<Project> remaining = projectRepository.findAllProjects();

        // Assert
        assertEquals(3, remaining.size(), "After deletion there should be 3 projects left");
        assertFalse(remaining.stream().anyMatch(p -> p.getProjectId() == idToDelete), "Deleted project should be gone");
    }

    @Test
    void testFindAllProjects() {
        // Act
        List<Project> projects = projectRepository.findAllProjects();

        // Assert
        assertEquals(4, projects.size()); // 3 fra SQL + 1 fra @BeforeEach
    }
}
