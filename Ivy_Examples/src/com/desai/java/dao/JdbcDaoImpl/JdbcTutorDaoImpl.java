package com.desai.java.dao.JdbcDaoImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.desai.java.Subject;
import com.desai.java.Tutor;
import com.desai.java.dao.SubjectDao;
import com.desai.java.dao.TutorDao;

public class JdbcTutorDaoImpl extends JdbcDaoSupport implements TutorDao {

	@Autowired
	private SubjectDao subjectDao;

	@Autowired
	private RowMapper<Tutor> tutorMapper;

	@Autowired
	private RowMapper<Subject> subjectMapper;

	public JdbcTutorDaoImpl(DataSource datasource) {
		setDataSource(datasource);
	}

	@Override
	public void insert(Tutor tutor) {
		String sql = "INSERT INTO TUTOR (name, subject_id) VALUES (?, ?)";
		getJdbcTemplate().update(
				sql,
				new Object[] { tutor.getName(),
						tutor.getSubject().getSubject_id() });

	}

	@Override
	public Tutor findById(int tutor_id) {
		String sql = "SELECT * FROM TUTOR WHERE ID = ?";
		Tutor tutor = getJdbcTemplate().queryForObject(sql,
				new Object[] { tutor_id }, tutorMapper);
		return tutor;
	}

	@Override
	public List<Tutor> findByName(String name) {
		String sql = "SELECT * FROM TUTOR WHERE NAME = ?";
		List<Tutor> tutors = getJdbcTemplate().query(sql,
				new Object[] { name }, tutorMapper);
		return tutors;
	}

	@Override
	public int countAll() {
		String sql = "SELECT COUNT(*) FROM TUTOR";
		int count = getJdbcTemplate().queryForObject(sql, Integer.class);
		return count;
	}

	@Override
	public boolean dropById(int id) {
		String sql = "DELETE FROM tutor WHERE id = ?";
		int numRows = getJdbcTemplate().update(sql, new Object[] { id });
		if (numRows == 0)
			return false;
		else
			return true;
	}

	@Override
	public Subject findSubjectOfTutor(int tutor_id) {
		String sql = "SELECT * FROM subject WHERE subject_id = (SELECT subject_id FROM tutor WHERE id = ? )";
		Subject subject = getJdbcTemplate().queryForObject(sql,
				new Object[] { tutor_id }, subjectMapper);
		return subject;
	}
}
