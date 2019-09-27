package com.dhcc.sso.config;

import org.apereo.cas.configuration.CasConfigurationProperties;
import org.apereo.cas.web.flow.CasWebflowConfigurer;
import org.apereo.cas.web.flow.CasWebflowExecutionPlan;
import org.apereo.cas.web.flow.CasWebflowExecutionPlanConfigurer;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.webflow.definition.registry.FlowDefinitionRegistry;
import org.springframework.webflow.engine.builder.support.FlowBuilderServices;

import javax.annotation.Resource;

@Configuration
@EnableConfigurationProperties(CasConfigurationProperties.class)
public class CustomAuthWebflowConfigurer implements CasWebflowExecutionPlanConfigurer {
    @Resource
    private CasConfigurationProperties casProperties;

    @Resource
    private FlowDefinitionRegistry loginFlowDefinationRegistry;

    @Resource
    private ApplicationContext applicationContext;

    @Resource
    private FlowBuilderServices flowBuilderServices;

    @Bean
    public CasWebflowConfigurer customWebflowConfigurer() {
        // 实例化自定义的表单配置类
        final CustomWebflowConfigurer cwc = new CustomWebflowConfigurer(flowBuilderServices, loginFlowDefinationRegistry, applicationContext, casProperties);

        // 初始化
        cwc.initialize();

        return cwc;
    }

    @Override
    public void configureWebflowExecutionPlan(CasWebflowExecutionPlan plan) {
        plan.registerWebflowConfigurer(customWebflowConfigurer());
    }
}
