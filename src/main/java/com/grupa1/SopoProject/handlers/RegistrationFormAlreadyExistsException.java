package com.grupa1.SopoProject.handlers;

/**
 * @author Michal on 16.01.2019
 */

public class RegistrationFormAlreadyExistsException extends Exception {
    public RegistrationFormAlreadyExistsException(String message) {
        super(message);
    }
}
