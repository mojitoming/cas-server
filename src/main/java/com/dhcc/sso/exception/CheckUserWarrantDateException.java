package com.dhcc.sso.exception;

import org.apereo.cas.authentication.AuthenticationException;

public class CheckUserWarrantDateException extends AuthenticationException {
    public CheckUserWarrantDateException(String msg) {
        super(msg);
    }

    public CheckUserWarrantDateException() {
    }
}
