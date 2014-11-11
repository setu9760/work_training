package com.desai.java.dao.JdbcDaoImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Component;

import com.desai.java.Student;
import com.desai.java.Subject;
import com.desai.java.RowMappers.StudentRowMapper;
import com.desai.java.dao.StudentDao;

@Component
public class JdbcStudentDaoImpl extends JdbcDaoSupport implements StudentDao {

	@Autowired
	private RowMapper<Student> studentMapper;

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
		Student student = getJdbcTemplate().queryForObject(sql,
				new Object[] { studId }, studentMapper);
		return student;
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
		List<Subject> subjects = new ArrayList<Subject>();
		List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql,
				new Object[] { id });
		for (Map<String, Object> row : rows) {
			Subject subject = new Subject();
			subject.setSubject_id((Integer) row.get("subject_id"));
			subject.setSubject_name((String) row.get("subject_name"));
			subjects.add(subject);
		}
		return subjects;
	}
}
