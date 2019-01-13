package com.kpg.flatter.core.exceptions;

public final class SharedPreferenceValueNotFoundException extends Throwable {

    private String key;

    private String fullMessage;

    private static String MESSAGE = "Key %s not found";

    public SharedPreferenceValueNotFoundException(String key) {
        this.key = key;
        this.fullMessage = String.format(MESSAGE,key);
    }

    public String getFullMessage(){
        return this.fullMessage;
    }
}
