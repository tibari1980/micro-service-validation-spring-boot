package com.arcesi.gestionusers.exceptions.handlers;

import java.time.format.DateTimeFormatter;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.arcesi.gestionusers.exceptions.EntityNotFoundException;
import com.arcesi.gestionusers.exceptions.InvalidEntityException;
import com.arcesi.gestionusers.utils.IUtils;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	DateTimeFormatter format = DateTimeFormatter.ofPattern("HHmm, dd MMM uuuu");

	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<ErrorsDTO> exceptionHandler(EntityNotFoundException exception, WebRequest request) {
		final HttpStatus httpStatusNotFound = HttpStatus.NOT_FOUND;
		ErrorsDTO dto = ErrorsDTO.builder().codeEnum(exception.getCodeErrors()).httpCode(httpStatusNotFound.value())
				.message(exception.getMessage()).timeStamp(IUtils.afficheDateFormatter()).build();

		return new ResponseEntity<ErrorsDTO>(dto, httpStatusNotFound);
	}

	@ExceptionHandler(InvalidEntityException.class)
	public ResponseEntity<ErrorsDTO> exceptionHandler(InvalidEntityException exception, WebRequest request) {
		final HttpStatus badhHttpStatus = HttpStatus.BAD_REQUEST;
		ErrorsDTO dto = ErrorsDTO.builder().codeEnum(exception.getErrorEnum()).httpCode(badhHttpStatus.value())
				.message(exception.getMessage()).lstErrors(exception.getLstErrors())
				.timeStamp(IUtils.afficheDateFormatter()).build();
		return new ResponseEntity<ErrorsDTO>(dto, badhHttpStatus);
	}
}
