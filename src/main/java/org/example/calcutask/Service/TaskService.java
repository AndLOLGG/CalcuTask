package org.example.calcutask.Service;

import org.example.calcutask.Repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.example.calcutask.Model.Task;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public void createTask(Task task) {
        taskRepository.save(task);
    }

    public List<Task> getTasksByProjectId(int projectId) {
        return taskRepository.findByProjectId(projectId);
    }

    public List<Task> getTasksByProjectIds(List<Integer> projectIds) {
        return taskRepository.findByProjectIds(projectIds);
    }

    // Retrieve a task along with its associated subtasks
    public Task getTaskWithSubtasks(int taskId) {
        return taskRepository.findByIdWithSubtask(taskId);
    }

    public void deleteTask(int id) {
        taskRepository.deleteById(id);
    }

    public void updateTask(Task task) {
        taskRepository.update(task);
    }
}
