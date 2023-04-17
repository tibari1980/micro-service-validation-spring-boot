package com.arcesi.gestionusers.exceptions;

import java.util.Set;

import com.arcesi.gestionusers.enums.ErrorsCodeEnumeration;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InvalidEntityException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private ErrorsCodeEnumeration errorEnum;
	private Set<String> lstErrors;

	public InvalidEntityException(String message, Throwable cause) {
		super(message, cause);

	}

	public InvalidEntityException(String message) {
		super(message);

	}

	public InvalidEntityException(String message, Throwable cause, ErrorsCodeEnumeration errosEnum) {
		super(message, cause);
		this.errorEnum = errosEnum;
	}

	public InvalidEntityException(String message, ErrorsCodeEnumeration errosEnum, Set<String> errors) {
		super(message);
		this.errorEnum = errosEnum;
		this.lstErrors = errors;
	}

	public InvalidEntityException(String message, ErrorsCodeEnumeration errosEnum) {
		super(message);
		this.errorEnum = errosEnum;
	}
}
