package spring.desai.dao.JdbcDaoImpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import spring.desai.dao.JdbcDaoSupport;
import spring.desai.dao.SubjectDao;
import spring.desai.dao.TutorDao;
import spring.desai.pojo.Subject;
import spring.desai.pojo.Tutor;

@Repository
public class JdbcTutorDaoImpl extends JdbcDaoSupport implements TutorDao {

	@Autowired
	protected SubjectDao subjectDao;

	@Resource
	protected TutorDao tutorDao;

	public JdbcTutorDaoImpl() {
		// NO-OP:
	}

	@Override
	public void insert(Tutor tutor) {
		Object obj = subjectDao.findById(tutor.getSubject().getSubject_id());
		if (obj != null && obj instanceof Subject) {
			obj = (Subject) obj;
			String sql = "INSERT INTO tutor (tutor_id, tutor_name, subject_id) VALUES (?, ?, ?)";
			logSql(sql);
			getJdbcTemplate().update(
					sql,
					new Object[] { tutor.getId(), tutor.getName(),
							tutor.getSubject().getSubject_id() });
		} else {
			logger.warn("The subject with id: "
					+ tutor.getSubject().getSubject_id()
					+ " does not exist. \nFirst create the subject in subject table to assign tutor for it.");
		}
	}

	@Override
	public Tutor findById(String tutor_id) {
		String sql = "SELECT * FROM tutor WHERE tutor_id = ?";
		logSql(sql);
		try {
			Tutor tutor = getJdbcTemplate().queryForObject(sql,
					new Object[] { tutor_id }, tutorMapper);
			return tutor;
		} catch (EmptyResultDataAccessException e) {
			if (logger.isDebugEnabled())
				logger.debug("no tutor found for id: " + tutor_id, e);
			else
				logger.info("no tutor found for id: " + tutor_id);
			return null;
		}
	}

	@Override
	public List<Tutor> findByName(String name) {
		String sql = "SELECT * FROM tutor WHERE tutor_name = ?";
		logSql(sql);
		List<Tutor> tutors = getJdbcTemplate().query(sql,
				new Object[] { name }, tutorMapper);
		return tutors;
	}

	@Override
	public int countAll() {
		String sql = "SELECT COUNT(*) FROM tutor";
		logSql(sql);
		int count = getJdbcTemplate().queryForObject(sql, Integer.class);
		return count;
	}

	@Override
	public void dropById(String id) {
		String sql = "DELETE FROM tutor WHERE tutor_id = ?";
		logSql(sql);
		int numRows = getJdbcTemplate().update(sql, new Object[] { id });
		if (logger.isDebugEnabled() && numRows == 0)
			logger.debug("Zero records deleted as no tutor found with id: "
					+ id);
		logger.info("One records deleted from tutor table with id: " + id);
	}

	@Override
	public Subject findSubjectOfTutor(String tutor_id) {
		Object obj = tutorDao.findById(tutor_id);
		if (obj != null && obj instanceof Tutor) {
			String sql = "SELECT * FROM subject WHERE subject_id = "
					+ "(SELECT subject_id FROM tutor WHERE tutor_id = ? )";
			logSql(sql);
			Subject subject = getJdbcTemplate().queryForObject(sql,
					new Object[] { tutor_id }, subjectMapper);
			return subject;
		} else {
			return null;
		}
	}

	@Override
	public void dropAllTutorsForSubject(String subject_id) {
		String sql = "DELETE FROM tutor WHERE subject_id = ?";
		logSql(sql);
		int rowNum = getJdbcTemplate().update(sql, new Object[] { subject_id });
		if (logger.isDebugEnabled() && rowNum == 0)
			logger.debug("Zero records deleted from tutor table as no tutor is allocated subject_id: "
					+ subject_id);
		logger.info(rowNum + " tutors deleted from tutor table with subject id"
				+ subject_id);
	}

	@Override
	public List<Tutor> getAll() throws DataAccessException {
		String sql = "SELECT * from tutor";
		logSql(sql);
		List<Tutor> tutors = getJdbcTemplate().query(sql, tutorMapper);
		return tutors;
	}

}
