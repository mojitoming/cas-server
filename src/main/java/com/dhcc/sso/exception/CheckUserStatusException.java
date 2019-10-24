package com.dhcc.sso.exception;

import org.apereo.cas.authentication.AuthenticationException;

/**
 * Annotation:
 * 该用户已被禁用
 *
 * @Author: Adam Ming
 * @Date: Oct 24, 2019 at 5:28:37 PM
 */
public class CheckUserStatusException extends AuthenticationException {
    public CheckUserStatusException(String msg) {
        super(msg);
    }

    public CheckUserStatusException() {
    }
}
