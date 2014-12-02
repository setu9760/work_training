package spring.desai.config;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Properties;

import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jndi.JndiTemplate;

import spring.desai.dao.StudentDao;
import spring.desai.dao.SubjectDao;
import spring.desai.dao.TutorDao;
import spring.desai.dao.JdbcDaoImpl.JdbcStudentDaoImpl;
import spring.desai.dao.JdbcDaoImpl.JdbcSubjectDaoImpl;
import spring.desai.dao.JdbcDaoImpl.JdbcTutorDaoImpl;

@Configuration
@Import({ PojoBeansConfig.class, RowMapperConfig.class })
// @ImportResource("spring-beans.xml")
public class Config {// implements InitializingBean {

	private static final Log log = LogFactory.getLog(Config.class);

	private final String DRIVER_CLASSNAME = Config_properties
			.getString("Config.class_name");

	private final String URL = Config_properties.getString("Config.db_url");

	private final String USERNAME = Config_properties
			.getString("Config.username");

	private final String PASSWORD = Config_properties
			.getString("Config.password");

	@Bean(name = "dataSource")
	public DataSource getDatasource() throws NamingException {
		JndiTemplate jndiTemplate = new JndiTemplate();
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(DRIVER_CLASSNAME);
		dataSource.setUrl(URL);
		dataSource.setUsername(USERNAME);
		dataSource.setPassword(PASSWORD);
		// return (DataSource)
		// jndiTemplate.lookup("java:/comp/env/jdbc/testDb");
		try {
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
		} catch (SQLException e) {
			log.error("driver not found", e);
		}
		return dataSource;
	}

	@Bean(name = "studentDao")
	public StudentDao getStudentDao() throws NamingException {
		JdbcStudentDaoImpl studentDAO = new JdbcStudentDaoImpl();
		return studentDAO;
	}

	@Bean(name = "tutorDao")
	public TutorDao getTutorDao() throws NamingException {
		JdbcTutorDaoImpl tutorDao = new JdbcTutorDaoImpl();
		return tutorDao;
	}

	@Bean(name = "subjectDao")
	public SubjectDao getSubjectDao() throws NamingException {
		JdbcSubjectDaoImpl subjectDao = new JdbcSubjectDaoImpl();
		return subjectDao;
	}

	@Bean(name = "messageSource")
	public ResourceBundleMessageSource getMessageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("messages");
		return messageSource;
	}

	// @Override
	// public void afterPropertiesSet() throws Exception {
	// log.info("afterPropertiesSet method");
	// Properties prop = System.getProperties();
	// Iterator<Object> i = prop.keySet().iterator();
	// while (i.hasNext()) {
	// Object key = i.next();
	// log.info(key + ":" + prop.get(key));
	// }
	// }

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
