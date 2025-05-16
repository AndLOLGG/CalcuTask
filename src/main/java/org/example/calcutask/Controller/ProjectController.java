package org.example.calcutask.Controller;
import jakarta.servlet.http.HttpSession;
import org.example.calcutask.Model.Project;
import org.springframework.stereotype.Controller;
import org.example.calcutask.Service.ProjectService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
public class ProjectController {
    private final ProjectService projectService;

public ProjectController(ProjectService projectService) {
    this.projectService = projectService;
}
//Nedestående endpoint bruges højst sandsynligt kun for testing, senere skal den udvides til /project/{id}
@GetMapping("/project")
public String getAllProjects(Model model) {
    Integer testUserId = 1;

    List<Project> projects = projectService.getProjectsByUserId(testUserId);
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
                             @RequestParam String projectDescription
            /* , HttpSession session */) {

        Project project = new Project();
        project.setProjectName(projectName);
        project.setProjectDescription(projectDescription);
        project.setUserId(1);

        projectService.addProject(project);
        return "redirect:/project";
    }



}
