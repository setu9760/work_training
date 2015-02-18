package spring.desai.dao.JdbcDaoImpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import spring.desai.dao.JdbcDaoSupport;
import spring.desai.dao.SubjectDao;
import spring.desai.dao.TutorDao;
import spring.desai.pojo.Subject;
import spring.desai.pojo.Tutor;

@Transactional
@Repository
public class JdbcSubjectDaoImpl extends JdbcDaoSupport implements SubjectDao {

	@Resource
	protected TutorDao tutorDao;

	public JdbcSubjectDaoImpl() {
		// NO-OP:
	}

	@Override
	public void insert(Subject subject) {
		String sql = "INSERT INTO subject (subject_id, subject_name) VALUES (?, ?)";
		logSql(sql);
		getJdbcTemplate().update(
				sql,
				new Object[] { subject.getSubject_id(),
						subject.getSubject_name() });
	}

	@Override
	public Subject findById(String id) {
		String sql = "SELECT * FROM subject WHERE subject_id = ? ";
		logSql(sql);
		try {
			Subject subject = getJdbcTemplate().queryForObject(sql,
					new Object[] { id }, subjectMapper);
			return subject;
		} catch (EmptyResultDataAccessException e) {
			if (logger.isDebugEnabled())
				logger.debug("No Subject found for id: " + id, e);
			else
				logger.info("No Subject found for id: " + id);
			return null;
		}
	}

	@Override
	public List<Subject> findByName(String name) {
		String sql = "SELECT * FROM subject WHERE subject_name = ? ";
		logSql(sql);
		List<Subject> subjects = getJdbcTemplate().query(sql,
				new Object[] { name }, subjectMapper);
		return subjects;
	}

	@Override
	public int countAll() {
		String sql = "SELECT COUNT(*) FROM subject";
		logSql(sql);
		int count = getJdbcTemplate().queryForObject(sql, Integer.class);
		return count;
	}

	@Override
	public void dropById(String id) {
		String sql = "DELETE FROM subject WHERE subject_id = ? ";
		logSql(sql);
		int rowNum = getJdbcTemplate().update(sql, new Object[] { id });
		if (logger.isDebugEnabled() && rowNum == 0) {
			logger.info("Zero records deleted as no subject found with id: "
					+ id);
		} else {
			tutorDao.dropAllTutorsForSubject(id);
			logger.info("One records deleted from subject table with id: " + id);
		}
	}

	@Override
	public List<Tutor> findAllTutorsForSubject(int subject_id) {
		String sql = "SELECT * FROM tutor WHERE subject_id = ?";
		logSql(sql);
		List<Tutor> tutors = getJdbcTemplate().query(sql,
				new Object[] { subject_id }, tutorMapper);
		return tutors;
	}

	@Override
	public List<Subject> getAll() {
		String sql = "SELECT * from subject";
		logSql(sql);
		List<Subject> subjects = getJdbcTemplate().query(sql, subjectMapper);
		return subjects;
	}

}
