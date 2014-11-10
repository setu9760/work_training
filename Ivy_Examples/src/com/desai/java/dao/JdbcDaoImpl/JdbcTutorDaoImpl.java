package com.desai.java.dao.JdbcDaoImpl;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.desai.java.Student;
import com.desai.java.Tutor;
import com.desai.java.RowMappers.TutorRowMapper;
import com.desai.java.dao.TutorDao;


public class JdbcTutorDaoImpl extends JdbcDaoSupport implements TutorDao {

	public JdbcTutorDaoImpl(DataSource datasource) {
		setDataSource(datasource);
	}

	@Override
	public void insert(Tutor tutor) {
		String sql = "INSERT INTO TUTOR (id, name) VALUES (?, ?)";
		getJdbcTemplate().update(sql,
				new Object[] { tutor.getId(), tutor.getName() });

	}

	@Override
	public Tutor findById(int tutor_id) {
		String sql = "SELECT * FROM TUTOR WHERE ID = ?";
		Tutor tutor = getJdbcTemplate().queryForObject(sql,
				new Object[] { tutor_id }, new TutorRowMapper());
		return tutor;
	}

	@Override
	public Tutor findByName(String name) {
		String sql = "SELECT * FROM TUTOR WHERE NAME = ?";
		Tutor tutor = getJdbcTemplate().queryForObject(sql,
				new Object[] { name }, new TutorRowMapper());
		return tutor;
	}

	@Override
	public int countAll() {
		String sql = "SELECT COUNT(*) FROM TUTOR";
		int count = getJdbcTemplate().queryForObject(sql, Integer.class);
		return count;
	}

	@Override
	public boolean dropAll() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Student> findAllStudents(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
