package com.arcesi.cliniquemedical.security.enums;

public enum ErrorsCodeUserRole {

	USER_NOT_FOUND(1000), 
	USER_NOT_VALIDE(1001), 
	
	ROLE_NOT_FOUND(2000), 
	ROLE_NOT_VALIDE(2001),
	
	PARAMETRE_NOT_VALID(3000),
	
	USER_EMAIL_EXISTS(3001),
	USER_EMAIL_NOT_VALIDE(3002),
	USER_PASSWORD_TOKEN(3003),
	USER_EMAIL_PASSWORD_NOT_VALID(3002);
	
	

	public final int code;

	private ErrorsCodeUserRole(final int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}
}
