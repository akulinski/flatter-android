package com.kpg.flatter.eventbus.events;

public class ValidateTokenEvent {
    private String status;

    public ValidateTokenEvent(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
