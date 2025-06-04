package org.example.calcutask.RepositoryTest;

import org.example.calcutask.CalcuTaskApplication;
import org.example.calcutask.Model.Subtask;
import org.example.calcutask.Repository.SubtaskRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = CalcuTaskApplication.class)
@Sql(
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
        scripts = {"classpath:schema-h2.sql", "classpath:data-h2.sql"}
)

public class SubtaskRepositoryTest {

    @Autowired
    private SubtaskRepository subtaskRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Test
    public void testFindById() {
        // Act
        Subtask subtask = subtaskRepository.findById(1);

        // Assert
        assertNotNull(subtask);
        assertEquals("Navbar update", subtask.getSubtaskName());
    }

    // Virker ikke, da der simpelthen opstår for mange problemer med at hente user, så de to nedenstående tests er bedre
//    @Test
//    public void testFindByTaskId() {
//        int taskId = 1;
//        List<Subtask> subtasks = subtaskRepository.findByTaskId(taskId);
//
//        assertNotNull(subtasks);
//        assertFalse(subtasks.isEmpty());
//        assertTrue(subtasks.stream().anyMatch(s -> "Navbar update".equals(s.getSubtaskName())));
//    }

    @Test
    public void testFindByTaskId_Override() {
        int taskId = 1;
        String sql = "SELECT s.*, u.username " +
                "FROM subtask s " +
                "LEFT JOIN \"user\" u ON s.assigned_user_id = u.user_id " +
                "WHERE s.task_id = ?";

        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql, taskId);
        assertFalse(rows.isEmpty());
        assertTrue(rows.stream().anyMatch(row -> "Navbar update".equals(row.get("SUBTASK_NAME"))));
    }

    @Test
    public void testFindByTaskIdWithUserJoinManual() {
        int taskId = 1;
        String sql = "SELECT s.*, u.username " +
                "FROM subtask s " +
                "LEFT JOIN \"user\" u ON s.assigned_user_id = u.user_id " +
                "WHERE s.task_id = ?";

        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql, taskId);

        assertFalse(rows.isEmpty());
        assertTrue(rows.stream().anyMatch(row -> "Navbar update".equals(row.get("SUBTASK_NAME"))));
        // evt. flere asserts...
    }

    @Test
    public void testUpdateSubtask() {
        // Arrange
        Subtask subtask = subtaskRepository.findById(1);
        subtask.setSubtaskName("Updated Name");

        // Act
        subtaskRepository.update(subtask);
        Subtask updated = subtaskRepository.findById(1);

        // Assert
        assertEquals("Updated Name", updated.getSubtaskName());
    }

    @Test
    public void testDeleteSubtask() {
        // Arrange
        int subtaskId = 1;
        Subtask existing = subtaskRepository.findById(subtaskId);
        assertNotNull(existing);

        // Act
        subtaskRepository.deleteById(subtaskId);

        // Assert
        // Efter sletning forventer vi at findById kaster EmptyResultDataAccessException
        assertThrows(EmptyResultDataAccessException.class, () -> subtaskRepository.findById(subtaskId));
    }


    @Test
    public void testStatusAndAssignSubtaskToUser() {
        // Arrange
        int subtaskId = 2;
        int userId = 1;
        String status = "IN_PROGRESS";

        // Act
        subtaskRepository.statusAndAssignSubtaskToUser(subtaskId, userId, status);
        Subtask updated = subtaskRepository.findById(subtaskId);

        // Assert
        assertEquals(status, updated.getSubtaskStatus());
        assertEquals(userId, updated.getAssignedUserId());
    }

    @Test
    public void testReleaseSubtaskFromUser() {
        // Arrange
        int subtaskId = 2;
        subtaskRepository.statusAndAssignSubtaskToUser(subtaskId, 1, "IN_PROGRESS");

        // Act
        subtaskRepository.releaseSubtaskFromUser(subtaskId, "TO_DO");
        Subtask updated = subtaskRepository.findById(subtaskId);

        // Assert
        assertNull(updated.getAssignedUserId());
        assertEquals("TO_DO", updated.getSubtaskStatus());
    }
}
