package spring.desai.pojo.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import spring.desai.pojo.Student;

@Component
public class StudentValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Student.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "student_id",
				"id.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "student_name",
				"name.required");
		Student student = (Student) target;
		if (student.getStudent_age() < 18) {
			errors.rejectValue("student_age", "age.too.low");
		} else if (student.getStudent_age() > 65) {
			errors.rejectValue("student_age", "age.too.high");
		}
	}
}
