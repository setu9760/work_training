package spring.desai.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import spring.desai.dao.StudentDao;
import spring.desai.dao.SubjectDao;
import spring.desai.dao.TutorDao;
import spring.desai.dao.JdbcDaoImpl.JdbcStudentDaoImpl;
import spring.desai.dao.JdbcDaoImpl.JdbcSubjectDaoImpl;
import spring.desai.dao.JdbcDaoImpl.JdbcTutorDaoImpl;

@Configuration
@Import({ PojoBeansConfig.class, RowMapperConfig.class})
// @ImportResource("spring-beans.xml")
public class Config {

	private final String DRIVER_CLASSNAME = Config_properties
			.getString("Config.class_name");

	private final String URL = Config_properties.getString("Config.db_url");

	private final String USERNAME = Config_properties
			.getString("Config.username");

	private final String PASSWORD = Config_properties
			.getString("Config.password");

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

	@Bean(name = "subjectDao")
	public SubjectDao getSubjectDao() {
		JdbcSubjectDaoImpl subjectDao = new JdbcSubjectDaoImpl(getDatasource());
		return subjectDao;
	}

	@Bean(name = "dataSource")
	public DriverManagerDataSource getDatasource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(DRIVER_CLASSNAME);
		dataSource.setUrl(URL);
		dataSource.setUsername(USERNAME);
		dataSource.setPassword(PASSWORD);
		return dataSource;
	}

	/**
	 * Not Implemented yet
	 * 
	 * @return
	 */
	// @Bean
	// public PropertySourcesPlaceholderConfigurer getProperties() {
	// PropertySourcesPlaceholderConfigurer properties = new
	// PropertySourcesPlaceholderConfigurer();
	// Resource[] resources = new ClassPathResource[] { new ClassPathResource(
	// "") };
	// properties.setLocations(resources);
	// properties.setIgnoreUnresolvablePlaceholders(true);
	// return properties;
	// }
}
