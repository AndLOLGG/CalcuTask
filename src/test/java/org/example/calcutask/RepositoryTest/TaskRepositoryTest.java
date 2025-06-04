package org.example.calcutask.RepositoryTest;

import org.example.calcutask.CalcuTaskApplication;
import org.example.calcutask.Model.Task;
import org.example.calcutask.Repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = CalcuTaskApplication.class)
@Sql(
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
        scripts = {"classpath:schema-h2.sql", "classpath:data-h2.sql"}
)
public class TaskRepositoryTest {

    @Autowired
    private TaskRepository taskRepository;

    @Test
    public void testFindByProjectId() {
        // Arrange
        int projectId = 1;

        // Act
        List<Task> tasks = taskRepository.findByProjectId(projectId);

        // Assert
        assertNotNull(tasks);
        assertFalse(tasks.isEmpty());
        assertTrue(tasks.stream().anyMatch(task -> "Frontend Redesign".equals(task.getTaskName())));
    }


    @Test
    public void testSaveAndFindById() {
        // Arrange
        Task task = new Task();
        task.setTaskName("Test Task");
        task.setTaskDescription("Desc");
        task.setTaskEstimatedHours(new BigDecimal("3.5"));
        task.setProjectId(1);

        // Act
        taskRepository.save(task);
        List<Task> tasks = taskRepository.findByProjectId(1);

        // Assert
        assertTrue(tasks.stream().anyMatch(t -> "Test Task".equals(t.getTaskName())));
    }

    @Test
    public void testUpdate() {
        // Arrange
        List<Task> tasks = taskRepository.findByProjectId(1);
        assertFalse(tasks.isEmpty());
        Task task = tasks.get(0);
        task.setTaskName("Updated Name");

        // Act
        taskRepository.update(task);

        // Assert
        Task updatedTask = taskRepository.findById(task.getTaskId());
        assertEquals("Updated Name", updatedTask.getTaskName());
    }

    @Test
    public void testDeleteById() {
        // Arrange
        List<Task> tasks = taskRepository.findByProjectId(1);
        assertFalse(tasks.isEmpty());
        int taskId = tasks.get(0).getTaskId();

        // Act
        taskRepository.deleteById(taskId);

        // Assert
        List<Task> afterDelete = taskRepository.findByProjectId(1);
        assertTrue(afterDelete.stream().noneMatch(t -> t.getTaskId() == taskId));
    }

    @Test
    public void testUpdateActualHours() {
        // Arrange
        List<Task> tasks = taskRepository.findByProjectId(1);
        assertFalse(tasks.isEmpty());
        Task task = tasks.get(0);
        int newActualHours = 10;

        // Act
        taskRepository.updateActualHours(task.getTaskId(), newActualHours);

        // Assert
        Task updatedTask = taskRepository.findById(task.getTaskId());
        assertEquals(newActualHours, updatedTask.getActualHours());
    }
}
