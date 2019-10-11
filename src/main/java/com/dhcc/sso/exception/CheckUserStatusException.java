package com.dhcc.sso.exception;

import org.apereo.cas.authentication.AuthenticationException;

public class CheckUserStatusException extends AuthenticationException {
    public CheckUserStatusException(String msg) {
        super(msg);
    }

    public CheckUserStatusException() {
    }
}
