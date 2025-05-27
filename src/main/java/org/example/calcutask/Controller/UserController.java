package org.example.calcutask.Controller;

import jakarta.servlet.http.HttpSession;
import org.example.calcutask.Model.User;
import org.example.calcutask.Model.UserProjectAccess;
import org.example.calcutask.Service.UserService;
import org.example.calcutask.Repository.UserProjectAccessRepository;
import org.example.calcutask.Repository.ProjectRepository;
import org.example.calcutask.Model.Project;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class UserController {

    private final UserService userService;
    private final UserProjectAccessRepository accessRepository;
    private final ProjectRepository projectRepository;

    public UserController(UserService userService, UserProjectAccessRepository accessRepository, ProjectRepository projectRepository) {
        this.userService = userService;
        this.accessRepository = accessRepository;
        this.projectRepository = projectRepository;
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String loginUser(@RequestParam String username, @RequestParam String password, HttpSession session, Model model) {
        User user = userService.authenticateAndGetUser(username, password);
        if (user != null) {
            session.setAttribute("userId", user.getUserId());
            session.setAttribute("userRole", user.getRole());
            return "redirect:/project";
        }
        model.addAttribute("error", "User or password incorrect");
        return "login";
    }

    @GetMapping("/user/edit-user")
    public String showEditUserForm(@RequestParam int userId,
                                   @RequestParam(required = false) Boolean success,
                                   HttpSession session,
                                   Model model) {
        Integer loggedInUserId = (Integer) session.getAttribute("userId");
        String userRole = (String) session.getAttribute("userRole");

        if (loggedInUserId == null) {
            return "redirect:/login";
        }

        if ("ADMIN".equals(userRole)) {
            List<User> users = userService.getAllUsers();
            List<Project> projects = projectRepository.findAllProjects();

            Map<Integer, List<Integer>> userAccessMap = new HashMap<>();
            for (User user : users) {
                List<Integer> accessList = accessRepository.findProjectIdsByUserId(user.getUserId());
                userAccessMap.put(user.getUserId(), accessList);
            }

            model.addAttribute("users", users);
            model.addAttribute("projects", projects);
            model.addAttribute("userAccessMap", userAccessMap);
            model.addAttribute("success", success != null && success);

            return "admin-edit-access";
        }

        if (loggedInUserId == userId) {
            User user = userService.findById(userId);
            model.addAttribute("user", user);
            return "edit-user";
        }

        return "redirect:/access-denied";
    }

    @PostMapping("/admin/update-access")
    public String updateUserAccess(@RequestParam int userId, @RequestParam(required = false) List<Integer> projectIds) {
        accessRepository.removeAllAccessForUser(userId);
        if (projectIds != null) {
            for (Integer projectId : projectIds) {
                UserProjectAccess access = new UserProjectAccess();
                access.setUserId(userId);
                access.setProjectId(projectId);
                access.setAccessType("EDIT");
                accessRepository.addAccess(access);
            }
        }
        return "redirect:/user/edit-user?userId=" + userId + "&success=true";
    }

    @PostMapping("/user/update")
    public String updateUserProfile(@RequestParam int userId,
                                    @RequestParam String username,
                                    @RequestParam String password,
                                    HttpSession session) {
        Integer loggedInUserId = (Integer) session.getAttribute("userId");

        if (loggedInUserId == null || loggedInUserId != userId) {
            return "redirect:/access-denied";
        }

        User user = userService.findById(userId);
        user.setUsername(username);
        user.setPassword(password);

        userService.updateUser(user);

        return "redirect:/project";
    }
    @GetMapping("/admin/edit-user-form")
    public String showUserEditForm(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "admin-edit-user-form";
    }

    @GetMapping("/admin/create-user-form")
    public String showCreateUserForm(Model model) {
        model.addAttribute("user", new User());
        return "admin-create-user";
    }
    @PostMapping("/admin/update-user")
    public String updateUserAsAdmin(@RequestParam int userId,
                                    @RequestParam String username,
                                    @RequestParam String password) {
        User user = userService.findById(userId);
        user.setUsername(username);
        user.setPassword(password);
        userService.updateUser(user);
        return "redirect:/admin/edit-user-form";
    }

    @PostMapping("/admin/create-user")
    public String createNewUser(@RequestParam String username,
                                @RequestParam String password,
                                @RequestParam String role) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setRole(role);
        userService.createUser(user);
        return "redirect:/admin/edit-user-form";
    }
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // Fjerner alle session-attributter
        return "redirect:/login";
    }


}
