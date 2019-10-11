package com.dhcc.sso.exception;

import org.apereo.cas.authentication.AuthenticationException;

public class CheckRoleStatusException extends AuthenticationException {
    public CheckRoleStatusException(String msg) {
        super(msg);
    }

    public CheckRoleStatusException() {
    }
}
