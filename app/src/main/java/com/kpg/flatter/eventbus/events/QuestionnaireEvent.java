package com.kpg.flatter.eventbus.events;

public class QuestionnaireEvent {
    private String status;

    public QuestionnaireEvent(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
