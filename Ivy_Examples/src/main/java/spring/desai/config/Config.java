package spring.desai.config;

import javax.naming.NamingException;
import javax.sql.DataSource;

import org.joda.time.DateTime;
import org.joda.time.LocalDateTime;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jndi.JndiTemplate;

import spring.desai.dao.StudentDao;
import spring.desai.dao.SubjectDao;
import spring.desai.dao.TutorDao;
import spring.desai.dao.JdbcDaoImpl.JdbcStudentDaoImpl;
import spring.desai.dao.JdbcDaoImpl.JdbcSubjectDaoImpl;
import spring.desai.dao.JdbcDaoImpl.JdbcTutorDaoImpl;

@Configuration
@ImportResource({ "classpath:spring-beans.xml" })
@Import({ PojoBeansConfig.class, RowMapperConfig.class })
public class Config {

	private final String DATASOURCE_JNDI = Config_properties
			.getString("Config.datasource.jndi");

	private final String DATASOURCE2_JNDI = Config_properties
			.getString("Config.datasource2.jndi");

	@Bean(name = "dataSource")
	public DataSource getDatasource() throws NamingException {
		JndiTemplate jndiTemplate = new JndiTemplate();
		DataSource dataSource = (DataSource) jndiTemplate
				.lookup(DATASOURCE_JNDI);
		return dataSource;
	}

	@Bean(name = "transactionManager")
	public DataSourceTransactionManager getDataSourceTransactionManager()
			throws NamingException {
		return new DataSourceTransactionManager(getDatasource());
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

	/************************
	 * 
	 * 
	 *************************/

	// @Bean(name = "dataSource2")
	// public DataSource getDatasource2() throws NamingException {
	// JndiTemplate jndiTemplate = new JndiTemplate();
	// DataSource dataSource = (DataSource) jndiTemplate
	// .lookup(DATASOURCE2_JNDI);
	// return dataSource;
	// }

}
