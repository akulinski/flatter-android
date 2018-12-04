package com.kpg.flatter.eventbus.events;

public final class PhotoAddEvent {
    private boolean isSuccessful;
    private String message;

    public PhotoAddEvent(boolean isSuccessful, String message) {
        this.isSuccessful = isSuccessful;
        this.message = message;
    }

    public PhotoAddEvent(boolean isSuccessful){
        this.isSuccessful = isSuccessful;
    }

    public boolean isSuccessful() {
        return isSuccessful;
    }

    public void setSuccessful(boolean successful) {
        isSuccessful = successful;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
