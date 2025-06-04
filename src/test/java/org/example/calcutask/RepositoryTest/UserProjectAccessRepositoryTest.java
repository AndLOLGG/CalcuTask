package org.example.calcutask.RepositoryTest;

import org.example.calcutask.CalcuTaskApplication;
import org.example.calcutask.Model.UserProjectAccess;
import org.example.calcutask.Repository.UserProjectAccessRepository;
import org.example.calcutask.RowMapper.UserProjectAccessRowMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = CalcuTaskApplication.class)
@Sql(
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
        scripts = {"classpath:schema-h2.sql", "classpath:data-h2.sql"}
)
public class UserProjectAccessRepositoryTest {

    @Autowired
    private UserProjectAccessRepository repository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void testGetAccessByUserId() {
        // Arrange
        int userId = 1;

        try {
            // Act
            UserProjectAccess access = repository.getAccessByUserId(userId);

            // Assert
            assertNotNull(access);
            assertEquals(userId, access.getUserId());

        } catch (org.springframework.dao.IncorrectResultSizeDataAccessException e) {
            // Act
            List<UserProjectAccess> accessList = jdbcTemplate.query(
                    "SELECT * FROM user_project_access WHERE user_id = ?",
                    new UserProjectAccessRowMapper(), userId);

            // Assert
            assertTrue(accessList.size() > 1, "Expected more than one access entry");
            for (UserProjectAccess access : accessList) {
                assertEquals(userId, access.getUserId());
            }
        }
    }



    @Test
    public void testHasUserAccessToProject() {
        // Arrange
        int userId = 1;
        int projectId = 1;

        // Act
        UserProjectAccess access = repository.hasUserAccessToProject(userId, projectId);

        // Assert
        assertNotNull(access);
        assertEquals(userId, access.getUserId());
        assertEquals(projectId, access.getProjectId());
        assertEquals("EDIT", access.getAccessType());
    }

    @Test
    public void testRemoveAllAccessForUser() {
        // Arrange
        int userId = 2;
        List<Integer> projectsBefore = repository.findProjectIdsByUserId(userId);
        assertFalse(projectsBefore.isEmpty());

        // Act
        repository.removeAllAccessForUser(userId);

        // Assert
        List<Integer> projectsAfter = repository.findProjectIdsByUserId(userId);
        assertTrue(projectsAfter.isEmpty());
    }

    @Test
    public void testAddAccess() {
        // Arrange
        int userId = 3;
        int projectId = 1;
        UserProjectAccess newAccess = new UserProjectAccess();
        newAccess.setUserId(userId);
        newAccess.setProjectId(projectId);
        newAccess.setAccessType("READ_ONLY");

        // Act
        repository.addAccess(newAccess);

        // Assert
        UserProjectAccess savedAccess = repository.hasUserAccessToProject(userId, projectId);
        assertNotNull(savedAccess);
        assertEquals(userId, savedAccess.getUserId());
        assertEquals(projectId, savedAccess.getProjectId());
        assertEquals("READ_ONLY", savedAccess.getAccessType());
    }

    @Test
    public void testFindProjectIdsByUserId() {
        // Arrange
        int userId = 1;

        // Act
        List<Integer> projectIds = repository.findProjectIdsByUserId(userId);

        // Assert
        assertNotNull(projectIds);
        assertFalse(projectIds.isEmpty());
        assertTrue(projectIds.contains(1));
        assertTrue(projectIds.contains(2));
    }
}
