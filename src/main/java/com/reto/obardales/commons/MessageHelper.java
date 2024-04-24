package com.reto.obardales.commons;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
public class MessageHelper {

    private final MessageSource messageSource;

    public MessageHelper(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public String get(String key) {
        return this.messageSource.getMessage(key, null, LocaleContextHolder.getLocale());
    }

}
