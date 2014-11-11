package com.desai.java.dao.JdbcDaoImpl;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.desai.java.Subject;
import com.desai.java.Tutor;
import com.desai.java.RowMappers.SubjectRowMapper;
import com.desai.java.dao.SubjectDao;

public class JdbcSubjectDaoImpl extends JdbcDaoSupport implements SubjectDao {

	public JdbcSubjectDaoImpl(DataSource dataSource) {
		setDataSource(dataSource);
	}

	@Override
	public void insert(Subject subject) {
		String sql = "INSERT INTO subject (subject_name) VALUES (?)";
		getJdbcTemplate().update(sql,
				new Object[] { subject.getSubject_name() });
	}

	@Override
	public Object findById(int id) {
		String sql = "SELECT * FROM subject WHERE subject_id = ? ";
		Subject subject = getJdbcTemplate().queryForObject(sql,
				new Object[] { id }, new SubjectRowMapper());
		return subject;
	}

	@Override
	public Subject findByName(String name) {
		String sql = "SELECT * FROM subject WHERE subject_name = ? ";
		Subject subject = getJdbcTemplate().queryForObject(sql,
				new Object[] { name }, new SubjectRowMapper());
		return subject;
	}

	@Override
	public int countAll() {
		String sql = "SELECT COUNT(*) FROM subject";
		int count = getJdbcTemplate().queryForObject(sql, Integer.class);
		return count;
	}

	@Override
	public boolean dropById(int id) {
		String sql = "DELETE FROM subject WHERE subject_id = ? ";
		int rowNum = getJdbcTemplate().queryForObject(sql, new Object[] { id },
				Integer.class);
		return rowNum == 0;
	}

	@Override
	public List<Tutor> findAllTutorsForSubject(int subject_id) {
		//String sql = "";
		return null;
	}

}
