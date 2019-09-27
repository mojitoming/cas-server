package com.dhcc.sso.authentication;

import com.dhcc.sso.entity.User;
import org.apereo.cas.authentication.AuthenticationHandlerExecutionResult;
import org.apereo.cas.authentication.MessageDescriptor;
import org.apereo.cas.authentication.PreventedException;
import org.apereo.cas.authentication.UsernamePasswordCredential;
import org.apereo.cas.authentication.handler.support.AbstractUsernamePasswordAuthenticationHandler;
import org.apereo.cas.authentication.principal.PrincipalFactory;
import org.apereo.cas.services.ServicesManager;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.Resource;
import javax.security.auth.login.AccountException;
import javax.security.auth.login.FailedLoginException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Annotation:
 *
 * @Author: Adam Ming
 * @Date: Sep 27, 2019 at 11:08:40 AM
 */
public class CustomUsernamePasswordAuthentication extends AbstractUsernamePasswordAuthenticationHandler {
    @Resource
    private JdbcTemplate jdbcTemplate;

    public CustomUsernamePasswordAuthentication(String name, ServicesManager servicesManager, PrincipalFactory principalFactory, Integer order) {
        super(name, servicesManager, principalFactory, order);
    }

    @Override
    protected AuthenticationHandlerExecutionResult authenticateUsernamePasswordInternal(UsernamePasswordCredential usernamePasswordCredential, String s) throws GeneralSecurityException, PreventedException {

        String username = usernamePasswordCredential.getUsername();

        String password = usernamePasswordCredential.getPassword();

        System.out.println("username : " + username);
        System.out.println("password : " + password);

        String sql = "SELECT username, password, expired, disabled FROM T_USER WHERE username = ?";

        User info = jdbcTemplate.queryForObject(sql, new Object[]{username}, new BeanPropertyRowMapper<>(User.class));

        System.out.println("database username : " + info.getUsername());
        System.out.println("database password : " + info.getPassword());


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

            return createHandlerResult(usernamePasswordCredential, this.principalFactory.createPrincipal(username, returnInfo), list);
        }
    }
}
