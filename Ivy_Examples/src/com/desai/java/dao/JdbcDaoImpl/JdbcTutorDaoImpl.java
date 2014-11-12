package com.desai.java.dao.JdbcDaoImpl;

import java.util.List;

import javax.annotation.Resource;
import javax.annotation.Resources;
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

public class JdbcTutorDaoImpl extends JdbcDaoSupport implements TutorDao {

	public static final Logger log = LogManager
			.getLogger(JdbcTutorDaoImpl.class);

	@Autowired
	private SubjectDao subjectDao;

	@Resource
	private TutorDao tutorDao;

	@Autowired
	private RowMapper<Tutor> tutorMapper;

	@Autowired
	private RowMapper<Subject> subjectMapper;

	public JdbcTutorDaoImpl(DataSource datasource) {
		setDataSource(datasource);
	}

	@Override
	public void insert(Tutor tutor) {
		Object obj = subjectDao.findById(tutor.getSubject().getSubject_id());
		if (obj != null && obj instanceof Subject) {
			obj = (Subject) obj;
			String sql = "INSERT INTO TUTOR (name, subject_id) VALUES (?, ?)";
			getJdbcTemplate().update(
					sql,
					new Object[] { tutor.getName(),
							tutor.getSubject().getSubject_id() });
		} else {
			log.warn("The subject with id: "
					+ tutor.getSubject().getSubject_id()
					+ " does not exist. \nFirst create the subject in subject table to assign tutor for it.");
		}
	}

	@Override
	public Tutor findById(int tutor_id) {
		String sql = "SELECT * FROM TUTOR WHERE ID = ?";
		try {
			Tutor tutor = getJdbcTemplate().queryForObject(sql,
					new Object[] { tutor_id }, tutorMapper);
			return tutor;
		} catch (EmptyResultDataAccessException e) {
			if (log.isDebugEnabled())
				log.debug("no tutor found for id: " + tutor_id, e);
			else
				log.info("no tutor found for id: " + tutor_id);
			return null;
		}
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
		Object obj = tutorDao.findById(tutor_id);
		if (obj != null && obj instanceof Tutor) {
			String sql = "SELECT * FROM subject WHERE subject_id = (SELECT subject_id FROM tutor WHERE id = ? )";
			Subject subject = getJdbcTemplate().queryForObject(sql,
					new Object[] { tutor_id }, subjectMapper);
			return subject;
		} else {
			return null;
		}
	}
}
