package org.example.calcutask.ControllerTest;

import org.example.calcutask.Controller.TaskController;
import org.example.calcutask.Model.Task;
import org.example.calcutask.Service.TaskService;
import org.example.calcutask.Service.UserProjectAccessService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class TaskControllerTest {

    private MockMvc mockMvc;
    private TaskService taskService;
    private UserProjectAccessService userProjectAccessService;

    @BeforeEach
    void setup() {
        taskService = Mockito.mock(TaskService.class);
        userProjectAccessService = Mockito.mock(UserProjectAccessService.class);

        TaskController taskController = new TaskController(taskService, userProjectAccessService);
        mockMvc = MockMvcBuilders.standaloneSetup(taskController).build();
    }

    @Test
    void testCreateTask() throws Exception {
        // Mock the session and set the userId attribute
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("userId", 1); // Set a valid userId

        Mockito.when(userProjectAccessService.hasUserAccessToProject(anyInt(), anyInt())).thenReturn(true);
        Mockito.doNothing().when(taskService).createTask(any(Task.class));

        mockMvc.perform(post("/task/create")
                        .param("taskName", "Test Task")
                        .param("taskDescription", "Test Description")
                        .param("projectId", "1")
                        .session(session)) // Attach the mocked session
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/project"));
    }

    @Test
    void testDeleteTask() throws Exception {
        Mockito.doNothing().when(taskService).deleteTask(anyInt());

        mockMvc.perform(post("/task/delete")
                        .param("taskId", "1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/project"));
    }

    @Test
    void testUpdateTask() throws Exception {
        Mockito.doNothing().when(taskService).updateTask(any(Task.class));

        mockMvc.perform(post("/task/edit")
                        .param("taskId", "1")
                        .param("taskName", "Updated Task")
                        .param("taskDescription", "Updated Description"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/project"));
    }
}