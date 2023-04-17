package com.arcesi.gestionusers.validators;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
@Component
public class ObjectValidator {



	@Autowired
	 private Validator validator;
	 
	

	public Set<String> validate(Object objectToValidate) {
		Set<ConstraintViolation<Object>> violations = validator.validate(objectToValidate);
		if (!violations.isEmpty()) {
			return violations.stream().map(ConstraintViolation::getMessage).collect(Collectors.toSet());
		}
		return Collections.emptySet();
	}
}
