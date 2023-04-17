package com.arcesi.gestionusers.enums;

public enum ErrorsCodeEnumeration {
	
	USER_NOT_FOUND(1000),
	
	USER_NOT_VALID(1001),
	
	USER_PARAM_ID_USER_NOT_VALID(1002);
	private final int code;
	
	private  ErrorsCodeEnumeration(final int code) {
		this.code=code;
	}
	public int getCode() {
		return code;
	}

}
