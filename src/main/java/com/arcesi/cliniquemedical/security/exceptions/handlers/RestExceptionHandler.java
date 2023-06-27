package com.arcesi.cliniquemedical.security.exceptions.handlers;

import java.time.Instant;
import java.time.format.DateTimeFormatter;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.arcesi.cliniquemedical.security.enums.ErrorsCodeUserRole;
import com.arcesi.cliniquemedical.security.exceptions.EntityNotFoundException;
import com.arcesi.cliniquemedical.security.exceptions.InvalidEntityException;

import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class RestExceptionHandler {
	DateTimeFormatter format = DateTimeFormatter.ofPattern("HHmm, dd MMM uuuu");

	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<ErrorsNotFoundDTO> exceptionHandler(EntityNotFoundException exception, WebRequest request) {
		final HttpStatus httpStatusNotFound = HttpStatus.NOT_FOUND;
		ErrorsNotFoundDTO dto = ErrorsNotFoundDTO.builder().codeEnum(exception.getCodeErrors())
				.httpCode(httpStatusNotFound.value()).message(exception.getMessage())
				.timeStamp(Instant.now().toString()).build();

		return new ResponseEntity<ErrorsNotFoundDTO>(dto, httpStatusNotFound);
	}

	@ExceptionHandler(InvalidEntityException.class)
	public ResponseEntity<ErrorsDTOMessage> exceptionHandler(InvalidEntityException exception, WebRequest request) {
		final HttpStatus badhHttpStatus = HttpStatus.BAD_REQUEST;
		ErrorsDTOMessage dto = ErrorsDTOMessage.builder().codeEnum(exception.getErrorEnum())
				.httpCode(badhHttpStatus.value()).message(exception.getMessage()).lstErrors(exception.getLstErrors())
				.lstErrors(exception.getLstErrors()).timeStamp(Instant.now().toString()).build();

		return new ResponseEntity<ErrorsDTOMessage>(dto, badhHttpStatus);
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<ErrorsUserDTO> exceptionHandler(ConstraintViolationException exception, WebRequest request) {
		final HttpStatus badhHttpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		ErrorsUserDTO dto = ErrorsUserDTO.builder().httpCode(badhHttpStatus.value())
				.codeEnum(ErrorsCodeUserRole.PARAMETRE_NOT_VALID).message(exception.getMessage())
				.timeStamp(Instant.now().toString()).build();

		return new ResponseEntity<ErrorsUserDTO>(dto, badhHttpStatus);
	}

}
