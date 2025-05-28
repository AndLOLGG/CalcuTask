//package org.example.calcutask.ControllerTest;
//
//import org.example.calcutask.Controller.SubtaskController;
//import org.example.calcutask.Model.Status;
//import org.example.calcutask.Model.Subtask;
//import org.example.calcutask.Model.Task;
//import org.example.calcutask.Service.SubtaskService;
//import org.example.calcutask.Service.TaskService;
//import org.example.calcutask.Service.UserProjectAccessService;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.util.Arrays;
//
//import static org.mockito.ArgumentMatchers.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//@WebMvcTest(SubtaskController.class)
//class SubtaskControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private SubtaskService subtaskService;
//
//    @MockBean
//    private TaskService taskService;
//
//    @MockBean
//    private UserProjectAccessService userProjectAccessService;
//
//    // Test for displaying the subtask overview page
//    @Test
//    void testShowProjectOverview() throws Exception {
//        // Mock a subtask to be returned by the service
//        Subtask subtask = new Subtask();
//        subtask.setSubtaskId(1);
//        subtask.setSubtaskName("Test Subtask");
//
//        // Mock the service to return a list of subtasks
//        Mockito.when(subtaskService.getSubtasksByTaskId(anyInt()))
//                .thenReturn(Arrays.asList(subtask));
//
//        // Perform a GET request and verify the response
//        mockMvc.perform(get("/subtask/overview").param("taskId", "1"))
//                .andExpect(status().isOk()) // Check for HTTP 200
//                .andExpect(model().attributeExists("subTasks")) // Verify model attribute
//                .andExpect(view().name("subtask-overview")); // Verify view name
//    }
//
//    // Test for displaying the create subtask form
//    @Test
//    void testCreateTaskGet() throws Exception {
//        // Perform a GET request to the create subtask endpoint
//        mockMvc.perform(get("/subtask/create").param("taskId", "1"))
//                .andExpect(status().isOk()) // Check for HTTP 200
//                .andExpect(model().attributeExists("subtask")) // Verify model attribute
//                .andExpect(view().name("create-subtask")); // Verify view name
//    }
//
//    // Test for creating a subtask via POST request
//    @Test
//    void testCreateTaskPost() throws Exception {
//        // Mock a task and user access
//        Task task = new Task();
//        task.setProjectId(1);
//
//        Mockito.when(taskService.findById(anyInt())).thenReturn(task);
//        Mockito.when(userProjectAccessService.hasUserAccessToProject(anyInt(), anyInt())).thenReturn(true);
//
//        // Perform a POST request to create a subtask
//        mockMvc.perform(post("/subtask/create")
//                        .sessionAttr("userId", 1) // Mock session attribute
//                        .param("subtaskName", "Test Subtask") // Mock form data
//                        .param("taskId", "1"))
//                .andExpect(status().is3xxRedirection()) // Check for redirection
//                .andExpect(redirectedUrl("/subtask/overview?taskId=1")); // Verify redirect URL
//    }
//
//    // Test for displaying the edit subtask form
//    @Test
//    void testEditSubtaskGet() throws Exception {
//        // Mock a task, subtask, and user access
//        Task task = new Task();
//        task.setProjectId(1);
//
//        Subtask subtask = new Subtask();
//        subtask.setSubtaskId(1);
//
//        Mockito.when(taskService.findById(anyInt())).thenReturn(task);
//        Mockito.when(userProjectAccessService.hasUserAccessToProject(anyInt(), anyInt())).thenReturn(true);
//        Mockito.when(subtaskService.getSubtaskById(anyInt())).thenReturn(subtask);
//
//        // Perform a GET request to the edit subtask endpoint
//        mockMvc.perform(get("/subtask/edit").param("taskId", "1").param("subtaskId", "1").sessionAttr("userId", 1))
//                .andExpect(status().isOk()) // Check for HTTP 200
//                .andExpect(model().attributeExists("subtask")) // Verify model attribute
//                .andExpect(view().name("edit-subtask")); // Verify view name
//    }
//
//    // Test for deleting a subtask
//    @Test
//    void testDeleteSubtask() throws Exception {
//        // Perform a POST request to delete a subtask
//        mockMvc.perform(post("/subtask/delete").param("subtaskId", "1").param("taskId", "1"))
//                .andExpect(status().is3xxRedirection()) // Check for redirection
//                .andExpect(redirectedUrl("/subtask/overview?taskId=1")); // Verify redirect URL
//    }
//
//    // Test for assigning a subtask to a user
//    @Test
//    void testAssignSubtask() throws Exception {
//        // Perform a POST request to assign a subtask
//        mockMvc.perform(post("/subtask/statusAndAssign")
//                        .param("subtaskId", "1") // Mock subtask ID
//                        .param("taskId", "1") // Mock task ID
//                        .sessionAttr("userId", 1)) // Mock session attribute
//                .andExpect(status().is3xxRedirection()) // Check for redirection
//                .andExpect(redirectedUrl("/subtask/overview?taskId=1")); // Verify redirect URL
//    }
//
//    // Test for releasing a subtask from a user
//    @Test
//    void testReleaseSubtask() throws Exception {
//        // Perform a POST request to release a subtask
//        mockMvc.perform(post("/subtask/release").param("subtaskId", "1").param("taskId", "1"))
//                .andExpect(status().is3xxRedirection()) // Check for redirection
//                .andExpect(redirectedUrl("/subtask/overview?taskId=1")); // Verify redirect URL
//    }
//
//    // Test for updating the status of a subtask
//    @Test
//    void testUpdateTaskStatus() throws Exception {
//        // Perform a POST request to update the status of a subtask
//        mockMvc.perform(post("/subtask/updateTaskStatus")
//                        .param("subtaskId", "1") // Mock subtask ID
//                        .param("taskId", "1") // Mock task ID
//                        .param("status", Status.Done.name()) // Mock status
//                        .sessionAttr("userId", 1)) // Mock session attribute
//                .andExpect(status().is3xxRedirection()) // Check for redirection
//                .andExpect(redirectedUrl("/subtask/overview?taskId=1")); // Verify redirect URL
//    }
//}