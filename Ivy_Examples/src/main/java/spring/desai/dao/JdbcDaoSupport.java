package spring.desai.dao;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;

import spring.desai.pojo.Student;
import spring.desai.pojo.Subject;
import spring.desai.pojo.Tutor;

/**
 * Convenient super class for JDBC-based data access objects.
 * 
 * <p>
 * Requires a {@link javax.sql.DataSource} to be set, providing a
 * {@link org.springframework.jdbc.core.JdbcTemplate} based on it to subclasses
 * through the {@link #getJdbcTemplate()} method.
 * 
 * @see #setDataSource
 * @see #getJdbcTemplate
 * @see org.springframework.jdbc.core.support.JdbcDaoSupport
 * @see org.springframework.jdbc.core.JdbcTemplate
 */
public abstract class JdbcDaoSupport extends
		org.springframework.jdbc.core.support.JdbcDaoSupport {

	private static final Logger logger = Logger.getLogger(JdbcDaoSupport.class);

	private static final Logger sqlLogger = Logger.getLogger("jdbcdaolog");

	@Autowired
	protected DataSource dataSource;

	@Autowired
	protected RowMapper<Student> studentMapper;

	@Autowired
	protected RowMapper<Subject> subjectMapper;

	@Autowired
	protected RowMapper<Tutor> tutorMapper;

	@PostConstruct
	public void init() {
		setDataSource(dataSource);
	}

	protected final void logSql(String sql) {
		sqlLogger.debug("SQL call: " + sql);
	}

	protected final void log(Level level, String message, Throwable t) {
		if (logger.getLevel().equals(level)) {
			logger.log(level, message, t);
		}
	}

	protected final void log(Level level, String message) {
		if (logger.getLevel().equals(level)) {
			logger.log(level, message);
		}
	}
}
