package org.example.calcutask.Controller;

import jakarta.servlet.http.HttpSession;
import org.example.calcutask.Model.Project;
import org.example.calcutask.Service.ProjectService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ProjectController {

    private final ProjectService projectService;
    private final String userId = "userId";

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("/project")
    public String getAllProjects(Model model, HttpSession session) {
        Integer userIdFromSession = (Integer) session.getAttribute(userId);
        if (userIdFromSession == null) {
            return "redirect:/login"; // Redirect to login or an appropriate page
        }
        List<Project> projects = projectService.getProjectsByUserId((Integer) session.getAttribute(userId));
        model.addAttribute("projects", projects);
        return "project";
    }

    @GetMapping("/project/create")
    public String showCreateForm(Model model) {
        model.addAttribute("project", new Project());
        return "add-project";
    }

    @PostMapping("/project/create")
    public String addProject(@RequestParam String projectName,
                             @RequestParam String projectDescription) {
        Project project = new Project();
        project.setProjectName(projectName);
        project.setProjectDescription(projectDescription);
        project.setUserId(1);
        projectService.addProject(project);
        return "redirect:/project";
    }

    @PostMapping("/project/delete")
    public String deleteProject(@RequestParam("projectId") int projectId) {
        projectService.deleteProject(projectId);
        return "redirect:/project";
    }

    @GetMapping("/project/edit")
    public String showEditForm(@RequestParam int projectId, Model model, HttpSession session) {
        Project project = projectService.getProjectById(projectId);
        if((Integer) session.getAttribute(userId) == project.getUserId()) {
            model.addAttribute("project", project);
            return "edit-project";
        }
        return "redirect:/404";
    }

    @PostMapping("/project/update")
    public String updateProject(@ModelAttribute Project project) {
        projectService.updateProjectAndTasks(project);
        return "redirect:/project";
    }
}