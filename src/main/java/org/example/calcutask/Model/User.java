package org.example.calcutask.Model;
//
//import org.example.calcutask.Util.PasswordUtil;

public class User {
    private int userId;
    private String username;
    private String userEmail;
    private String passwordHash;
    private String role;

    public User(int userId, String username, String userEmail, String passwordHash, String role) {
        this.userId = userId;
        this.username = username;
        this.userEmail = userEmail;
        this.passwordHash = passwordHash;
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

    public String getPasswordHash() {
        return passwordHash;
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

//    public void setPassword(String rawPassword) {
//        this.passwordHash = PasswordUtil.hashPassword(rawPassword);
//    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public void setRole(String role) {
        this.role = role;
    }
}