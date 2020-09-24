package com.example.app;

public class LognRequest {
    private String email;
    private String password;

    public String getUsername() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.email = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
