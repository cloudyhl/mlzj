package com.mlzj.international.config;

import com.mlzj.international.utils.I18nMessageUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@Configuration
public class MessageSourceAutoConfiguration {
    @Value("${spring.messages.basename}")
    private String path;

    @Bean("messageSource")
    @ConditionalOnMissingBean
    public ReloadableResourceBundleMessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasenames(path);
        messageSource.setDefaultEncoding("UTF-8");
        I18nMessageUtils.setMessageSource(messageSource);
        return messageSource;
    }
}
