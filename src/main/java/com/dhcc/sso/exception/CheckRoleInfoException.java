package com.dhcc.sso.exception;

import org.apereo.cas.authentication.AuthenticationException;

/**
 * Annotation:
 * 该用户未分配角色
 *
 * @Author: Adam Ming
 * @Date: Oct 24, 2019 at 5:27:33 PM
 */
public class CheckRoleInfoException extends AuthenticationException {
    public CheckRoleInfoException(String msg) {
        super(msg);
    }

    public CheckRoleInfoException() {
    }
}
