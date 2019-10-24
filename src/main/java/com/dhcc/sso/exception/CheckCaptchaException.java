package com.dhcc.sso.exception;

import org.apereo.cas.authentication.AuthenticationException;

/**
 * Annotation:
 * 验证码不正确
 *
 * @Author: Adam Ming
 * @Date: Oct 24, 2019 at 5:27:13 PM
 */
public class CheckCaptchaException extends AuthenticationException {

    public CheckCaptchaException() {
        super();
    }

    public CheckCaptchaException(String explanation) {
        super(explanation);
    }
}
