package org.example.calcutask.ServiceTest;

import org.example.calcutask.Model.User;
import org.example.calcutask.Repository.UserRepository;
import org.example.calcutask.Service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateUser() {
        User user = new User();
        userService.createUser(user);
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void testFindByUsername() {
        String username = "testUser";
        User user = new User();
        when(userRepository.findByUsername(username)).thenReturn(user);

        User result = userService.findByUsername(username);

        assertEquals(user, result);
        verify(userRepository, times(1)).findByUsername(username);
    }

    @Test
    void testFindById() {
        int id = 1;
        User user = new User();
        when(userRepository.findById(id)).thenReturn(user);

        User result = userService.findById(id);

        assertEquals(user, result);
        verify(userRepository, times(1)).findById(id);
    }

    @Test
    void testAuthenticateAndGetUser_ValidCredentials() {
        String username = "testUser";
        String password = "password";
        User user = new User();
        user.setPassword(password);
        when(userRepository.findByUsername(username)).thenReturn(user);

        User result = userService.authenticateAndGetUser(username, password);

        assertEquals(user, result);
        verify(userRepository, times(1)).findByUsername(username);
    }

    @Test
    void testAuthenticateAndGetUser_InvalidCredentials() {
        String username = "testUser";
        String password = "wrongPassword";
        User user = new User();
        user.setPassword("password");
        when(userRepository.findByUsername(username)).thenReturn(user);

        User result = userService.authenticateAndGetUser(username, password);

        assertNull(result);
        verify(userRepository, times(1)).findByUsername(username);
    }

    @Test
    void testGetAllUsers() {
        List<User> users = Arrays.asList(new User(), new User());
        when(userRepository.findAll()).thenReturn(users);

        List<User> result = userService.getAllUsers();

        assertEquals(users, result);
        verify(userRepository, times(1)).findAll();
    }

    @Test
    void testUpdateUser() {
        User user = new User();
        userService.updateUser(user);
        verify(userRepository, times(1)).update(user);
    }
}