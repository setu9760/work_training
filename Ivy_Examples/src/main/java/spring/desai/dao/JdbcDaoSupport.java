package spring.desai.dao;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;

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
