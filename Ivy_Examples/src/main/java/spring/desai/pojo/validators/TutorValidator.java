package spring.desai.pojo.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import spring.desai.pojo.Tutor;

public class TutorValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Tutor.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "tutor_id",
				"id.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "tutor_name", 
				"name.required");
	}
}
