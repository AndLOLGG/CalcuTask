package org.example.calcutask.Model;

public class Project {
    private int projectId;
    private String projectName;
    private int userId;

    public Project(int projectiD, String projectName, int userId) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.userId = userId;

    }

    public int getProjectId() {
        return projectId;
    }
        public void setProjectId(int projectId) {
            this.projectId = projectId;
        }



    public String getProjectName() {
        return projectName;
    }
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }


}


