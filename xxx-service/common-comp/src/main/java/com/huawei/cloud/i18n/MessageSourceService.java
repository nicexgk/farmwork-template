package com.huawei.cloud.i18n;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContext;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Locale;

@Component
public class MessageSourceService {

    @Resource
    private MessageSource messageSource;
    private final Logger logger = LoggerFactory.getLogger(MessageSourceService.class);

    public String getMessage(String code, Object[] args, Locale locale) {
        String message = null;
        try {
            message = messageSource.getMessage(code, args, locale);
        } catch (NoSuchMessageException e) {
            logger.error(e.getMessage());
        }
        return message;
    }

    public String getMessage(String code, Object[] args) {
        Locale locale = LocaleContextHolder.getLocale();
        return getMessage(code, args, locale);
    }

    public String getMessage(String code) {
        return getMessage(code, null);
    }

}
