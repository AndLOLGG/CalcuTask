package org.example.calcutask.Model;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Project {
    private int projectId;
    private String projectName;
    private String projectDescription;
    private LocalDate createdDate;
    private String createdBy;
    private int userId;
    private List<Task> tasks = new ArrayList<>(); // Initieret for at undgå NullPointerException

    // NYT felt til adgangstype
    private String accessType;

    public Project(int projectId, String projectName, String projectDescription, LocalDate createdDate, String createdBy, int userId) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.projectDescription = projectDescription;
        this.createdDate = createdDate;
        this.createdBy = createdBy;
        this.userId = userId;
    }

    public Project() {}

    public int getProjectId() { return projectId; }
    public String getProjectName() { return projectName; }
    public String getProjectDescription() { return projectDescription; }
    public LocalDate getCreatedDate() { return createdDate; }
    public String getCreatedBy() { return createdBy; }
    public int getUserId() { return userId; }
    public List<Task> getTasks() { return tasks; }
    public String getAccessType() { return accessType; }

    public void setProjectId(int projectId) { this.projectId = projectId; }
    public void setProjectName(String projectName) { this.projectName = projectName; }
    public void setProjectDescription(String projectDescription) { this.projectDescription = projectDescription; }
    public void setCreatedDate(LocalDate createdDate) { this.createdDate = createdDate; }
    public void setCreatedBy(String createdBy) { this.createdBy = createdBy; }
    public void setUserId(int userId) { this.userId = userId; }
    public void setTasks(List<Task> tasks) { this.tasks = tasks; }
    public void setAccessType(String accessType) { this.accessType = accessType; }
}
