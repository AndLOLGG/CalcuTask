package org.example.calcutask.Repository;

import org.example.calcutask.Model.Subtask;
import java.util.List;

// Interface for Subtask data operations
public interface SubtaskRepository {
    // Finds subtasks by task ID
    List<Subtask> findByTaskId(int taskId);
    // Finds a subtask by its ID.
    Subtask findById(int subtaskId);
    // Saves a new subtask
    void save(Subtask subtask);
    // Updates an existing subtask
    void update(Subtask subtask);
    // Deletes a subtask by its ID
    void delete(int subtaskId);
}