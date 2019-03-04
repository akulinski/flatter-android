package com.kpg.flatter.requests.models;

public class LoginPostModel {

    private String password;
    private Boolean rememberMe;
    private String username;

    public LoginPostModel(String password, Boolean rememberMe, String username) {
        this.password = password;
        this.rememberMe = rememberMe;
        this.username = username;
    }

    public LoginPostModel(String password, String username) {
        this.password = password;
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getRememberMe() {
        return rememberMe;
    }

    public void setRememberMe(Boolean rememberMe) {
        this.rememberMe = rememberMe;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
