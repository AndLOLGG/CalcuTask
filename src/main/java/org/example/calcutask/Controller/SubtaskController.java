package org.example.calcutask.Controller;

import jakarta.servlet.http.HttpSession;
import org.example.calcutask.Model.Status;
import org.example.calcutask.Model.Subtask;
import org.example.calcutask.Model.Task;
import org.example.calcutask.Service.SubtaskService;
import org.example.calcutask.Service.TaskService;
import org.example.calcutask.Service.UserProjectAccessService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class SubtaskController {
    private final SubtaskService subtaskService;
    private final TaskService taskService;
    private final UserProjectAccessService userProjectAccessService;

    public SubtaskController(SubtaskService subtaskService, TaskService taskService, UserProjectAccessService userProjectAccessService) {
        this.subtaskService = subtaskService;
        this.taskService = taskService;
        this.userProjectAccessService = userProjectAccessService;
    }

    @PostMapping("/subtask/statusAndAssign")
    public String assignSubtask(@RequestParam int subtaskId, @RequestParam int taskId, HttpSession session) {
        Integer userId = getUserIdFromSession(session);
        subtaskService.statusAndAssignSubtaskToUser(subtaskId, userId, Status.Igang.name());
        return "redirect:/subtask/overview?taskId=" + taskId;
    }

    @PostMapping("/subtask/release")
    public String releaseSubtask(@RequestParam int subtaskId, @RequestParam int taskId) {
        subtaskService.releaseSubtaskFromUser(subtaskId);
        return "redirect:/subtask/overview?taskId=" + taskId;
    }

    @PostMapping("/subtask/updateTaskStatus")
    public String updateTaskStatus(@RequestParam int subtaskId, @RequestParam int taskId, @RequestParam Status status,  HttpSession session) {
        Integer userId = getUserIdFromSession(session);
        subtaskService.updateStatus(subtaskId, status.name());
        return "redirect:/subtask/overview?taskId=" + taskId;
    }

//    @GetMapping("/subtask/edit")
//    public String editSubtask(@RequestParam int subtaskId, HttpSession session, Model model) {
//        Integer userId = getUserIdFromSession(session);
//        model.addAttribute("subtask", subtaskService.getSubtaskById(subtaskId));
//        return "edit-subtask";
//    }

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
        model.addAttribute("taskId", taskId);
        return "create-subtask";
    }

    @PostMapping("/subtask/create")
    public String createTask(@ModelAttribute Subtask subtask, HttpSession session) {
        Integer userId = getUserIdFromSession(session);
        Task t = taskService.findById(subtask.getTaskId());
        Boolean hasAccess = userProjectAccessService.hasUserAccessToProject(userId, t.getProjectId());
        if(hasAccess) {
            Subtask st = new Subtask(subtask.getSubtaskName(), subtask.getSubtaskDescription(), subtask.getSubtaskEstimatedHours(), subtask.getTaskId());
            subtaskService.createSubtask(st);
            return "redirect:/subtask/overview?taskId=" + subtask.getTaskId();
        }
        return "redirect:/login";
    }

    @GetMapping("/subtask/edit") 
    public String editSubtask(@RequestParam int taskId, @RequestParam int subtaskId, Model model, HttpSession session) {
        Integer userId = getUserIdFromSession(session);
        Task t = taskService.findById(taskId);
        Boolean hasAccess = userProjectAccessService.hasUserAccessToProject(userId, t.getProjectId());
        if(hasAccess) {
            Subtask subtask = subtaskService.getSubtaskById(subtaskId);
            model.addAttribute("subtask", subtask);
            return "edit-subtask";
        }
        return "redirect:/login";
    }

    @PostMapping("/subtask/edit")
    public String updateSubtask(@ModelAttribute Subtask subtask) {
        Subtask st = subtask;
        subtaskService.updateSubtask(st);
        return "redirect:/subtask/overview?taskId=" + subtask.getTaskId();
    }

}





