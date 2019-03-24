package com.kpg.flatter.utills;


/**
 * Enum class that returns staus of sign in request
 */
public enum Status {

    SUCCES("SUCCESS"),
    FALIURE("FAILURE"),
    LOGINEXISTS("LOGINEXISTS"),
    EMAILEXISTS("EMAILEXISTS"),
    ERROR("ERROR"),
    UNAUTHORIZED("UNAUTHORIZED");


    public String str;

    Status(String s){

        str = s;

    }
}
