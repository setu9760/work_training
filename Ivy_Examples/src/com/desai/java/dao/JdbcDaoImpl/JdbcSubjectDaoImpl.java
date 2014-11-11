package com.desai.java.dao.JdbcDaoImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.desai.java.Subject;
import com.desai.java.Tutor;
import com.desai.java.RowMappers.SubjectRowMapper;
import com.desai.java.dao.SubjectDao;

public class JdbcSubjectDaoImpl extends JdbcDaoSupport implements SubjectDao {

	@Autowired
	private SubjectRowMapper subjectMapper;

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
				new Object[] { id }, subjectMapper);
		return subject;
	}

	@Override
	public Subject findByName(String name) {
		String sql = "SELECT * FROM subject WHERE subject_name = ? ";
		Subject subject = getJdbcTemplate().queryForObject(sql,
				new Object[] { name }, subjectMapper);
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
		String sql = "SELECT * FROM tutor WHERE subject_id = ?";
		List<Tutor> tutors = new ArrayList<Tutor>();
		List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql,
				new Object[] { subject_id });
		for (Map<String, Object> row : rows) {
			Tutor tutor = new Tutor((Integer) row.get("id"),
					(String) row.get("name"), (Subject) findById(subject_id));
			tutors.add(tutor);
		}
		return tutors;
	}

}
