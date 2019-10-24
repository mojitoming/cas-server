package com.dhcc.sso.exception;

import org.apereo.cas.authentication.AuthenticationException;

/**
 * Annotation:
 * 您无权访问该系统
 *
 * @Author: Adam Ming
 * @Date: Oct 24, 2019 at 5:31:27 PM
 */
public class CheckSystemRightException extends AuthenticationException {

    public CheckSystemRightException(String msg) {
        super(msg);
    }

    public CheckSystemRightException() {
        super();
    }
}
