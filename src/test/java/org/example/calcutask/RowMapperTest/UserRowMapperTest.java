package org.example.calcutask.RowMapperTest;

import org.example.calcutask.Model.User;
import org.example.calcutask.RowMapper.UserRowMapper;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserRowMapperTest {

    @Test
    void testMapRow() throws SQLException {
        // Mock ResultSet
        ResultSet resultSet = mock(ResultSet.class);
        when(resultSet.getInt("user_id")).thenReturn(1);
        when(resultSet.getString("username")).thenReturn("testUser");
        when(resultSet.getString("user_password")).thenReturn("password123");
        when(resultSet.getString("role")).thenReturn("USER");

        // Test the RowMapper
        UserRowMapper rowMapper = new UserRowMapper();
        User user = rowMapper.mapRow(resultSet, 1);

        // Assertions
        assertAll(
                () -> assertEquals(1, user.getUserId(), "User ID mismatch"),
                () -> assertEquals("testUser", user.getUsername(), "Username mismatch"),
                () -> assertEquals("password123", user.getPassword(), "Password mismatch"),
                () -> assertEquals("USER", user.getRole(), "Role mismatch")
        );
    }
}