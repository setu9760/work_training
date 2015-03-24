package spring.desai.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.Validator;

import spring.desai.pojo.Student;
import spring.desai.pojo.Subject;
import spring.desai.pojo.Tutor;
import spring.desai.pojo.validators.StudentValidator;
import spring.desai.pojo.validators.SubjectValidator;
import spring.desai.pojo.validators.TutorValidator;
import spring.desai.utils.GuidGeneratorException;

@Configuration
public class PojoBeansConfig {

	@Bean(name = "studentBean")
	public Student getStudentBean() {
		try {
			return new Student("student 1", 20);
		} catch (GuidGeneratorException e) {
			return null;
		}
	}

	@Bean(name = "tutorBean")
	public Tutor getTutorBean() {

		try {
			return new Tutor("tutor 1", getSubjectBean());
		} catch (GuidGeneratorException e) {
			return null;
		}
	}

	@Bean(name = "subjectBean")
	public Subject getSubjectBean() {

		try {
			return new Subject("subject 1");
		} catch (GuidGeneratorException e) {
			return null;
		}
	}

	@Bean(name = "studentValidator")
	public Validator getStudentValudatior() {
		return new StudentValidator();
	}

	@Bean(name = "tutorValidator")
	public Validator getTutorValidator() {
		return new TutorValidator();
	}

	@Bean(name = "subjectValidator")
	public Validator getSubjectValidator() {
		return new SubjectValidator();
	}
}
