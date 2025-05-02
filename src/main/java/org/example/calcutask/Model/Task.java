package org.example.calcutask.Model;

public class Task {
    private int taskId;
    private String taskName;
    private String taskDescription;
    private int projectId;
    /** Vi skal tilføje tags senere**/

public Task (int taskId, String taskName, String taskDescription, int projectId) {
    this.taskName = taskName;
    this.taskDescription = taskDescription;
    this.projectId = projectId;
}
public Task() {} /** skal denne linje overhovedet være her? unsure... **/

public int getTaskId() {
    return taskId;
}
public void setTaskId(int taskId) {
    this.taskId = taskId;
}
public String getTaskName() {
    return taskName;
}
public void setTaskName() {
    this.taskName = taskName;
}
public String getTaskDescription() {
    return taskDescription;
}
public void setTaskDescription(String taskDescription) {
    this.taskDescription = taskDescription;
}

}
