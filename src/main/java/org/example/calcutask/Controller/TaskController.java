package org.example.calcutask.Controller;
import jakarta.servlet.http.HttpSession;
import org.example.calcutask.Model.Task;
import org.example.calcutask.Service.UserProjectAccessService;
import org.springframework.stereotype.Controller;
import org.example.calcutask.Service.TaskService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TaskController {
    private final UserProjectAccessService userProjectAccessService;

    private final TaskService taskService;
    public TaskController(TaskService taskService,  UserProjectAccessService userProjectAccessService) {
        this.taskService = taskService;
        this.userProjectAccessService = userProjectAccessService;
    }

    @GetMapping("/task/create")
    public String createTask(@RequestParam int projectId, Model model) {
        model.addAttribute("projectId", projectId);
        model.addAttribute("Task", new Task());
        return "create-task";
    }

    @PostMapping("/task/create")
    public String createTask(@RequestParam String taskName, @RequestParam String taskDescription, @RequestParam int projectId, HttpSession session) {
        Integer userId = getUserIdFromSession(session);
        Boolean hasAccess = userProjectAccessService.hasUserAccessToProject(userId, projectId);
        if(hasAccess) {
            Task t = new Task(taskName, taskDescription, projectId);
            taskService.createTask(t);
            return "redirect:/project";
        }
        return "redirect:/login";
    }

    @GetMapping("/task/edit") 
    public String editTask(@RequestParam int taskId, @RequestParam int projectId, Model model) {
        Task t = taskService.findById(taskId);
        t.setProjectId(projectId);
        model.addAttribute("task", t);
        return "edit-task";
    }

    @PostMapping("/task/edit")
    public String updateTask(@ModelAttribute Task task) {
        Task t = task;
        taskService.updateTask(task);
        return "redirect:/project";
    }

    @PostMapping("/task/delete")
    public String deleteProject(@RequestParam int taskId) {
        try {
            taskService.deleteTask(taskId);
            return "redirect:/project";
        } catch (Exception e) {
            e.printStackTrace();
            return "error/500"; // Hvis du har lavet en custom fejlside
        }
    }

    private int getUserIdFromSession(HttpSession session) {
        return (Integer) session.getAttribute("userId");
    }
}
