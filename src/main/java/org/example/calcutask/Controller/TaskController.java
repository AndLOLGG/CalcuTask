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

    private int getUserIdFromSession(HttpSession session) {
        return (Integer) session.getAttribute("userId");
    }
}







/**

package org.example.calcutask.Controller;

import jakarta.servlet.http.HttpSession;
import org.example.calcutask.Service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

//    @GetMapping("/task/create")
//    public String showCreateForm(Model model, HttpSession session) {
//        Integer userId = (Integer) session.getAttribute("userId");
//        if (userId == null) {
//            return "redirect:/";
//        }
//        model.addAttribute("wishlist", new Task());
//        return "add-task";
//    }

//De 2 nedenstående endpoints er begge tænkt til hvordan man tilføjer en task. Den ene er udfra Wishlist-logik
//Og den anden er ud fra Wish-logik
//    @PostMapping("/task/create")
//    public String addTask(@RequestParam String taskName, HttpSession session) {
//        Integer userId = (Integer) session.getAttribute("userId");
//        if (userId == null) {
//            return "redirect:/";
//        }
//
//        System.out.println("User ID: " + userId);
//
//        Task task = new Task();
//        task.setTaskName(taskName);
//        task.setUserId(userId);
//
//        System.out.println("Created Task: " + task.getTaskName() + " for User ID: " + task.getUserId());
//
//        TaskService.addTask(task);
//        return "redirect:/task";
//    }
}
 **/