package org.example.calcutask.ModelTest;

import org.example.calcutask.Model.Subtask;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SubtaskTest {

    @Test
    void testSubtaskGettersAndSetters() {
        Subtask subtask = new Subtask();
        subtask.setSubtaskId(1);
        subtask.setSubtaskName("Test Subtask");
        subtask.setSubtaskDescription("Test Description");
        subtask.setSubtaskEstimatedHours(5);
        subtask.setSubtaskStatus("In Progress");
        subtask.setTaskId(10);
        subtask.setAssignedUserId(100);
        subtask.setAssignedUsername("testUser");

        List<Subtask> childSubtasks = new ArrayList<>();
        subtask.setSubtasks(childSubtasks);

        assertEquals(1, subtask.getSubtaskId());
        assertEquals("Test Subtask", subtask.getSubtaskName());
        assertEquals("Test Description", subtask.getSubtaskDescription());
        assertEquals(5, subtask.getSubtaskEstimatedHours());
        assertEquals("In Progress", subtask.getSubtaskStatus());
        assertEquals(10, subtask.getTaskId());
        assertEquals(100, subtask.getAssignedUserId());
        assertEquals("testUser", subtask.getAssignedUsername());
        assertEquals(childSubtasks, subtask.getSubtasks());
    }

    @Test
    void testSubtaskConstructorWithAllFields() {
        Subtask subtask = new Subtask(1, "Test Subtask", "Test Description", 5, "In Progress", 10, 100, "testUser");

        assertEquals(1, subtask.getSubtaskId());
        assertEquals("Test Subtask", subtask.getSubtaskName());
        assertEquals("Test Description", subtask.getSubtaskDescription());
        assertEquals(5, subtask.getSubtaskEstimatedHours());
        assertEquals("In Progress", subtask.getSubtaskStatus());
        assertEquals(10, subtask.getTaskId());
        assertEquals(100, subtask.getAssignedUserId());
        assertEquals("testUser", subtask.getAssignedUsername());
    }

    @Test
    void testSubtaskConstructorWithPartialFields() {
        Subtask subtask = new Subtask("Test Subtask", "Test Description", 5, 10);

        assertEquals("Test Subtask", subtask.getSubtaskName());
        assertEquals("Test Description", subtask.getSubtaskDescription());
        assertEquals(5, subtask.getSubtaskEstimatedHours());
        assertEquals("To-do", subtask.getSubtaskStatus());
        assertEquals(10, subtask.getTaskId());
    }

    @Test
    void testSubtaskConstructorWithTaskIdOnly() {
        Subtask subtask = new Subtask(10);

        assertEquals(10, subtask.getTaskId());
    }

    @Test
    void testDefaultValues() {
        Subtask subtask = new Subtask();

        assertNull(subtask.getSubtaskName());
        assertNull(subtask.getSubtaskDescription());
        assertNull(subtask.getSubtaskEstimatedHours());
        assertNull(subtask.getSubtaskStatus());
        assertNull(subtask.getAssignedUserId());
        assertNull(subtask.getAssignedUsername());
        assertNull(subtask.getSubtasks());
    }
}