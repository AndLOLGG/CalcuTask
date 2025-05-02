package org.example.calcutask.Model;


public class SubTask {
    private int subTaskId;
    private String subTaskName;
    private String subTaskDescription;
    private int taskId;
    /** Vi skal tilføje tags senere**/

    public SubTask (int subTaskId, String subTaskName, String subTaskDescription, int taskId) {
        this.subTaskName = subTaskName;
        this.subTaskDescription = subTaskDescription;
        this.taskId = taskId;
    }
    public SubTask() {}

    public int getSubTaskId() {
        return subTaskId;
    }
    public void setSubTaskId(int subTaskId) {
        this.subTaskId = subTaskId;
    }
    public String getSubTaskName() {
        return subTaskName;
    }
    public void setSubTaskName(String subTaskName) {
        this.subTaskName = subTaskName;
    }
    public String getSubTaskDescription() {
        return subTaskDescription;
    }
    public void setSubTaskDescription(String subTaskDescription) {
        this.subTaskDescription = subTaskDescription;
    }

}
