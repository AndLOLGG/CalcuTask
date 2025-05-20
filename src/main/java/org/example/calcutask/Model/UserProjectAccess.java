package org.example.calcutask.Model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class UserProjectAccess {
    private int userId;
    private int projectId;
    private String accessType;

    public UserProjectAccess() {}

    public String getAccessType() {
        return accessType;
    }

    public String setAccessType(String accessType) {
        this.accessType = accessType;
    }

    public int getUserId() {
        return userId;
    }

    public int setUserId(int id) {
        this.userId = id;
    }

    public int getProjectId() {
        return projectId;
    }

    public int setProjectId(int id) {
        this.projectId = id;
    }

}