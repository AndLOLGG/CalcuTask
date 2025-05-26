package org.example.calcutask.Controller;

import jakarta.servlet.http.HttpSession;
import org.example.calcutask.Model.Project;
import org.example.calcutask.Service.ProjectService;
import org.example.calcutask.Service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ProjectController {

    private final ProjectService projectService;
    private final TaskService taskService;

    private final String userId = "userId";

    public ProjectController(ProjectService projectService, TaskService taskService) {
        this.projectService = projectService;
        this.taskService = taskService;
    }


    @GetMapping("/project")
    public String getAllProjects(Model model, HttpSession session) {
        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null) {
            return "redirect:/login"; // eller vis en 403 side
        }
        List<Project> projects = projectService.getProjectsByUserId(userId);
        List<Integer> projectIds = new ArrayList<>();
        for (Project p : projects) {
            String access = projectService.getUserAccessType(userId, p.getProjectId());
            projectIds.add(p.getProjectId());
            p.setAccessType(access);
        }

        model.addAttribute("tasks",taskService.getTasksByProjectIds(projectIds));
        model.addAttribute("projects", projects);
        return "project-list";
    }



    @GetMapping("/project/create")
    public String showCreateForm(Model model) {
        model.addAttribute("project", new Project());
        return "add-project";
    }

    @PostMapping("/project/create")
    public String addProject(@RequestParam String projectName,
                             @RequestParam String projectDescription,
                             HttpSession session) {
        Integer userIdFromSession = (Integer) session.getAttribute(userId);

        Project project = new Project();
        project.setProjectName(projectName);
        project.setProjectDescription(projectDescription);
        project.setUserId(userIdFromSession); // set owner

        projectService.addProject(project); // gemmer i `project`

        projectService.grantAccessToProject(userIdFromSession);

        return "redirect:/project-list";
    }


    @PostMapping("/project/delete")
    public String deleteProject(@RequestParam("projectId") int projectId) {
        try {
            projectService.deleteProject(projectId);
            return "redirect:/project";
        } catch (Exception e) {
            e.printStackTrace();
            return "error/500"; // Hvis du har lavet en custom fejlside
        }
    }


    @GetMapping("/project/edit")
    public String showEditForm(@RequestParam int projectId, Model model, HttpSession session) {
        Integer userIdFromSession = (Integer) session.getAttribute(userId);
        String accessType = projectService.getUserAccessType(userIdFromSession, projectId);

        if (!"EDIT".equals(accessType)) {

            return "error/403";
        }

            Project project = projectService.getProjectById(projectId);
            model.addAttribute("project", project);
            return "edit-project";


    }
        @PostMapping("/project/update")
        public String updateProject (@ModelAttribute Project project){
            projectService.updateProjectAndTasks(project);
            return "redirect:/project-list";
        }
}
