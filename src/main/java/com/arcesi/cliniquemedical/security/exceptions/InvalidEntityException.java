package com.arcesi.cliniquemedical.security.exceptions;

import java.util.Map;

import com.arcesi.cliniquemedical.security.enums.ErrorsCodeUserRole;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InvalidEntityException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private ErrorsCodeUserRole errorEnum;
	private Map<String, String> lstErrors;

	public InvalidEntityException(String message, Throwable cause) {
		super(message, cause);

	}

	public InvalidEntityException(String message) {
		super(message);

	}

	public InvalidEntityException(String message, Throwable cause, ErrorsCodeUserRole errosEnum) {
		super(message, cause);
		this.errorEnum = errosEnum;
	}

	public InvalidEntityException(String message, ErrorsCodeUserRole errosEnum, Map<String, String> errors) {
		super(message);
		this.errorEnum = errosEnum;
		this.lstErrors = errors;
	}

	public InvalidEntityException(String message, ErrorsCodeUserRole errosEnum) {
		super(message);
		this.errorEnum = errosEnum;
	}
}
