package com.grupa1.SopoProject.handlers;

import org.springframework.security.core.AuthenticationException;

/**
 * @author Michal on 22.12.2018
 */

public class AccountBlockedException extends AuthenticationException {
    public AccountBlockedException(String message) {
        super(message);
    }
}
