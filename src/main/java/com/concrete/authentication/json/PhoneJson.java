package com.concrete.authentication.json;

import com.concrete.authentication.domain.Phone;

public class PhoneJson {

	private final Integer ddd;
	private final String number;
	
	public PhoneJson(Phone phone) {
		super();
		this.ddd = phone.getDdd();
		this.number = phone.getNumber();
	}

	public String getNumber() {
		return number;
	}

	public Integer getDdd() {
		return ddd;
	}
	
	
}
