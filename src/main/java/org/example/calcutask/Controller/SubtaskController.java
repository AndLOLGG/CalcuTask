package org.example.calcutask.Controller;

import jakarta.servlet.http.HttpSession;
import org.example.calcutask.Model.Status;
import org.example.calcutask.Model.Subtask;
import org.example.calcutask.Service.SubtaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class SubtaskController {
    private final SubtaskService subtaskService;
    
    public SubtaskController(SubtaskService subtaskService, TaskService taskService) {
        this.subtaskService = subtaskService;
        this.taskService = taskService;
    }

    @PostMapping("/subtask/statusAndAssign")
    public String assignSubtask(@RequestParam int subtaskId, HttpSession session, @RequestParam int projectId) {
        Integer userId = getUserIdFromSession(session);
        subtaskService.statusAndAssignSubtaskToUser(subtaskId, userId, Status.Igang.name());
        return "redirect:/project/overview?projectId=" + projectId;
    }
    @PostMapping("/subtask/release")
    public String releaseSubtask(@RequestParam int subtaskId, @RequestParam int projectId) {
        subtaskService.releaseSubtaskFromUser(subtaskId);
        return "redirect:/project/overview?projectId=" + projectId;
    }

    @PostMapping("/subtask/updateTaskStatus")
    public String updateTaskStatus(@RequestParam int subtaskId, @RequestParam int projectId, @RequestParam Status status,  HttpSession session) {
        Integer userId = getUserIdFromSession(session);
        subtaskService.updateStatus(subtaskId, status.name());
        return "redirect:/project/overview?projectId=" + projectId;
    }

    @GetMapping("/subtask/edit")
    public String editSubtask(@RequestParam int subtaskId, HttpSession session, Model model) {
        Integer userId = getUserIdFromSession(session);
        model.addAttribute("subtask", subtaskService.getSubtaskById(subtaskId));
        return "edit-subtask";
    }

    private int getUserIdFromSession(HttpSession session) {
        return (Integer) session.getAttribute("userId");
    }

    @GetMapping("/subtask/overview")
    public String showProjectOverview(@RequestParam int taskId, Model model) {
        List<Subtask> subtasks = subtaskService.getSubtasksByTaskId(taskId);
        model.addAttribute("subTasks", subtasks);
        model.addAttribute("taskId", taskId);
        return "subtask-overview";
    }

    @GetMapping("/subtask/create")
    public String createTask(@RequestParam int taskId, Model model) {
        model.addAttribute("subtask", new Subtask(taskId));
        return "create-subtask";
    }

    @PostMapping("/subtask/create")
    public String createTask(@ModelAttribute Subtask subtask, HttpSession session) {
        Integer userId = getUserIdFromSession(session);
        Task t = taskService.findById(taskId);
        Boolean hasAccess = userProjectAccessService.hasUserAccessToProject(userId, t.projectId);
        if(hasAccess) {
            Subtask st = new Subtask(subtask.getSubtaskName, subtask.getSubtaskDescription, subtask.getTaskEstimatedHours, subtask.getTaskId());
            subtaskService.createSubtask(st);
            return "subtask-overview";
        }
        return "redirect:/login";
    }

    @GetMapping("/subtask/edit") 
    public String editTask(@RequestParam int taskId, @RequestParam int subtaskId, Model model) {
        Integer userId = getUserIdFromSession(session);
        Task t = taskService.findById(taskId);
        Boolean hasAccess = userProjectAccessService.hasUserAccessToProject(userId, t.projectId);
        if(hasAccess) {
            Subtask st = new Subtask(subtask.getSubtaskName, subtask.getSubtaskDescription, subtask.getTaskEstimatedHours, subtask.getTaskId());
            subtaskService.createSubtask(st);
        }
        Task t = subtask.findById(taskId);
        t.setProjectId(projectId);
        model.addAttribute("task", t);
        return "edit-task";
    }

    @PostMapping("/subtask/edit")
    public String updateTask(@ModelAttribute Subtask subtask) {
        Subtask st = subtask;
        subtaskService.updateSubtask(st);
        return "redirect:/project";
    }

}





