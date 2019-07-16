package com.example.alex.rasenshuriken;

public class User {
    private String userId;
    private  String Username;

    public User(String userId, String username) {
        this.userId = userId;
        this.Username = username;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        this.Username = username;
    }
}
