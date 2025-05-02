package org.example.calcutask.Model;

import java.math.BigDecimal;

public class Task {
    private int taskId;
    private String taskName;
    private String taskDescription;
    private BigDecimal taskEstimatedHours;
    private String taskStatus;
    private int projectId;
    /** Vi skal tilføje tags senere**/

    public Task (int taskId, String taskName, String taskDescription, BigDecimal taskEstimatedHours, String taskStatus, int projectId) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.taskEstimatedHours = taskEstimatedHours;
        this.taskStatus = taskStatus;
        this.projectId = projectId;
    }
    public Task() {}

    //Getters
    public int getTaskId() {
        return taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public BigDecimal getTaskEstimatedHours() {
        return taskEstimatedHours;
    }

    public String getTaskStatus() {
        return taskStatus;
    }

    public int getProjectId() {
        return projectId;
    }

    //Setters
    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public void setTaskEstimatedHours(BigDecimal taskEstimatedHours) {
        this.taskEstimatedHours = taskEstimatedHours;
    }

    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }
}
