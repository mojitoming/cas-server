package com.dhcc.sso.exception;

import org.apereo.cas.authentication.AuthenticationException;

/**
 * Annotation:
 * 该用户所属角色已被禁用
 *
 * @Author: Adam Ming
 * @Date: Oct 24, 2019 at 5:28:09 PM
 */
public class CheckRoleStatusException extends AuthenticationException {
    public CheckRoleStatusException(String msg) {
        super(msg);
    }

    public CheckRoleStatusException() {
    }
}
