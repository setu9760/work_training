package desai.java.config;

import desai.java.Student;
import desai.java.Subject;
import desai.java.Tutor;
import desai.java.RowMappers.StudentRowMapper;
import desai.java.RowMappers.SubjectRowMapper;
import desai.java.RowMappers.TutorRowMapper;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.RowMapper;

@Configuration
public class RowMapperConfig {

	@Bean
	public RowMapper<Student> getStudentMapper() {
		return new StudentRowMapper();
	}

	@Bean
	public RowMapper<Subject> getSubjectMapper() {
		return new SubjectRowMapper();
	}

	@Bean
	public RowMapper<Tutor> getTutorMapper() {
		return new TutorRowMapper();
	}

}
