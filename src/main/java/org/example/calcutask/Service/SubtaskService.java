package org.example.calcutask.Service;

import org.example.calcutask.Model.Status;
import org.example.calcutask.Model.Subtask;
import org.example.calcutask.Repository.SubtaskRepository;
import org.example.calcutask.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubtaskService {

    @Autowired
    private SubtaskRepository subtaskRepository;
    @Autowired
    private UserRepository userRepository;

    // Create a new subtask
    public void createSubtask(Subtask subtask) {
        subtaskRepository.save(subtask);
    }

    // Retrieve all subtasks for a specific task
    public List<Subtask> getSubtasksByTaskId(int taskId) {
        List<Subtask> subtasks = subtaskRepository.findByTaskId(taskId);
        for (Subtask sb : subtasks) {
            if(sb.getAssignedUserId() != null) {
                sb.setAssignedUsername(userRepository.findById(sb.getAssignedUserId()).getUsername());
            }
        }
        return subtasks;
    }

    // Retrieve a specific subtask by its ID
    public Subtask getSubtaskById(int subtaskId) {
        return subtaskRepository.findById(subtaskId);
    }

    // Update an existing subtask
    public void updateSubtask(Subtask subtask) {
        subtaskRepository.update(subtask);
    }

    // Delete a subtask by its ID
    public void deleteSubtask(int subtaskId) {
        subtaskRepository.deleteById(subtaskId);
    }

    //
    public void statusAndAssignSubtaskToUser(int subtaskId, int userId, String subtaskStatus) {
        subtaskRepository.statusAndAssignSubtaskToUser(subtaskId, userId, subtaskStatus);
    }

    public void releaseSubtaskFromUser(int subtaskId) {
        subtaskRepository.releaseSubtaskFromUser(subtaskId, Status.Todo.name());
    }

    public void updateStatus(int subtaskId, String status) {
        subtaskRepository.updateStatus(subtaskId, status);
    }
}
