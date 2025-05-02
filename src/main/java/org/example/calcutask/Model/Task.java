package org.example.calcutask.Model;

import java.math.BigDecimal;

public class Task {
    private int taskId;
    private String taskName;
    private String taskDescription;
    private int projectId;
    private BigDecimal taskEstimatedHours;
    private String taskStatus;
    /** Vi skal tilføje tags senere**/

    public Task (int taskId, String taskName, String taskDescription, int projectId) {
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.projectId = projectId;
    }
    public Task() {}

    public int getTaskId() {
        return taskId;
    }
    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }
    public String getTaskName() {
        return taskName;
    }
    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }
    public String getTaskDescription() {
        return taskDescription;
    }
    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public BigDecimal getTaskEstimatedHours() {
        return taskEstimatedHours;
    }

    public void setTaskEstimatedHours(BigDecimal taskEstimatedHours) {
        this.taskEstimatedHours = taskEstimatedHours;
    }

    public String getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }

}
