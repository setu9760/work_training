package com.desai.java.dao.JdbcDaoImpl;

import java.util.List;
import javax.sql.DataSource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Component;

import com.desai.java.Student;
import com.desai.java.Subject;
import com.desai.java.dao.StudentDao;

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
		final String sql = "INSERT INTO STUDENT (name, age) VALUES (?, ?)";
		return getJdbcTemplate().update(sql,
				new Object[] { student.getName(), student.getAge() });
	}

	@Override
	public Student findById(int studId) {
		String sql = "SELECT * FROM STUDENT WHERE ID = ?";
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
		String sql = "SELECT * FROM STUDENT WHERE NAME = ?";
		List<Student> students = getJdbcTemplate().query(sql,
				new Object[] { name }, studentMapper);
		return students;
	}

	@Override
	public int countAll() {
		String sql = "SELECT COUNT(*) FROM STUDENT";
		int count = getJdbcTemplate().queryForObject(sql, Integer.class);
		return count;
	}

	@Override
	public boolean dropById(int id) {
		String sql = "DELETE FROM student WHERE id = ?";
		int numRows = getJdbcTemplate().update(sql, new Object[] { id });
		return numRows == 0;
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
