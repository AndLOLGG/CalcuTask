package org.example.calcutask.Model;


import java.math.BigDecimal;

public class Subtask {
    private int subtaskId;
    private String subtaskName;
    private String subtaskDescription;
    private BigDecimal subtaskEstimatedHours;
    private String subtaskStatus;
    private int taskId;

    /** Vi skal tilføje tags senere**/

    public Subtask (int subtaskId, String subtaskName, String subtaskDescription, BigDecimal subtaskEstimatedHours, String subtaskStatus, int taskId) {
        this.subtaskName = subtaskName;
        this.subtaskDescription = subtaskDescription;
        this.subtaskEstimatedHours = subtaskEstimatedHours;
        this.subtaskStatus = subtaskStatus;
        this.taskId = taskId;
    }
    public Subtask() {}

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

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public int getTaskId() {
        return taskId;
    }
}
