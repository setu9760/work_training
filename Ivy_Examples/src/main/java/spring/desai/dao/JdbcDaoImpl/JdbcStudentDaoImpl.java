package spring.desai.dao.JdbcDaoImpl;

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

import spring.desai.dao.StudentDao;
import spring.desai.pojo.Student;
import spring.desai.pojo.Subject;

@Repository
// @Component
public class JdbcStudentDaoImpl extends JdbcDaoSupport implements StudentDao {

	public static final Logger log = LogManager
			.getLogger(JdbcStudentDaoImpl.class);

	// public static final Log log =
	// LogFactory.getLog(JdbcStudentDaoImpl.class);

	@Autowired
	private DataSource dataSource;

	@Autowired
	private RowMapper<Student> studentMapper;

	@Autowired
	private RowMapper<Subject> subjectMapper;

	public JdbcStudentDaoImpl() {
		// setDataSource(dataSource);
	}

	@PostConstruct
	public void init() {
		setDataSource(dataSource);
	}

	@Override
	public int insert(Student student) {
		final String sql = "INSERT INTO student (student_name, student_age) VALUES (?, ?)";
		logSql(sql);
		return getJdbcTemplate().update(sql,
				new Object[] { student.getName(), student.getAge() });
	}

	@Override
	public Student findById(int studId) {
		String sql = "SELECT * FROM student WHERE student_id = ?";
		logSql(sql);
		try {
			Student student = getJdbcTemplate().queryForObject(sql,
					new Object[] { studId }, studentMapper);
			return student;
		} catch (EmptyResultDataAccessException e) {
			if (log.isDebugEnabled())
				log.debug("No student found for id: " + studId, e);
			else
				log.info("No student found for id: " + studId);
			return null;
		}
	}

	@Override
	public List<Student> findByName(String name) {
		String sql = "SELECT * FROM student WHERE student_name = ?";
		logSql(sql);
		List<Student> students = getJdbcTemplate().query(sql,
				new Object[] { name }, studentMapper);
		return students;
	}

	@Override
	public int countAll() {
		String sql = "SELECT COUNT(*) FROM student";
		logSql(sql);
		int count = getJdbcTemplate().queryForObject(sql, Integer.class);
		return count;
	}

	@Override
	public void dropById(int id) {
		String sql = "DELETE FROM student WHERE student_id = ?";
		logSql(sql);
		int numRows = getJdbcTemplate().update(sql, new Object[] { id });
		if (numRows > 0)
			log.info("One record deleted from student table with id: " + id);
		else
			log.info("No record deleted as no student exists with id: " + id);

	}

	@Override
	public List<Subject> findAssociatedSubjects(int id) {
		String sql = "SELECT * FROM subject INNER JOIN student_subj_table "
				+ "ON subject.subject_id = student_subj_table.subject_id "
				+ "WHERE student_subj_table.student_id = ?";
		logSql(sql);
		List<Subject> subjects = getJdbcTemplate().query(sql,
				new Object[] { id }, subjectMapper);
		return subjects;
	}

	@Override
	public List<Student> getAll() {
		String sql = "SELECT * FROM student;";
		logSql(sql);
		List<Student> students = getJdbcTemplate().query(sql, studentMapper);
		return students;
	}

	private void logSql(String sql) {
		log.info("SQL call: " + sql);
	}
}
