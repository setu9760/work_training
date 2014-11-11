package com.desai.java.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.RowMapper;

import com.desai.java.Student;
import com.desai.java.Subject;
import com.desai.java.Tutor;
import com.desai.java.RowMappers.StudentRowMapper;
import com.desai.java.RowMappers.SubjectRowMapper;
import com.desai.java.RowMappers.TutorRowMapper;

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
