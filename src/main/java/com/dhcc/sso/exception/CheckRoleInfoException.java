package com.dhcc.sso.exception;

import org.apereo.cas.authentication.AuthenticationException;

public class CheckRoleInfoException extends AuthenticationException {
    public CheckRoleInfoException(String msg) {
        super(msg);
    }

    public CheckRoleInfoException() {
    }
}
