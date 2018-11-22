package com.kpg.flatter.utills;


/**
 * Enum class that returns staus of sign in request
 */
public enum Status {

    SUCCES("SUCCES"),
    FALIURE("FAILURE");

    public String str;

    Status(String s){

        str = s;

    }
}
