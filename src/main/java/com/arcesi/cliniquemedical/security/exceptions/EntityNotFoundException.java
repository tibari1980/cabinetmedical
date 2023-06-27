package com.arcesi.cliniquemedical.security.exceptions;

import com.arcesi.cliniquemedical.security.enums.ErrorsCodeUserRole;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EntityNotFoundException extends RuntimeException {
	private static final long serialVersionUID = -7872230957054420044L;

	private ErrorsCodeUserRole codeErrors;

	public EntityNotFoundException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public EntityNotFoundException(String message) {
		super(message);

	}

	public EntityNotFoundException(String message, ErrorsCodeUserRole codeErrors) {
		super(message);
		this.codeErrors = codeErrors;
	}


}
