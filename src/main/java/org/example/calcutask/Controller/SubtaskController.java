package org.example.calcutask.Controller;

import jakarta.servlet.http.HttpSession;
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
        Integer userId = (Integer) session.getAttribute("userId");
        subtaskService.statusAndAssignSubtaskToUser(subtaskId, userId, Status.Igang);
        return "redirect:/project/overview?projectId=" + projectId;
    }
    @PostMapping("/subtask/release")
    public String releaseSubtask(@RequestParam int subtaskId, @RequestParam int projectId) {
        subtaskService.releaseSubtaskFromUser(subtaskId);
        return "redirect:/project/overview?projectId=" + projectId;
    }






}





