package org.example.calcutask.Model;

public class User {
    private int userId;
    private String username;
    private String userEmail;
    private String password;
    private String role;

    public User(int userId, String username, String userEmail, String password, String role) {
        this.userId = userId;
        this.username = username;
        this.userEmail = userEmail;
        this.password = password;
        this.role = role;
    }

    public User() {}

    // Getters
    public int getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    // Setters
    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
