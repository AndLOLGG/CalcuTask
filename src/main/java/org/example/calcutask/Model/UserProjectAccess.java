package org.example.calcutask.Model;

public class UserProjectAccess {
    private int userId;
    private int projectId;
    private String accessType;

    public UserProjectAccess() {}

    public String getAccessType() {
        return accessType;
    }

    public void setAccessType(String accessType) {
        this.accessType = accessType;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int id) {
        this.userId = id;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int id) {
        this.projectId = id;
    }

}