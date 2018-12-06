package com.kpg.flatter.utills;
import org.apache.commons.validator.routines.EmailValidator;


/**
 * Validation class for password and email
 * Used during sign up
 */
public class Validation {

    public static boolean validatePassword(String password){
        String pattern = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!*])(?=\\S+$).{6,}";
        return password.matches(pattern);
    }

    public static boolean validateEmail(String email){
        return EmailValidator.getInstance().isValid(email);
    }

}
