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
        this.subtaskId = subtaskId;
        this.subtaskName = subtaskName;
        this.subtaskDescription = subtaskDescription;
        this.subtaskEstimatedHours = subtaskEstimatedHours;
        this.subtaskStatus = subtaskStatus;
        this.taskId = taskId;
    }
    public Subtask() {}

    //Getters
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

    //Setters
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
}
