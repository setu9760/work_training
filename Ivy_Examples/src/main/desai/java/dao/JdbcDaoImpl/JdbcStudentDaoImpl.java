package desai.java.dao.JdbcDaoImpl;

import java.util.List;
import javax.sql.DataSource;

import desai.java.Student;
import desai.java.Subject;
import desai.java.dao.StudentDao;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Component;

@Component
public class JdbcStudentDaoImpl extends JdbcDaoSupport implements StudentDao {

	public static final Logger log = LogManager
			.getLogger(JdbcStudentDaoImpl.class);

	@Autowired
	private RowMapper<Student> studentMapper;

	@Autowired
	private RowMapper<Subject> subjectMapper;

	public JdbcStudentDaoImpl(DataSource dataSource) {
		setDataSource(dataSource);
	}

	@Override
	public int insert(Student student) {
		final String sql = "INSERT INTO student (student_name, student_age) VALUES (?, ?)";
		return getJdbcTemplate().update(sql,
				new Object[] { student.getName(), student.getAge() });
	}

	@Override
	public Student findById(int studId) {
		String sql = "SELECT * FROM student WHERE student_id = ?";
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
		List<Student> students = getJdbcTemplate().query(sql,
				new Object[] { name }, studentMapper);
		return students;
	}

	@Override
	public int countAll() {
		String sql = "SELECT COUNT(*) FROM student";
		int count = getJdbcTemplate().queryForObject(sql, Integer.class);
		return count;
	}

	@Override
	public void dropById(int id) {
		String sql = "DELETE FROM student WHERE student_id = ?";
		int numRows = getJdbcTemplate().update(sql, new Object[] { id });
		if (log.isDebugEnabled() && numRows == 0)
			log.debug("Zero records deleted as no student found with id: " + id);
		log.info("One record deleted from student table with id: " + id);
	}

	@Override
	public List<Subject> findAssociatedSubjects(int id) {
		String sql = "SELECT * FROM subject INNER JOIN student_subj_table "
				+ "ON subject.subject_id = student_subj_table.subject_id "
				+ "WHERE student_subj_table.student_id = ?";
		List<Subject> subjects = getJdbcTemplate().query(sql,
				new Object[] { id }, subjectMapper);
		return subjects;
	}
}
