package com.desai.java.dao.JdbcDaoImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Component;

import com.desai.java.Student;
import com.desai.java.Subject;
import com.desai.java.RowMappers.StudentRowMapper;
import com.desai.java.dao.StudentDao;

@Component
public class JdbcStudentDaoImpl extends JdbcDaoSupport implements StudentDao {

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
				new Object[] { studId }, new StudentRowMapper());
		return student;
	}

	@Override
	public List<Student> findByName(String name) {
		String sql = "SELECT * FROM STUDENT WHERE NAME = ?";
		List<Student> students = new ArrayList<Student>();
		List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql,
				new Object[] { name });
		for (Map<String, Object> row : rows) {
			Student student = new Student();
			student.setId((Integer) row.get("id"));
			student.setName((String) row.get("name"));
			student.setAge((Integer) row.get("age"));
			students.add(student);
		}
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
