package com.desai.java.dao.JdbcDaoImpl;

import java.util.List;
import javax.sql.DataSource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.desai.java.Subject;
import com.desai.java.Tutor;
import com.desai.java.dao.SubjectDao;
import com.desai.java.dao.TutorDao;

public class JdbcSubjectDaoImpl extends JdbcDaoSupport implements SubjectDao {

	public static final Logger log = LogManager
			.getLogger(JdbcSubjectDaoImpl.class);

	@Autowired
	private TutorDao tutorDao;

	@Autowired
	private RowMapper<Subject> subjectMapper;

	@Autowired
	private RowMapper<Tutor> tutorMapper;

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
	public Subject findById(int id) {
		String sql = "SELECT * FROM subject WHERE subject_id = ? ";
		try {
			Subject subject = getJdbcTemplate().queryForObject(sql,
					new Object[] { id }, subjectMapper);
			return subject;
		} catch (EmptyResultDataAccessException e) {
			if (log.isDebugEnabled())
				log.debug("No Subject found for id: " + id, e);
			else
				log.info("No Subject found for id: " + id);
			return null;
		}
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
	public void dropById(int id) {
		String sql = "DELETE FROM subject WHERE subject_id = ? ";
		int rowNum = getJdbcTemplate().update(sql, new Object[] { id });
		if (log.isDebugEnabled() && rowNum == 0) {
			log.info("Zero records deleted as no subject found with id: " + id);
		} else {
			tutorDao.dropAllTutorsForSubject(id);
			log.info("One records deleted from subject table with id: " + id);
		}
	}

	@Override
	public List<Tutor> findAllTutorsForSubject(int subject_id) {
		String sql = "SELECT * FROM tutor WHERE subject_id = ?";
		List<Tutor> tutors = getJdbcTemplate().query(sql,
				new Object[] { subject_id }, tutorMapper);
		return tutors;
	}
}