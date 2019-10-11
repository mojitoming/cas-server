package com.dhcc.sso.authentication;

import com.alibaba.fastjson.JSONObject;
import com.dhcc.sso.entity.CustomCredential;
import com.dhcc.sso.entity.Module;
import com.dhcc.sso.entity.Role;
import com.dhcc.sso.entity.User;
import com.dhcc.sso.exception.*;
import com.dhcc.sso.utils.SecurityUtil;
import org.apache.commons.collections.map.HashedMap;
import org.apereo.cas.authentication.AuthenticationHandlerExecutionResult;
import org.apereo.cas.authentication.Credential;
import org.apereo.cas.authentication.MessageDescriptor;
import org.apereo.cas.authentication.PreventedException;
import org.apereo.cas.authentication.handler.support.AbstractPreAndPostProcessingAuthenticationHandler;
import org.apereo.cas.authentication.principal.PrincipalFactory;
import org.apereo.cas.services.ServicesManager;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import java.security.GeneralSecurityException;
import java.time.LocalDateTime;
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

        // 先判断验证码
        if (captcha != null) {
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            String captchaRight = attributes.getRequest().getSession().getAttribute("captcha_code").toString();

            if (!captcha.equalsIgnoreCase(captchaRight)) {
                throw new CheckCaptchaException();
            }
        }
        String sql = "SELECT * FROM T_USER WHERE USERNAME = ?";

        User user;
        try {
            user = jdbcTemplate.queryForObject(sql, new Object[]{username}, new BeanPropertyRowMapper<>(User.class));
        } catch (EmptyResultDataAccessException e) {
            user = null;
        }

        // 判断用户是否存在
        if (user == null) {
            throw new CheckUserInfoException();
        }

        // 判断用户是否可用
        String status = user.getStatus();
        if (!"1".equalsIgnoreCase(status)) {
            throw new CheckUserStatusException();
        }

        // 判断密码
        // TODO 默认密码修改
        String salt = user.getSalt();
        String pwdRight = user.getPassword();
        String pwdCheck = SecurityUtil.md5(salt + password);

        if (!pwdRight.equals(pwdCheck)) {
            throw new CheckUserInfoException();
        }
        // 清空密码
        user.setPassword("");

        // 判断用户是否在可用时间范围
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime starter, ender;
        if (user.getWarrantStartDate() == null) {
            starter = LocalDateTime.of(1991, 1, 1, 0, 0, 0);
        } else {
            starter = user.getWarrantStartDate();
        }
        if (user.getWarrantEndDate() == null) {
            ender = LocalDateTime.of(2999, 12, 31, 11, 59, 29);
        } else {
            ender = user.getWarrantEndDate();
        }
        if (now.compareTo(starter) < 0 || now.compareTo(ender) > 0) {
            throw new CheckUserWarrantDateException();
        }

        // 判断角色
        sql = "select * from t_role r where exists(select 1 from t_user_role ur where ur.role_id = r.role_id and ur.is_default='1' and ur.user_id = ?)";
        Role role;
        try {
            String userId = user.getUserId();
            role = jdbcTemplate.queryForObject(sql, new Object[]{userId}, new BeanPropertyRowMapper<>(Role.class));
        } catch (EmptyResultDataAccessException e) {
            role = null;
        }
        if (role == null) {
            throw new CheckRoleInfoException();
        }

        if (!"1".equals(role.getStatus())) {
            throw new CheckRoleStatusException();
        }

        // 获取权限 - system, page, data, function
        String[] priviTypes = {"SYSTEM", "PAGE", "DATA", "FUNCTION"};
        sql = "select * from t_module m where m.status = '1' and exists (select 1 from T_ROLE_PRIVILEGE rp where m.module_id = rp.privi_id and rp.role_id = ? and rp.PRIVI_TYPE_CODE = ?) order by m.seq_no";
        List<Module> moduleList;
        HashedMap privilegeMap = new HashedMap();
        String roleId = role.getRoleId();
        for (String priviType : priviTypes) {
            try {
                moduleList = jdbcTemplate.query(sql, new Object[]{roleId, priviType}, new BeanPropertyRowMapper<>(Module.class));
            } catch (EmptyResultDataAccessException e) {
                moduleList = new ArrayList<>();
            }

            privilegeMap.put(priviType, moduleList);
        }

        final List<MessageDescriptor> list = new ArrayList<>();

        // 自定义返回给客户端的多个属性信息
        HashMap<String, Object> returnInfo = new HashMap<>();

        String userJsonStr = JSONObject.toJSONString(user);
        String roleJsonStr = JSONObject.toJSONString(role);
        String privilegeMapJsonStr = JSONObject.toJSONString(privilegeMap);

        JSONObject userJson = (JSONObject) JSONObject.toJSON(user);
        JSONObject roleJson = (JSONObject) JSONObject.toJSON(role);
        JSONObject privilegeMapJson = (JSONObject) JSONObject.toJSON(privilegeMap);

        returnInfo.put("user", userJsonStr);
        returnInfo.put("role", roleJsonStr);
        returnInfo.put("privilege", privilegeMapJsonStr);

        return createHandlerResult(customCredential, this.principalFactory.createPrincipal(username, returnInfo), list);

    }
}
