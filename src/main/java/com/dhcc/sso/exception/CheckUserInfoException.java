package com.dhcc.sso.exception;

import org.apereo.cas.authentication.AuthenticationException;

/**
 * Annotation:
 * 用户名或密码错误
 *
 * @Author: Adam Ming
 * @Date: Oct 24, 2019 at 5:28:24 PM
 */
public class CheckUserInfoException extends AuthenticationException {

    public CheckUserInfoException(String msg) {
        super(msg);
    }

    public CheckUserInfoException() {
        super();
    }
}
