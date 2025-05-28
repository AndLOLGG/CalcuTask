//package org.example.calcutask.ModelTest;
//
//import org.example.calcutask.Model.Subtask;
//import org.example.calcutask.Model.Task;
//import org.junit.jupiter.api.Test;
//
//import java.math.BigDecimal;
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class TaskTest {
//
//    @Test
//    void testTaskGettersAndSetters() {
//        Task task = new Task();
//        task.setTaskId(1);
//        task.setTaskName("Test Task");
//        task.setTaskDescription("Test Description");
//        task.setTaskEstimatedHours(new BigDecimal("8.5"));
//        task.setTaskStatus("In Progress");
//        task.setProjectId(100);
//
//        Subtask subtask = new Subtask();
//        List<Subtask> subtasks = new ArrayList<>();
//        subtasks.add(subtask);
//        task.setSubtask(subtask);
//        task.setSubtasks(subtasks);
//
//        assertEquals(1, task.getTaskId());
//        assertEquals("Test Task", task.getTaskName());
//        assertEquals("Test Description", task.getTaskDescription());
//        assertEquals(new BigDecimal("8.5"), task.getTaskEstimatedHours());
//        assertEquals("In Progress", task.getTaskStatus());
//        assertEquals(100, task.getProjectId());
//        assertEquals(subtask, task.getSubtask());
//        assertEquals(subtasks, task.getSubtasks());
//    }
//
//    @Test
//    void testTaskConstructorWithAllFields() {
//        Task task = new Task(1, "Test Task", "Test Description", new BigDecimal("8.5"), "In Progress", 100);
//
//        assertEquals(1, task.getTaskId());
//        assertEquals("Test Task", task.getTaskName());
//        assertEquals("Test Description", task.getTaskDescription());
//        assertEquals(new BigDecimal("8.5"), task.getTaskEstimatedHours());
//        assertEquals("In Progress", task.getTaskStatus());
//        assertEquals(100, task.getProjectId());
//    }
//
//    @Test
//    void testTaskConstructorWithPartialFields() {
//        Task task = new Task("Test Task", "Test Description", 100);
//
//        assertEquals("Test Task", task.getTaskName());
//        assertEquals("Test Description", task.getTaskDescription());
//        assertEquals(100, task.getProjectId());
//    }
//
//    @Test
//    void testDefaultValues() {
//        Task task = new Task();
//
//        assertNull(task.getTaskName());
//        assertNull(task.getTaskDescription());
//        assertNull(task.getTaskEstimatedHours());
//        assertNull(task.getTaskStatus());
//        assertEquals(0, task.getTaskId());
//        assertEquals(0, task.getProjectId());
//        assertNull(task.getSubtask());
//        assertNull(task.getSubtasks());
//    }
//}