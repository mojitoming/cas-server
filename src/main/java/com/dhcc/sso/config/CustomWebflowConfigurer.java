package com.dhcc.sso.config;

import com.dhcc.sso.entity.CustomCredential;
import org.apereo.cas.configuration.CasConfigurationProperties;
import org.apereo.cas.web.flow.CasWebflowConstants;
import org.apereo.cas.web.flow.configurer.AbstractCasWebflowConfigurer;
import org.springframework.context.ApplicationContext;
import org.springframework.webflow.definition.registry.FlowDefinitionRegistry;
import org.springframework.webflow.engine.Flow;
import org.springframework.webflow.engine.ViewState;
import org.springframework.webflow.engine.builder.BinderConfiguration;
import org.springframework.webflow.engine.builder.support.FlowBuilderServices;

public class CustomWebflowConfigurer extends AbstractCasWebflowConfigurer {

    CustomWebflowConfigurer(FlowBuilderServices flowBuilderServices,
                            FlowDefinitionRegistry loginFlowDefinitionRegistry,
                            ApplicationContext applicationContext,
                            CasConfigurationProperties casProperties) {
        super(flowBuilderServices, loginFlowDefinitionRegistry, applicationContext, casProperties);
    }

    @Override
    protected void doInitialize() {
        final Flow flow = super.getLoginFlow();
        bindCredential(flow);
    }

    /**
     * Annotation:
     * 绑定自定义的 Credential 信息
     *
     * @Author: Adam Ming
     * @Date: Sep 27, 2019 at 5:50:16 PM
     */
    private void bindCredential(Flow flow) {
        // 重写绑定自定义 credential
        createFlowVariable(flow, CasWebflowConstants.VAR_ID_CREDENTIAL, CustomCredential.class);

        // 登录页绑定新参数
        final ViewState state = (ViewState) flow.getState(CasWebflowConstants.STATE_ID_VIEW_LOGIN_FORM);
        final BinderConfiguration cfg = getViewStateBinderConfiguration(state);

        // 用户名、密码已经绑定，只需要对新加入系统参数绑定即可
        // 字段名、转换器、是否必须
        cfg.addBinding(new BinderConfiguration.Binding("captcha", null, true));
    }
}
