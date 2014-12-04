package spring.desai.dao.JdbcDaoImpl;

import java.util.Comparator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import spring.desai.dao.SubjectDao;
import spring.desai.dao.TutorDao;
import spring.desai.pojo.Subject;
import spring.desai.pojo.Tutor;

//@Component
@Repository
public class JdbcSubjectDaoImpl extends JdbcDaoSupport implements SubjectDao {

	public static final Logger log = LogManager
			.getLogger(JdbcSubjectDaoImpl.class);

	// public static final Log log =
	// LogFactory.getLog(JdbcSubjectDaoImpl.class);

	@Autowired
	private DataSource dataSource;

	@Autowired
	private TutorDao tutorDao;

	@Autowired
	private RowMapper<Subject> subjectMapper;

	@Autowired
	private RowMapper<Tutor> tutorMapper;

	public JdbcSubjectDaoImpl() {
		// setDataSource(dataSource);
	}

	@PostConstruct
	public void init() {
		setDataSource(dataSource);
	}

	@Override
	public void insert(Subject subject) {
		String sql = "INSERT INTO subject (subject_name) VALUES (?)";
		logSql(sql);
		getJdbcTemplate().update(sql,
				new Object[] { subject.getSubject_name() });
	}

	@Override
	public Subject findById(int id) {
		String sql = "SELECT * FROM subject WHERE subject_id = ? ";
		logSql(sql);
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
		logSql(sql);
		Subject subject = getJdbcTemplate().queryForObject(sql,
				new Object[] { name }, subjectMapper);
		return subject;
	}

	@Override
	public int countAll() {
		String sql = "SELECT COUNT(*) FROM subject";
		logSql(sql);
		int count = getJdbcTemplate().queryForObject(sql, Integer.class);
		return count;
	}

	@Override
	public void dropById(int id) {
		String sql = "DELETE FROM subject WHERE subject_id = ? ";
		logSql(sql);
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
		logSql(sql);
		List<Tutor> tutors = getJdbcTemplate().query(sql,
				new Object[] { subject_id }, tutorMapper);
		return tutors;
	}

	@Override
	public List<?> getAll() {
		String sql = "SELECT * from subject";
		logSql(sql);
		List<?> subjects = getJdbcTemplate().query(sql, subjectMapper);
		return subjects;
	}

	private void logSql(String sql) {
		log.info("SQL call: " + sql);
	}
}
