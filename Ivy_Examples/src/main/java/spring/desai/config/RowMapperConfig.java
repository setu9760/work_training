package spring.desai.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.RowMapper;

import spring.desai.RowMappers.StudentRowMapper;
import spring.desai.RowMappers.SubjectRowMapper;
import spring.desai.RowMappers.TutorRowMapper;
import spring.desai.pojo.Student;
import spring.desai.pojo.Subject;
import spring.desai.pojo.Tutor;

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
