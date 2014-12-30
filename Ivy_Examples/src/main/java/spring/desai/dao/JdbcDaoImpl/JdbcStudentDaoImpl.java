package spring.desai.dao.JdbcDaoImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import spring.desai.dao.JdbcDaoSupport;
import spring.desai.dao.StudentDao;
import spring.desai.pojo.Student;
import spring.desai.pojo.Subject;

@Repository
public class JdbcStudentDaoImpl extends JdbcDaoSupport implements StudentDao {

	@Autowired
	private RowMapper<Student> studentMapper;

	@Autowired
	private RowMapper<Subject> subjectMapper;

	public JdbcStudentDaoImpl() {
		// NO-OP:
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
			if (logger.isDebugEnabled())
				logger.debug("No student found for id: " + studId, e);
			else
				logger.info("No student found for id: " + studId);
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
			logger.info("One record deleted from student table with id: " + id);
		else
			logger.info("No record deleted as no student exists with id: " + id);

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

}
