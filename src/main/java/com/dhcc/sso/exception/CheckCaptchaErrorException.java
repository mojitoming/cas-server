package com.dhcc.sso.exception;

import org.apereo.cas.authentication.AuthenticationException;

public class CheckCaptchaErrorException extends AuthenticationException {

    public CheckCaptchaErrorException() {
        super();
    }

    public CheckCaptchaErrorException(String explanation) {
        super(explanation);
    }
}
