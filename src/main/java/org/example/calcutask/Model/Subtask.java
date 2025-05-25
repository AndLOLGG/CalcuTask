package org.example.calcutask.Model;

import java.math.BigDecimal;
import java.util.List;

public class Subtask {
    private int subtaskId;
    private String subtaskName;
    private String subtaskDescription;
    private BigDecimal subtaskEstimatedHours;
    private String subtaskStatus;
    private int taskId;
    private Integer assignedUserId; // Hvem der har påtaget sig subtasken
    private String assignedUsername; // Navnet på brugeren
    private List<Subtask> subtasks; // Child subtasks

    /** Vi skal tilføje tags senere **/

    public Subtask(int subtaskId, String subtaskName, String subtaskDescription,
                   BigDecimal subtaskEstimatedHours, String subtaskStatus,
                   int taskId, Integer assignedUserId, String assignedUsername) {
        this.subtaskId = subtaskId;
        this.subtaskName = subtaskName;
        this.subtaskDescription = subtaskDescription;
        this.subtaskEstimatedHours = subtaskEstimatedHours;
        this.subtaskStatus = subtaskStatus;
        this.taskId = taskId;
        this.assignedUserId = assignedUserId;
        this.assignedUsername = assignedUsername;
    }

    public Subtask(int taskId) {
        this.taskId = taskId;
    }
    
    public Subtask() {}

    // Getters
    public int getSubtaskId() {
        return subtaskId;
    }

    public String getSubtaskName() {
        return subtaskName;
    }

    public String getSubtaskDescription() {
        return subtaskDescription;
    }

    public BigDecimal getSubtaskEstimatedHours() {
        return subtaskEstimatedHours;
    }

    public String getSubtaskStatus() {
        return subtaskStatus;
    }

    public int getTaskId() {
        return taskId;
    }

    public Integer getAssignedUserId() {
        return assignedUserId;
    }

    public String getAssignedUsername() {
        return assignedUsername;
    }

    public List<Subtask> getSubtasks() {
        return subtasks;
    }

    // Setters
    public void setSubtaskId(int subtaskId) {
        this.subtaskId = subtaskId;
    }

    public void setSubtaskName(String subtaskName) {
        this.subtaskName = subtaskName;
    }

    public void setSubtaskDescription(String subtaskDescription) {
        this.subtaskDescription = subtaskDescription;
    }

    public void setSubtaskEstimatedHours(BigDecimal subtaskEstimatedHours) {
        this.subtaskEstimatedHours = subtaskEstimatedHours;
    }

    public void setSubtaskStatus(String subtaskStatus) {
        this.subtaskStatus = subtaskStatus;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public void setAssignedUserId(Integer assignedUserId) {
        this.assignedUserId = assignedUserId;
    }

    public void setAssignedUsername(String assignedUsername) {
        this.assignedUsername = assignedUsername;
    }

    public void setSubtasks(List<Subtask> subtasks) {
        this.subtasks = subtasks;
    }
}
