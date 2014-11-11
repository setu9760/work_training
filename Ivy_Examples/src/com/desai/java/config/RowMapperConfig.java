package com.desai.java.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.desai.java.RowMappers.StudentRowMapper;
import com.desai.java.RowMappers.SubjectRowMapper;
import com.desai.java.RowMappers.TutorRowMapper;

@Configuration
public class RowMapperConfig {

	@Bean
	public StudentRowMapper getStudentMapper() {
		return new StudentRowMapper();
	}

	@Bean
	public SubjectRowMapper getSubjectMapper() {
		return new SubjectRowMapper();
	}

	@Bean
	public TutorRowMapper getTutorMapper() {
		return new TutorRowMapper();
	}

}
