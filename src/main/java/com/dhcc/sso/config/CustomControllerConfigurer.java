package com.dhcc.sso.config;

import com.dhcc.sso.controller.CaptchaController;
import org.apereo.cas.configuration.CasConfigurationProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(CasConfigurationProperties.class)
public class CustomControllerConfigurer {
    /**
     * Annotation:
     * 配置验证码，注入 bean 到 spring 中
     *
     * @Author: Adam Ming
     * @Date: Sep 27, 2019 at 5:01:05 PM
     */
    @Bean
    @ConditionalOnMissingBean(CaptchaController.class)
    public CaptchaController captchaController() {
        return new CaptchaController();
    }

    /**
     * Annotation:
     * 自定义 ServicesManager 管理配置，注入 bean 到 spring 中
     *
     * @Author: Adam Ming
     * @Date: Sep 27, 2019 at 5:06:48 PM
     */
    // TODO
}
