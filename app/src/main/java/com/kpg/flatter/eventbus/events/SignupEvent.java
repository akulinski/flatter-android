package com.kpg.flatter.eventbus.events;

/**
 * Class that is used to communicate by EventBus
 * it caries status of sign up request
 */
public class SignupEvent {

    private String status;

    public SignupEvent(String status) {

        this.status = status;

    }

    public String getStatus() {

        return status;

    }

}
