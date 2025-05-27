package org.example.calcutask.RepositoryTest;

import org.example.calcutask.Model.Project;
import org.example.calcutask.Repository.ProjectRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ProjectRepositoryTest {

    @Autowired
    private ProjectRepository projectRepository;

    @BeforeEach
    void setUp() {
        Project project = new Project();
        project.setProjectName("Test Project");
        project.setProjectDescription("Test Description");
        project.setUserId(1);
        projectRepository.save(project);
    }

    @Test
    void testAddProject() {
        Project project = new Project();
        project.setProjectName("New Project");
        project.setProjectDescription("New Description");
        project.setUserId(2);

        projectRepository.save(project);

        List<Project> projects = projectRepository.findAll();
        assertEquals(2, projects.size());
    }

    @Test
    void testFindById() {
        Project project = projectRepository.findById(1).orElse(null);
        assertNotNull(project);
        assertEquals("Test Project", project.getProjectName());
    }

    @Test
    void testUpdateProject() {
        Project project = projectRepository.findById(1).orElse(null);
        assertNotNull(project);
        project.setProjectName("Updated Project");
        project.setProjectDescription("Updated Description");

        projectRepository.save(project);

        Project updatedProject = projectRepository.findById(1).orElse(null);
        assertNotNull(updatedProject);
        assertEquals("Updated Project", updatedProject.getProjectName());
        assertEquals("Updated Description", updatedProject.getProjectDescription());
    }

    @Test
    void testDeleteProject() {
        projectRepository.deleteById(1);

        List<Project> projects = projectRepository.findAll();
        assertTrue(projects.isEmpty());
    }

    @Test
    void testFindAllProjects() {
        List<Project> projects = projectRepository.findAll();
        assertEquals(1, projects.size());
    }
}