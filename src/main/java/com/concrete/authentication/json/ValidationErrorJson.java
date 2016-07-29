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
		
		private  void add(MessageResolver messageResolver, FieldError error){
			String localizedErrorMessage = messageResolver.resolveLocalizedErrorMessage(error);
    		this.addFieldError(error.getField(), localizedErrorMessage);
		}
		
		public static ValidationErrorJson processFieldErrors(List<FieldError> fieldErrors, MessageResolver messageResolver) {
	        ValidationErrorJson validationErroJson = new ValidationErrorJson();
	        
	        fieldErrors.forEach((error) -> validationErroJson.add(messageResolver, error));
	 
	        return validationErroJson;
	    }	 	    
	
}
