package org.example.calcutask.ControllerTest;

import org.example.calcutask.Controller.ProjectController;
import org.example.calcutask.Model.Project;
import org.example.calcutask.Service.ProjectService;
import org.example.calcutask.Service.TaskService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProjectController.class)
class ProjectControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProjectService projectService;

    @MockBean
    private TaskService taskService;

    // Test for retrieving all projects when a session exists
    @Test
    void testGetAllProjects() throws Exception {
        // Mock a project to be returned by the service
        Project project = new Project();
        project.setProjectId(1);
        project.setProjectName("Test Project");

        // Mock the service to return a list of projects
        Mockito.when(projectService.getProjectsByUserId(anyInt()))
                .thenReturn(Arrays.asList(project));

        // Perform a GET request and verify the response
        mockMvc.perform(get("/project").sessionAttr("userId", 1)) // Mock session attribute
                .andExpect(status().isOk()) // Check for HTTP 200
                .andExpect(model().attributeExists("projects")) // Verify model attribute
                .andExpect(view().name("project-list")); // Verify view name
    }

    // Test for redirecting to login if no session exists
    @Test
    void testGetAllProjectsRedirectsToLoginIfNoSession() throws Exception {
        // Perform a GET request without a session
        mockMvc.perform(get("/project"))
                .andExpect(status().is3xxRedirection()) // Check for redirection
                .andExpect(redirectedUrl("/login")); // Verify redirect URL
    }
}