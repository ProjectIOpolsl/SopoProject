package com.grupa1.SopoProject.handlers;

/**
 * @author Michal on 09.01.2019
 */

public class UserAlreadyVotedException extends Exception{

    public UserAlreadyVotedException(String message) {
        super(message);
    }
}
