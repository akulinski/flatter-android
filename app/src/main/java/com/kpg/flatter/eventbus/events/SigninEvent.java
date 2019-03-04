package com.kpg.flatter.eventbus.events;


/**
 * Class that is used to communicate by EventBus
 * it caries status of sign in request
 */
public class SigninEvent {

    private String status;
    private String token;

    public SigninEvent(String status) {

        this.status = status;

    }

    public SigninEvent(String status, String token) {
        this.status = status;
        this.token = token;
    }

    public String getStatus() {

        return status;

    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
