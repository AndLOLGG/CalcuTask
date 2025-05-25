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
    
    public SubtaskController(SubtaskService subtaskService) {
        this.subtaskService = subtaskService;
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
        return "subtask-overview";
    }
}





