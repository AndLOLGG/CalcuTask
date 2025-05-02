package org.example.calcutask.Model;


public class Subtask {
    private int subtaskId;
    private String subtaskName;
    private String subtaskDescription;
    private int taskId;
    /** Vi skal tilføje tags senere**/

    public Subtask (int subtaskId, String subtaskName, String subtaskDescription, int taskId) {
        this.subtaskName = subtaskName;
        this.subtaskDescription = subtaskDescription;
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

}
