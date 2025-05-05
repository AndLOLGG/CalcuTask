package org.example.calcutask.Controller;
import org.springframework.stereotype.Controller;
import org.example.calcutask.Service.ProjectService;


@Controller
public class ProjectController {
    private final ProjectService projectService;

public ProjectController(ProjectService projectService) {
    this.projectService = projectService;
}


}
