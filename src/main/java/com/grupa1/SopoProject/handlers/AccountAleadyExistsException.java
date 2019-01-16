package com.grupa1.SopoProject.handlers;

/**
 * @author Michal on 16.01.2019
 */

public class AccountAleadyExistsException extends Exception {
    public AccountAleadyExistsException(String message) {
        super(message);
    }
}
