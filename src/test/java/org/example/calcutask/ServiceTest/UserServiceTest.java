package org.example.calcutask.ServiceTest;

import org.example.calcutask.Model.Task;
import org.example.calcutask.Model.User;
import org.example.calcutask.Repository.TaskRepository;
import org.example.calcutask.Repository.UserRepository;
import org.example.calcutask.Service.TaskService;
import org.example.calcutask.Service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateUser() {
        User user = new User();
        userService.createUser(user);
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void testFindByUsername() {
        String username = "testUser";
        User user = new User();
        when(userRepository.findByUsername(username)).thenReturn(user);

        User result = userService.findByUsername(username);

        assertEquals(user, result);
        verify(userRepository, times(1)).findByUsername(username);
    }

    @Test
    void testFindById() {
        int id = 1;
        User user = new User();
        when(userRepository.findById(id)).thenReturn(user);

        User result = userService.findById(id);

        assertEquals(user, result);
        verify(userRepository, times(1)).findById(id);
    }

    @Test
    void testAuthenticateAndGetUser_ValidCredentials() {
        String username = "testUser";
        String password = "password";
        User user = new User();
        user.setPassword(password);
        when(userRepository.findByUsername(username)).thenReturn(user);

        User result = userService.authenticateAndGetUser(username, password);

        assertEquals(user, result);
        verify(userRepository, times(1)).findByUsername(username);
    }

    @Test
    void testAuthenticateAndGetUser_InvalidCredentials() {
        String username = "testUser";
        String password = "wrongPassword";
        User user = new User();
        user.setPassword("password");
        when(userRepository.findByUsername(username)).thenReturn(user);

        User result = userService.authenticateAndGetUser(username, password);

        assertNull(result);
        verify(userRepository, times(1)).findByUsername(username);
    }

    @Test
    void testGetAllUsers() {
        List<User> users = Arrays.asList(new User(), new User());
        when(userRepository.findAll()).thenReturn(users);

        List<User> result = userService.getAllUsers();

        assertEquals(users, result);
        verify(userRepository, times(1)).findAll();
    }

    @Test
    void testUpdateUser() {
        User user = new User();
        userService.updateUser(user);
        verify(userRepository, times(1)).update(user);
    }

    static class TaskServiceTest {

        @Mock
        private TaskRepository taskRepository;

        @InjectMocks
        private TaskService taskService;

        @BeforeEach
        void setUp() {
            MockitoAnnotations.openMocks(this);
        }

        @Test
        void testCreateTask() {
            Task task = new Task();
            doNothing().when(taskRepository).save(task);

            taskService.createTask(task);

            verify(taskRepository, times(1)).save(task);
        }

        @Test
        void testFindById() {
            int taskId = 1;
            Task task = new Task();
            when(taskRepository.findById(taskId)).thenReturn(task);

            Task result = taskService.findById(taskId);

            assertEquals(task, result);
            verify(taskRepository, times(1)).findById(taskId);
        }

        @Test
        void testGetTasksByProjectId() {
            int projectId = 1;
            Task task = new Task();
            when(taskRepository.findByProjectId(projectId)).thenReturn(Collections.singletonList(task));

            List<Task> result = taskService.getTasksByProjectId(projectId);

            assertEquals(1, result.size());
            assertEquals(task, result.get(0));
            verify(taskRepository, times(1)).findByProjectId(projectId);
        }

        @Test
        void testGetTasksByProjectIds() {
            List<Integer> projectIds = List.of(1, 2);
            Task task = new Task();
            when(taskRepository.findByProjectIds(projectIds)).thenReturn(Collections.singletonList(task));

            List<Task> result = taskService.getTasksByProjectIds(projectIds);

            assertEquals(1, result.size());
            assertEquals(task, result.get(0));
            verify(taskRepository, times(1)).findByProjectIds(projectIds);
        }

        @Test
        void testGetTaskWithSubtasks() {
            int taskId = 1;
            Task task = new Task();
            when(taskRepository.findByIdWithSubtask(taskId)).thenReturn(task);

            Task result = taskService.getTaskWithSubtasks(taskId);

            assertEquals(task, result);
            verify(taskRepository, times(1)).findByIdWithSubtask(taskId);
        }

        @Test
        void testDeleteTask() {
            int taskId = 1;
            doNothing().when(taskRepository).deleteById(taskId);

            taskService.deleteTask(taskId);

            verify(taskRepository, times(1)).deleteById(taskId);
        }

        @Test
        void testUpdateTask() {
            Task task = new Task();
            doNothing().when(taskRepository).update(task);

            taskService.updateTask(task);

            verify(taskRepository, times(1)).update(task);
        }
    }
}