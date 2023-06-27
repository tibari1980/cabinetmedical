package com.arcesi.cliniquemedical.security.validatorssecurity;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;

@Component
public class ValidatorsUserRole<T> {
	@Autowired
	private Validator validator;

	public Map<String, String> validate(T objectToValidate) {
		Set<ConstraintViolation<T>> violations = validator.validate(objectToValidate);
		if (!violations.isEmpty()) {
			final Map<String, String> mapsErrors = new HashMap<>();
			for (ConstraintViolation<T> constraint : violations) {
				String message = constraint.getMessageTemplate();
				String fieldName = constraint.getPropertyPath().toString();
				mapsErrors.put(fieldName, message);

			}
			return mapsErrors;
		}
		return Collections.emptyMap();
	}
}
