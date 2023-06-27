package com.arcesi.cliniquemedical.security.exceptions.handlers;

import java.util.Map;

import com.arcesi.cliniquemedical.security.enums.ErrorsCodeUserRole;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorsDTOMessage {

	private Integer httpCode;
	private ErrorsCodeUserRole codeEnum;
	private String message;
	private Map<String, String> lstErrors;
	private String timeStamp;

}
