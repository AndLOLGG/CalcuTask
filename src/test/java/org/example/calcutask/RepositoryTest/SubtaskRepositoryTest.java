package org.example.calcutask.RepositoryTest;

import org.example.calcutask.CalcuTaskApplication;
import org.example.calcutask.Model.Subtask;
import org.example.calcutask.Repository.SubtaskRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = CalcuTaskApplication.class)
@Sql(
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
        scripts = {"classpath:schema-h2.sql", "classpath:data-h2.sql"}
)
public class SubtaskRepositoryTest {

    @Autowired
    private SubtaskRepository subtaskRepository;

    @Test
    public void testFindById() {
        Subtask subtask = subtaskRepository.findById(1);
        assertNotNull(subtask);
        assertEquals("Navbar update", subtask.getSubtaskName());
    }

    @Test
    public void testFindByTaskId() {
        List<Subtask> subtasks = subtaskRepository.findByTaskId(1);
        assertNotNull(subtasks);
        assertFalse(subtasks.isEmpty());
        assertTrue(subtasks.stream().anyMatch(s -> "Navbar update".equals(s.getSubtaskName())));
    }

    @Test
    public void testUpdateSubtask() {
        Subtask subtask = subtaskRepository.findById(1);
        subtask.setSubtaskName("Updated Name");
        subtaskRepository.update(subtask);
        Subtask updated = subtaskRepository.findById(1);
        assertEquals("Updated Name", updated.getSubtaskName());
    }

    @Test
    public void testDeleteSubtask() {
        subtaskRepository.deleteById(1);
        Subtask deleted = subtaskRepository.findById(1);
        assertNull(deleted);
    }

    @Test
    public void testStatusAndAssignSubtaskToUser() {
        subtaskRepository.statusAndAssignSubtaskToUser(2, 1, "IN_PROGRESS");
        Subtask updated = subtaskRepository.findById(2);
        assertEquals("IN_PROGRESS", updated.getSubtaskStatus());
        assertEquals(1, updated.getAssignedUserId());
    }

    @Test
    public void testReleaseSubtaskFromUser() {
        subtaskRepository.statusAndAssignSubtaskToUser(2, 1, "IN_PROGRESS");
        subtaskRepository.releaseSubtaskFromUser(2, "TO_DO");
        Subtask updated = subtaskRepository.findById(2);
        assertNull(updated.getAssignedUserId());
        assertEquals("TO_DO", updated.getSubtaskStatus());
    }
}
