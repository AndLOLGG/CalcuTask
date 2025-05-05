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
//
//    @GetMapping("/wishlist/{id}/add-wish")
//    public String showAddWishForm(@PathVariable("id") int taskId, Model model, HttpSession session) {
//        Integer loggedInUserId = (Integer) session.getAttribute("userId");
//        if (loggedInUserId == null) {
//            return "redirect:/login";
//        }
//
//        Task task = taskService.getTaskById(taskId);
//        if (task == null || task.getUserId() != loggedInUserId) {
//            return "redirect:/";
//        }
//
//        model.addAttribute("taskId", taskId);
//        model.addAttribute("task", new Task());
//        return "add-task";
//    }
}
