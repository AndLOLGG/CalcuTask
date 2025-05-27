package org.example.calcutask.ControllerTest;

import org.example.calcutask.Controller.UserController;
import org.example.calcutask.Model.User;
import org.example.calcutask.Service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class UserControllerTest {

    private MockMvc mockMvc;
    private UserService userService;

    @BeforeEach
    void setup() {
        userService = Mockito.mock(UserService.class);
        UserController userController = new UserController(userService);

        mockMvc = MockMvcBuilders.standaloneSetup(userController)
                .setViewResolvers((viewName, locale) -> new org.springframework.web.servlet.view.InternalResourceView("/WEB-INF/views/" + viewName + ".jsp"))
                .build();
    }

    @Test
    void testShowLoginPage() throws Exception {
        mockMvc.perform(get("/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"));
    }

    @Test
    void testLoginUser_Success() throws Exception {
        User mockUser = new User();
        mockUser.setUserId(1);

        when(userService.authenticateAndGetUser(anyString(), anyString())).thenReturn(mockUser);

        mockMvc.perform(post("/login")
                        .param("username", "testUser")
                        .param("password", "password123"))
                .andExpect(status().is3xxRedirection()) // Expect 3xx redirection
                .andExpect(redirectedUrl("/project")) // Verify redirection URL
                .andExpect(request().sessionAttribute("userId", 1)); // Verify session attribute
    }

    @Test
    void testLoginUser_Failure() throws Exception {
        when(userService.authenticateAndGetUser(anyString(), anyString())).thenReturn(null);

        mockMvc.perform(post("/login")
                        .param("username", "testUser")
                        .param("password", "wrongPassword"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"))
                .andExpect(model().attributeExists("error"));
    }

    @Test
    void testLogoutUser() throws Exception {
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("userId", 1);

        mockMvc.perform(get("/logout").session(session))
                .andExpect(status().is3xxRedirection()) // Expect 3xx redirection
                .andExpect(redirectedUrl("/login")); // Verify redirection URL

        // Verify session invalidation
        assert session.getAttribute("userId") == null;
    }
}