package com.kpg.flatter.eventbus.events;

public class AddOfferEvent {
    private String status;

    public AddOfferEvent(String status) { this.status = status;}

    public String getStatus() {return status;}
}
