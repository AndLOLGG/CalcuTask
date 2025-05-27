package org.example.calcutask.ControllerTest;

import jakarta.servlet.http.HttpSession;
import org.example.calcutask.Controller.UserController;
import org.example.calcutask.Model.User;
import org.example.calcutask.Service.UserService;
import org.example.calcutask.Repository.UserProjectAccessRepository;
import org.example.calcutask.Repository.ProjectRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.ui.Model;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class UserControllerTest {

    private UserController userController;
    private UserService userService;
    private UserProjectAccessRepository accessRepository;
    private ProjectRepository projectRepository;

    @BeforeEach
    void setup() {
        // Mock dependencies
        userService = Mockito.mock(UserService.class);
        accessRepository = Mockito.mock(UserProjectAccessRepository.class);
        projectRepository = Mockito.mock(ProjectRepository.class);

        // Initialize the controller with mocked dependencies
        userController = new UserController(userService, accessRepository, projectRepository);
    }

    @Test
    void testShowLoginPage() {
        // Test the login page view name
        String viewName = userController.showLoginPage();
        assertEquals("login", viewName, "The login page view name should be 'login'");
    }

    @Test
    void testLoginUser_Success() {
        // Mock user authentication
        User mockUser = new User();
        mockUser.setUserId(1);
        mockUser.setRole("USER");
        when(userService.authenticateAndGetUser(anyString(), anyString())).thenReturn(mockUser);

        // Mock session and model
        HttpSession session = mock(HttpSession.class);
        Model model = mock(Model.class);

        // Call the loginUser method
        String viewName = userController.loginUser("testUser", "password123", session, model);

        // Verify session attributes and redirection
        verify(session).setAttribute("userId", 1);
        verify(session).setAttribute("userRole", "USER");
        assertEquals("redirect:/project", viewName, "The user should be redirected to '/project' after successful login");
    }

    @Test
    void testLoginUser_Failure() {
        // Mock failed authentication
        when(userService.authenticateAndGetUser(anyString(), anyString())).thenReturn(null);

        // Mock session and model
        HttpSession session = mock(HttpSession.class);
        Model model = mock(Model.class);

        // Call the loginUser method
        String viewName = userController.loginUser("testUser", "wrongPassword", session, model);

        // Verify error attribute and view name
        verify(model).addAttribute(eq("error"), anyString());
        assertEquals("login", viewName, "The login page should be shown again on failed login");
    }

    @Test
    void testLogoutUser() {
        // Mock session
        HttpSession session = mock(HttpSession.class);

        // Call the logout method
        String viewName = userController.logout(session);

        // Verify session invalidation and redirection
        verify(session).invalidate();
        assertEquals("redirect:/login", viewName, "The user should be redirected to '/login' after logout");
    }

    @Test
    void testShowEditUserForm_AsAdmin() {
        // Mock session and model
        HttpSession session = mock(HttpSession.class);
        Model model = mock(Model.class);

        // Mock session attributes
        when(session.getAttribute("userId")).thenReturn(1);
        when(session.getAttribute("userRole")).thenReturn("ADMIN");

        // Mock repository and service calls
        when(userService.getAllUsers()).thenReturn(Collections.emptyList());
        when(projectRepository.findAllProjects()).thenReturn(Collections.emptyList());

        // Call the showEditUserForm method
        String viewName = userController.showEditUserForm(1, null, session, model);

        // Verify model attributes and view name
        verify(model).addAttribute(eq("users"), anyList());
        verify(model).addAttribute(eq("projects"), anyList());
        assertEquals("admin-edit-access", viewName, "The admin edit access page should be shown for admins");
    }
}