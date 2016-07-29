package com.concrete.authentication.json;

public class FieldErrorJson {

	private String field;

	private String message;

	public FieldErrorJson(String field, String message) {
	        this.field = field;
	        this.message = message;
	}

	public FieldErrorJson() {
		super();
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	

}
