package org.example.calcutask.Controller;

import jakarta.servlet.http.HttpSession;
import org.example.calcutask.Model.Status;
import org.example.calcutask.Service.SubtaskService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @PostMapping("/subtak/updateTaskStatus")
    public String updateTaskStatus(@RequestParam int subtaskId, @RequestParam int projectId, @RequestParam Status status,  HttpSession session) {
        Integer userId = getUserIdFromSession(session);
        subtaskService.updateStatus(subtaskId, status);
        return "redirect:/project/overview?projectId=" + projectId;
    }

    private int getUserIdFromSession(HttpSession session) {
        return (Integer) session.getAttribute("userId");
    }
}





