package com.dhcc.sso.exception;

import org.apereo.cas.authentication.AuthenticationException;

/**
 * Annotation:
 * 当前时间不在该用户授权访问系统时间内
 *
 * @Author: Adam Ming
 * @Date: Oct 24, 2019 at 5:28:50 PM
 */
public class CheckUserWarrantDateException extends AuthenticationException {
    public CheckUserWarrantDateException(String msg) {
        super(msg);
    }

    public CheckUserWarrantDateException() {
    }
}
