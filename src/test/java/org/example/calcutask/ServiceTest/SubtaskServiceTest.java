package org.example.calcutask.ServiceTest;

import org.example.calcutask.Model.Status;
import org.example.calcutask.Model.Subtask;
import org.example.calcutask.Repository.SubtaskRepository;
import org.example.calcutask.Repository.UserRepository;
import org.example.calcutask.Service.SubtaskService;
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

class SubtaskServiceTest {

    @Mock
    private SubtaskRepository subtaskRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private SubtaskService subtaskService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateSubtask() {
        Subtask subtask = new Subtask();
        doNothing().when(subtaskRepository).save(subtask);

        subtaskService.createSubtask(subtask);

        verify(subtaskRepository, times(1)).save(subtask);
    }

    @Test
    void testGetSubtasksByTaskId() {
        int taskId = 1;
        Subtask subtask = new Subtask();
        subtask.setAssignedUserId(2);
        subtask.setAssignedUsername(null); // Ensure initial state
        when(subtaskRepository.findByTaskId(taskId)).thenReturn(Collections.singletonList(subtask));

        org.example.calcutask.Model.User user = new org.example.calcutask.Model.User();
        user.setUserId(2);
        user.setUsername("testUser");
        user.setPassword("password");
        user.setRole("USER");
        when(userRepository.findById(2)).thenReturn(user);

        List<Subtask> result = subtaskService.getSubtasksByTaskId(taskId);

        assertEquals(1, result.size());
        assertEquals("testUser", result.get(0).getAssignedUsername());
        verify(subtaskRepository, times(1)).findByTaskId(taskId);
        verify(userRepository, times(1)).findById(2);
    }

    @Test
    void testGetSubtaskById() {
        int subtaskId = 1;
        Subtask subtask = new Subtask();
        when(subtaskRepository.findById(subtaskId)).thenReturn(subtask);

        Subtask result = subtaskService.getSubtaskById(subtaskId);

        assertEquals(subtask, result);
        verify(subtaskRepository, times(1)).findById(subtaskId);
    }

    @Test
    void testUpdateSubtask() {
        Subtask subtask = new Subtask();
        doNothing().when(subtaskRepository).update(subtask);

        subtaskService.updateSubtask(subtask);

        verify(subtaskRepository, times(1)).update(subtask);
    }

    @Test
    void testDeleteSubtask() {
        int subtaskId = 1;
        doNothing().when(subtaskRepository).deleteById(subtaskId);

        subtaskService.deleteSubtask(subtaskId);

        verify(subtaskRepository, times(1)).deleteById(subtaskId);
    }

    @Test
    void testStatusAndAssignSubtaskToUser() {
        int subtaskId = 1;
        int userId = 2;
        String status = "IN_PROGRESS";
        doNothing().when(subtaskRepository).statusAndAssignSubtaskToUser(subtaskId, userId, status);

        subtaskService.statusAndAssignSubtaskToUser(subtaskId, userId, status);

        verify(subtaskRepository, times(1)).statusAndAssignSubtaskToUser(subtaskId, userId, status);
    }

    @Test
    void testReleaseSubtaskFromUser() {
        int subtaskId = 1;
        String expectedStatus = Status.Todo.name(); // Ensure correct status
        doNothing().when(subtaskRepository).releaseSubtaskFromUser(subtaskId, expectedStatus);

        subtaskService.releaseSubtaskFromUser(subtaskId);

        verify(subtaskRepository, times(1)).releaseSubtaskFromUser(subtaskId, expectedStatus);
    }

    @Test
    void testUpdateStatus() {
        int subtaskId = 1;
        String status = "DONE";
        doNothing().when(subtaskRepository).updateStatus(subtaskId, status);

        subtaskService.updateStatus(subtaskId, status);

        verify(subtaskRepository, times(1)).updateStatus(subtaskId, status);
    }
}