package spring.desai.pojo;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class StudentValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Student.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Student student = (Student) target;
		if (student.getName().equalsIgnoreCase("")) {
			errors.rejectValue("name", "required");
		}
	}

}
