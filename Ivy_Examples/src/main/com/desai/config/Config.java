package main.com.desai.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import main.com.desai.java.Tutor;
import main.com.desai.java.dao.*;
import main.com.desai.java.dao.JdbcDaoImpl.JdbcStudentDaoImpl;
import main.com.desai.java.dao.JdbcDaoImpl.JdbcTutorDaoImpl;

@Configuration
public class Config {

	@Value("org.springframework.jdbc.datasource.DriverManagerDataSource")
	private String DRIVER_CLASSNAME;

	@Value("jdbc:mysql://localhost:3306/test")
	private String URL;

	@Value("setu")
	private String USERNAME;

	@Value("password")
	private String PASSWORD;

	@Bean(name = "studentDao")
	public StudentDao getStudentDao() {
		JdbcStudentDaoImpl studentDAO = new JdbcStudentDaoImpl(getDatasource());
		return studentDAO;
	}

	@Bean(name = "tutorDao")
	public TutorDao getTutorDao() {
		JdbcTutorDaoImpl tutorDao = new JdbcTutorDaoImpl(getDatasource());
		return tutorDao;
	}

	@Bean(name = "datasource")
	public DriverManagerDataSource getDatasource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(DRIVER_CLASSNAME);
		dataSource.setUrl(URL);
		dataSource.setUsername(USERNAME);
		dataSource.setPassword(PASSWORD);
		return dataSource;
	}

	@Bean(name = "tutorBean")
	public Tutor getTutorBean() {
		Tutor tutor = new Tutor();
		tutor.setName("setu");
		tutor.setId(1);
		return tutor;
	}
}
