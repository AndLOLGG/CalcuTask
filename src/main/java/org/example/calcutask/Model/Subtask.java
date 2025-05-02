package org.example.calcutask.Model;

import java.math.BigDecimal;

public class Subtask {
    private int subtaskId;
    private String subtaskName;
    private String subtaskDescription;
    private BigDecimal subtaskEstimatedHours;
    private String subtaskStatus;
    private int projectId;

    // Getters and Setters
    public int getSubtaskId() {
        return subtaskId;
    }

    public void setSubtaskId(int subtaskId) {
        this.subtaskId = subtaskId;
    }

    public String getSubtaskName() {
        return subtaskName;
    }

    public void setSubtaskName(String subtaskName) {
        this.subtaskName = subtaskName;
    }

    public String getSubtaskDescription() {
        return subtaskDescription;
    }

    public void setSubtaskDescription(String subtaskDescription) {
        this.subtaskDescription = subtaskDescription;
    }

    public BigDecimal getSubtaskEstimatedHours() {
        return subtaskEstimatedHours;
    }

    public void setSubtaskEstimatedHours(BigDecimal subtaskEstimatedHours) {
        this.subtaskEstimatedHours = subtaskEstimatedHours;
    }

    public String getSubtaskStatus() {
        return subtaskStatus;
    }

    public void setSubtaskStatus(String subtaskStatus) {
        this.subtaskStatus = subtaskStatus;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }
}