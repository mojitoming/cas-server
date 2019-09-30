package com.dhcc.sso.authentication;

import com.dhcc.sso.entity.CustomCredential;
import com.dhcc.sso.entity.User;
import com.dhcc.sso.exception.CheckCaptchaErrorException;
import org.apereo.cas.authentication.AuthenticationHandlerExecutionResult;
import org.apereo.cas.authentication.Credential;
import org.apereo.cas.authentication.MessageDescriptor;
import org.apereo.cas.authentication.PreventedException;
import org.apereo.cas.authentication.handler.support.AbstractPreAndPostProcessingAuthenticationHandler;
import org.apereo.cas.authentication.principal.PrincipalFactory;
import org.apereo.cas.services.ServicesManager;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.security.auth.login.AccountException;
import javax.security.auth.login.FailedLoginException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author anumbrella
 */
public class CustomHandlerAuthentication extends AbstractPreAndPostProcessingAuthenticationHandler {
    @Resource
    private JdbcTemplate jdbcTemplate;

    public CustomHandlerAuthentication(String name, ServicesManager servicesManager, PrincipalFactory principalFactory, Integer order) {
        super(name, servicesManager, principalFactory, order);
    }

    @Override
    public boolean supports(Credential credential) {
        //判断传递过来的Credential 是否是自己能处理的类型
        return credential instanceof CustomCredential;
    }

    @Override
    protected AuthenticationHandlerExecutionResult doAuthentication(Credential credential) throws GeneralSecurityException, PreventedException {

        CustomCredential customCredential = (CustomCredential) credential;

        String username = customCredential.getUsername();
        String password = customCredential.getPassword();
        String captcha = customCredential.getCaptcha();

        System.out.println("username : " + username);
        System.out.println("password : " + password);
        System.out.println("captcha : " + captcha);

        // 先判断验证码
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        String captchaRight = attributes.getRequest().getSession().getAttribute("captcha_code").toString();

        if (!captcha.equalsIgnoreCase(captchaRight)) {
            throw new CheckCaptchaErrorException();
        }

        String sql = "SELECT USERNAME, PASSWORD, EXPIRED, DISABLED FROM T_USER WHERE USERNAME = ?";

        User info = jdbcTemplate.queryForObject(sql, new Object[]{username}, new BeanPropertyRowMapper<>(User.class));

        if (info == null) {
            throw new AccountException("Sorry, username not found!");
        }

        if (!info.getPassword().equals(password)) {
            throw new FailedLoginException("Sorry, password not correct!");
        } else {
            // 可自定义返回给客户端的多个属性信息
            HashMap<String, Object> returnInfo = new HashMap<>();
            returnInfo.put("expired", info.getDisabled());

            final List<MessageDescriptor> list = new ArrayList<>();

            return createHandlerResult(customCredential, this.principalFactory.createPrincipal(username, returnInfo), list);
        }
    }
}
