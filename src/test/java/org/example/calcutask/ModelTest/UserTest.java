package org.example.calcutask.ModelTest;

import org.example.calcutask.Model.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void testUserGettersAndSetters() {
        User user = new User();
        user.setUserId(1);
        user.setUsername("testUser");
        user.setPassword("password123");
        user.setUserEmail("test@example.com");
        user.setRole("Admin");

        assertEquals(1, user.getUserId());
        assertEquals("testUser", user.getUsername());
        assertEquals("password123", user.getPassword()); // Updated method
        assertEquals("test@example.com", user.getUserEmail());
        assertEquals("Admin", user.getRole());
    }

    @Test
    void testUserConstructorWithAllFields() {
        User user = new User(1, "testUser", "test@example.com", "password123", "Admin");

        assertEquals(1, user.getUserId());
        assertEquals("testUser", user.getUsername());
        assertEquals("test@example.com", user.getUserEmail());
        assertEquals("password123", user.getPassword()); // Updated method
        assertEquals("Admin", user.getRole());
    }

    @Test
    void testDefaultValues() {
        User user = new User();

        assertEquals(0, user.getUserId());
        assertNull(user.getUsername());
        assertNull(user.getUserEmail());
        assertNull(user.getPassword());
        assertNull(user.getRole());
    }
}