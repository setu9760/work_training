package main.com.desai.java.dao;

import groovy.sql.BatchingPreparedStatementWrapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import main.com.desai.java.Tutor;

public class JdbcTutorDaoImpl extends JdbcDaoSupport implements TutorDao {

	// @Autowired
	// @Qualifier("datasource")
	// DataSource datasource;
	Connection conn;

	// @PostConstruct
	// private void initialize() {
	// setDataSource(datasource);
	// }

	public JdbcTutorDaoImpl(DataSource datasource) {
		// this.datasource = datasource;
		setDataSource(datasource);
	}

	@Override
	public void insert(Tutor tutor) {
		String sql = "INSERT INTO TUTOR (id, name) VALUES (?, ?)";
		getJdbcTemplate().update(sql,
				new Object[] { tutor.getId(), tutor.getName() });

	}

	@Override
	public Tutor findTutorById(int tutor_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Tutor findTutorByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

}
