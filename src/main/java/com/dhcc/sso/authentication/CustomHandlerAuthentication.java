package com.dhcc.sso.authentication;

import com.alibaba.fastjson.JSONObject;
import com.dhcc.sso.entity.*;
import com.dhcc.sso.exception.*;
import com.dhcc.sso.utils.SecurityUtils;
import com.dhcc.sso.utils.StringUtils;
import org.apache.commons.collections.map.HashedMap;
import org.apereo.cas.authentication.AuthenticationHandlerExecutionResult;
import org.apereo.cas.authentication.Credential;
import org.apereo.cas.authentication.MessageDescriptor;
import org.apereo.cas.authentication.PreventedException;
import org.apereo.cas.authentication.handler.support.AbstractPreAndPostProcessingAuthenticationHandler;
import org.apereo.cas.authentication.principal.PrincipalFactory;
import org.apereo.cas.services.ServicesManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
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

    @Value("${cas.logout.redirectParameter:service}")
    private String redirectParameter;

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
        if (!"ACTIVE".equalsIgnoreCase(status)) {
            throw new CheckUserStatusException();
        }

        // 判断密码
        String salt = user.getSalt();
        String pwdRight = user.getPassword();
        String pwdCheck = SecurityUtils.md5(salt + password);

        if (!pwdRight.equals(pwdCheck)) {
            throw new CheckUserInfoException();
        }
        // 清空密码
        user.setPassword("");
        user.setSalt("");

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
        sql = "SELECT * FROM T_ROLE R WHERE EXISTS(SELECT 1 FROM T_USER_ROLE UR WHERE UR.ROLE_ID = R.ROLE_ID AND UR.IS_DEFAULT='1' AND UR.USER_ID = ?)";
        Role role;
        try {
            long userId = user.getUserId();
            role = jdbcTemplate.queryForObject(sql, new Object[]{userId}, new BeanPropertyRowMapper<>(Role.class));
        } catch (EmptyResultDataAccessException e) {
            role = null;
        }
        if (role == null) {
            throw new CheckRoleInfoException();
        }

        if (!"ACTIVE".equals(role.getStatus())) {
            throw new CheckRoleStatusException();
        }

        // 获取权限 - system, page, function
        String[] moduleTypeArr = {"SYSTEM", "PAGE", "FUNCTION"};
        sql = "SELECT * FROM T_MODULE M WHERE M.STATUS = 'ACTIVE' AND M.MODULE_TYPE = ? " +
                  "AND EXISTS (SELECT 1 FROM T_ROLE_PRIVILEGE RP WHERE M.MODULE_ID = RP.PRIVI_ID AND RP.ROLE_ID = ? AND RP.PRIVI_TYPE_CODE = 'MODULE') ORDER BY M.ODN";
        List<Module> moduleList;
        HashedMap privilegeMap = new HashedMap();
        long roleId = role.getRoleId();
        String moduleAction;
        boolean hasAccessRight = false;
        // 获取 service 后面的 url
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String serviceStr = request.getQueryString();
        String clientUrlStr = null;
        if (!StringUtils.isNullOrEmpty(serviceStr) && serviceStr.contains(redirectParameter + "=")) {
            try {
                clientUrlStr = URLDecoder.decode(serviceStr.substring(serviceStr.indexOf("service=")), StandardCharsets.UTF_8.name());
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        } else {
            clientUrlStr = "";
            hasAccessRight = true;
        }
        for (String moduleType : moduleTypeArr) {
            try {
                moduleList = jdbcTemplate.query(sql, new Object[]{moduleType, roleId}, new BeanPropertyRowMapper<>(Module.class));
            } catch (EmptyResultDataAccessException e) {
                moduleList = new ArrayList<>();
            }

            privilegeMap.put(moduleType, moduleList);

            if ("SYSTEM".equals(moduleType)) {
                if (moduleList.size() == 0) {
                    throw new CheckSystemRightException();
                }
                for (Module module : moduleList) {
                    moduleAction = module.getModuleAction();

                    if (!StringUtils.isNullOrEmpty(clientUrlStr) && clientUrlStr.contains(moduleAction)) {
                        hasAccessRight = true;
                    }
                }
                if (!hasAccessRight) {
                    throw new CheckSystemRightException();
                }
            }
        }

        // data
        sql = "SELECT O.ORG_CODE, O.ORG_NAME, O.ODN AS ORG_ODN, T.ORG_TYPE_CODE, T.ORG_TYPE_NAME, T.ODN AS ORG_TYPE_ODN " +
                  "  FROM T_DICT_ORG O, T_DICT_ORG_TYPE T, T_DICT_ORG_TYPE_SUB S " +
                  " WHERE O.ORG_CODE = S.ORG_CODE " +
                  "   AND T.ORG_TYPE_CODE = S.ORG_TYPE_CODE " +
                  "   AND T.ORG_TYPE_CODE <> 'DHCC' " +
                  "   AND EXISTS( " +
                  "       SELECT 1 FROM T_ROLE_PRIVILEGE RP " +
                  "        WHERE O.ORG_CODE = RP.PRIVI_ID " +
                  "          AND RP.ROLE_ID = ? " +
                  "          AND RP.PRIVI_TYPE_CODE = 'DATA' " +
                  "       ) " +
                  " ORDER BY O.ODN ";
        List<OrgVO> orgVOList = jdbcTemplate.query(sql, new Object[]{roleId}, new BeanPropertyRowMapper<>(OrgVO.class));
        privilegeMap.put("DATA", orgVOList);

        final List<MessageDescriptor> list = new ArrayList<>();

        // 自定义返回给客户端的多个属性信息
        HashMap<String, Object> returnInfo = new HashMap<>();

        String userJsonStr = JSONObject.toJSONString(user);
        String roleJsonStr = JSONObject.toJSONString(role);
        String privilegeMapJsonStr = JSONObject.toJSONString(privilegeMap);

        returnInfo.put("user", userJsonStr);
        returnInfo.put("role", roleJsonStr);
        returnInfo.put("privilege", privilegeMapJsonStr);

        return createHandlerResult(customCredential, this.principalFactory.createPrincipal(username, returnInfo), list);

    }
}
