package org.sam.springboot.form.app.validation;

import org.springframework.util.StringUtils;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class RequeridoValidador implements ConstraintValidator<Requerido, String> {

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		
		//return !(value == null || value.isEmpty() || value.isBlank());
		return !(value == null || !StringUtils.hasText(value));
	}

}
