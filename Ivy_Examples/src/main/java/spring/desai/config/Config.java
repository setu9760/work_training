package spring.desai.config;

import javax.naming.NamingException;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.jndi.JndiTemplate;

import spring.desai.dao.StudentDao;
import spring.desai.dao.SubjectDao;
import spring.desai.dao.TutorDao;
import spring.desai.dao.JdbcDaoImpl.JdbcStudentDaoImpl;
import spring.desai.dao.JdbcDaoImpl.JdbcSubjectDaoImpl;
import spring.desai.dao.JdbcDaoImpl.JdbcTutorDaoImpl;

@Configuration
@Import({ PojoBeansConfig.class, RowMapperConfig.class })
@ImportResource({ "classpath:spring-beans.xml" })
public class Config {
	
	private final String DATASOURCE_JNDI = Config_properties
			.getString("Config.datasource.jndi");

	@Bean(name = "dataSource")
	public DataSource getDatasource() throws NamingException {
		JndiTemplate jndiTemplate = new JndiTemplate();
		DataSource dataSource = (DataSource) jndiTemplate
				.lookup(DATASOURCE_JNDI);
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

}
