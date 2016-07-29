package com.concrete.authentication.service;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;

@Component
public class MessageResolver {
	
	@Autowired
	private MessageSource messageSource;

	public String resolveLocalizedErrorMessage(FieldError fieldError) {
        Locale currentLocale =  LocaleContextHolder.getLocale();
        String localizedErrorMessage = messageSource.getMessage (fieldError.getCode(), null,currentLocale);
        
 
        return localizedErrorMessage;
    }

}
