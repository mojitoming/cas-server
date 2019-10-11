package com.dhcc.sso.exception;

import org.apereo.cas.authentication.AuthenticationException;

public class CheckUserInfoException extends AuthenticationException {

    public CheckUserInfoException(String msg) {
        super(msg);
    }

    public CheckUserInfoException() {
        super();
    }
}
