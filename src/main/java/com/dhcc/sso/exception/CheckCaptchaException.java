package com.dhcc.sso.exception;

import org.apereo.cas.authentication.AuthenticationException;

public class CheckCaptchaException extends AuthenticationException {

    public CheckCaptchaException() {
        super();
    }

    public CheckCaptchaException(String explanation) {
        super(explanation);
    }
}
