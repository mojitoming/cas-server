package com.dhcc.sso.exception;

import org.apereo.cas.authentication.AuthenticationException;

public class CheckUserInfoErrorException extends AuthenticationException {

    public CheckUserInfoErrorException(String msg) {
        super(msg);
    }

    public CheckUserInfoErrorException() {
        super();
    }
}
