package com.dhcc.sso.config;

import com.dhcc.sso.authentication.CustomHandlerAuthentication;
import org.apereo.cas.authentication.AuthenticationEventExecutionPlan;
import org.apereo.cas.authentication.AuthenticationEventExecutionPlanConfigurer;
import org.apereo.cas.authentication.AuthenticationHandler;
import org.apereo.cas.authentication.principal.DefaultPrincipalFactory;
import org.apereo.cas.configuration.CasConfigurationProperties;
import org.apereo.cas.services.ServicesManager;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * @author anumbrella
 * 自定义身份验证配置
 */
@Configuration
@EnableConfigurationProperties(CasConfigurationProperties.class)
public class CustomAuthenticationConfigurer implements AuthenticationEventExecutionPlanConfigurer {

    @Resource
    private CasConfigurationProperties casProperties;

    @Resource
    private ServicesManager servicesManager;

    @Bean
    public AuthenticationHandler myAuthenticationHandler() {
        // 参数: name, servicesManager, principalFactory, order
        // 定义为优先使用它进行认证
        // return new CustomUsernamePasswordAuthentication(CustomUsernamePasswordAuthentication.class.getName(), servicesManager, new DefaultPrincipalFactory(), 1);

        return new CustomHandlerAuthentication(CustomHandlerAuthentication.class.getName(), servicesManager, new DefaultPrincipalFactory(), 1);
    }

    @Override
    public void configureAuthenticationExecutionPlan(final AuthenticationEventExecutionPlan plan) {
        plan.registerAuthenticationHandler(myAuthenticationHandler());
    }
}