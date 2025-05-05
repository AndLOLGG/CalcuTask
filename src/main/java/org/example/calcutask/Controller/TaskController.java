package org.example.calcutask.Controller;
import org.springframework.stereotype.Controller;
import org.example.calcutask.Service.TaskService;

@Controller
public class TaskController {
    private final TaskService taskService;
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }



}
