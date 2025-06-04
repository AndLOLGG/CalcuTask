package org.example.calcutask.DBTest;

import org.example.calcutask.CalcuTaskApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = CalcuTaskApplication.class)
@Sql(
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
        scripts = {"classpath:schema-h2.sql", "classpath:data-h2.sql"}
)
public class DBTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    void testUserCount() {
        // Arrange
        // (No explicit setup needed since SQL scripts run before each test)

        // Act
        Integer count = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM APP_USER", Integer.class);

        // Assert
        assertNotNull(count, "User count should not be null");
        assertTrue(count > 0, "There should be users in the APP_USER table");
    }

    @Test
    void testProjectExists() {
        // Arrange
        String projectName = "Website Redesign";

        // Act
        Integer count = jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM PROJECT WHERE PROJECT_NAME = ?",
                Integer.class,
                projectName
        );

        // Assert
        assertEquals(1, count, "Project 'Website Redesign' should exist exactly once");
    }
}
