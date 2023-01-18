package com.demo.mybatis.advice;

import org.springframework.dao.DuplicateKeyException;

public class UniqeUserNameException extends DuplicateKeyException {

	private static final long serialVersionUID = 1L;
	private String defaultMessage;
	private String field;
	private String invalidValue;

	public UniqeUserNameException(String msg) {
		super(msg);
		defaultMessage = msg;
	}

	public UniqeUserNameException(String msg, String field, String invalidValue) {
		super(msg);
		this.defaultMessage = msg;
		this.field = field;
		this.invalidValue = invalidValue;
	}

	public String getDefaultMessage() {
		return defaultMessage;
	}
	public String getField() {
		return field;
	}
	public String getInvalidValue() {
		return invalidValue;
	}
	
	

}
