package org.example.calcutask.Model;

import java.sql.Date;

public class Project {
    private int projectId;
    private String projectName;
    private String projectDescription;
    private Date createdDate;
    private String createdBy;
    private int userId;


    public Project(int projectId, String projectName, String projectDescription, Date createdDate, String createdBy, int userId) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.projectDescription = projectDescription;
        this.createdDate = createdDate;
        this.createdBy = createdBy;
        this.userId = userId;
    }

    public Project() {}


    // Getters
    public int getProjectId() {
        return projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public int getUserId() {
        return userId;
    }

    // Setters
    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}


