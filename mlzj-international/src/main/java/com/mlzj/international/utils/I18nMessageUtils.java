package com.mlzj.international.utils;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

/**
 * @author yhl
 * @date 2022/9/6
 */
public class I18nMessageUtils {

    private static MessageSource messageSource;

    public static void setMessageSource (MessageSource inputMessageSource) {
        messageSource = inputMessageSource;
    }
    /**
     * 获取信息
     * @param code 编码
     * @return 国际化消息
     */
    public static String message(String code) {
        return messageSource.getMessage(code, null, LocaleContextHolder.getLocale());
    }
}
