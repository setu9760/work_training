package spring.desai.pojo.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import spring.desai.pojo.Subject;

public class SubjectValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Subject.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "subject_id", 
				"id.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "subject_name",
				"name.required");
	}

}
