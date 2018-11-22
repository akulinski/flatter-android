package com.kpg.flatter.eventbus.events;


/**
 * Class that is used to communicate by EventBus
 * it caries status of sign in request
 */
public class SigninEvent {

    private String status;

    public SigninEvent(String status) {

        this.status = status;

    }

    public String getStatus() {

        return status;

    }
}
