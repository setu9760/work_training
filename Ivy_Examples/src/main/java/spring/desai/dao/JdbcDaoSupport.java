package spring.desai.dao;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;

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

	@Autowired
	protected DataSource dataSource;

	@PostConstruct
	public void init() {
		setDataSource(dataSource);
	}

	protected final void logSql(String sql) {
		if (logger.isInfoEnabled())
			logger.info("SQL call: " + sql);
	}
}
