package com.concrete.authentication.json;

import java.util.ArrayList;
import java.util.List;

import org.springframework.validation.FieldError;

import com.concrete.authentication.service.MessageResolver;

public class ValidationErrorJson {

	 
	    private List<FieldErrorJson> fieldErrors = new ArrayList<>();
	 
	    public ValidationErrorJson() {
	    	super();
	    }
	 
	    public void addFieldError(String path, String message) {
	    	FieldErrorJson error = new FieldErrorJson(path, message);
	        fieldErrors.add(error);
	    }

		public List<FieldErrorJson> getFieldErrors() {
			return fieldErrors;
		}

		public void setFieldErrors(List<FieldErrorJson> fieldErrors) {
			this.fieldErrors = fieldErrors;
		}
		
		public static ValidationErrorJson processFieldErrors(List<FieldError> fieldErrors, MessageResolver messageResolver) {
	        ValidationErrorJson validationErroJson = new ValidationErrorJson();
	 
	        for (FieldError fieldError: fieldErrors) {
	            String localizedErrorMessage = messageResolver.resolveLocalizedErrorMessage(fieldError);
	            validationErroJson.addFieldError(fieldError.getField(), localizedErrorMessage);
	        }
	 
	        return validationErroJson;
	    }	 	    
	
}
