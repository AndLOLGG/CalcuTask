package org.example.calcutask.Service;

import org.example.calcutask.Model.Subtask;
import org.example.calcutask.Repository.SubtaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubtaskService {

    @Autowired
    private SubtaskRepository subtaskRepository;

    // Create a new subtask
    public void createSubtask(Subtask subtask) {
        subtaskRepository.save(subtask);
    }

    // Retrieve all subtasks for a specific task
    public List<Subtask> getSubtasksByTaskId(int taskId) {
        return subtaskRepository.findByTaskId(taskId);
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
    public void assignSubtaskToUser(int subtaskId, int userId) {
        subtaskRepository.assignSubtaskToUser(subtaskId, userId);
    }
    public void releaseSubtaskFromUser(int subtaskId) {
        subtaskRepository.releaseSubtaskFromUser(subtaskId);
    }

}
