package com.dhcc.sso.entity;

import org.apereo.cas.authentication.UsernamePasswordCredential;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.constraints.Size;

public class CustomCredential extends UsernamePasswordCredential {
    private static final Logger logger = LoggerFactory.getLogger(CustomCredential.class);
    private static final Long serialVersionUID = -4166149641561667276L;

    @Size(min = 4, max = 6, message = "required.captcha")
    private String captcha;

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }
}
