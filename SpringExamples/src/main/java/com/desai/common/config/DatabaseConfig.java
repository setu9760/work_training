package com.desai.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.desai.common.database.JdbcStudentDAO;
import com.desai.common.database.Student;
import com.desai.common.database.StudentDAO;

@Configuration
public class DatabaseConfig {

	@Value("org.springframework.jdbc.datasource.DriverManagerDataSource")
	private String DRIVER_CLASSNAME;

	@Value("jdbc:mysql://localhost:3306/test")
	private String URL;

	@Value("setu")
	private String USERNAME;

	@Value("password")
	private String PASSWORD;

	@Bean(name = "studentDao")
	public StudentDAO getStudentDao() {
		JdbcStudentDAO studentDAO = new JdbcStudentDAO(getDatasource());
		// studentDAO.setDataSource(getDatasource());
		return studentDAO;
	}

	@Bean(name = "studentBean")
	public Student getStudent() {
		return new Student(20, "abcd", 23);
	}

	private DriverManagerDataSource getDatasource() {
		DriverManagerDataSource datasource = new DriverManagerDataSource();
		datasource.setDriverClassName(DRIVER_CLASSNAME);
		datasource.setUrl(URL);
		datasource.setUsername(USERNAME);
		datasource.setPassword(PASSWORD);
		return datasource;
	}
}
