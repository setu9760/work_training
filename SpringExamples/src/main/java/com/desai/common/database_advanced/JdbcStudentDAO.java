package com.desai.common.database_advanced;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

public class JdbcStudentDAO extends JdbcDaoSupport implements StudentDAO {

	@Override
	public void insert(Student student) {
		String sql = "INSERT INTO STUDENT (_Id, _Name, _Age) VALUES (?, ?, ?)";
		getJdbcTemplate().update(
				sql,
				new Object[] { student.getId(), student.getName(),
						student.getAge() });
	}

	@Override
	public void insertBatch(final List<Student> students) {
		String sql = "INSERT INTO STUDENT (_Id, _Name, _Age) VALUES (?, ?, ?)";
		getJdbcTemplate().batchUpdate(sql, new BatchPreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps, int i)
					throws SQLException {
				Student student = students.get(i);
				ps.setInt(1, student.getId());
				ps.setString(2, student.getName());
				ps.setInt(3, student.getAge());
			}

			@Override
			public int getBatchSize() {
				return students.size();
			}
		});

	}

	@Override
	public Student findStudentById_Normal(int studId) {
		String sql = "SELECT * FROM STUDENT WHERE _Id = ?";
		Student student = (Student) getJdbcTemplate().queryForObject(sql,
				new Object[] { studId }, new StudentRowMapper());
		return student;
	}

	@Override
	public Student findStudentById_BeanRowMapper(int studId) {
		String sql = "SELECT * FROM STUDENT WHERE _Id = ?";
		Student student = (Student) getJdbcTemplate().queryForObject(sql,
				new Object[] { studId },
				new BeanPropertyRowMapper<Student>(Student.class));
		return student;
	}

	@Override
	public int countStudent() {
		String sql = "SELECT COUNT(*) FROM STUDENT";
		int count = getJdbcTemplate().queryForInt(sql);
		return count;
	}

	@Override
	public List<Student> getAll_Normal() {
		String sql = "SELECT * FROM STUDENT";
		List<Student> students = new ArrayList<Student>();

		List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);
		for (Map<String, Object> row : rows) {
			Student student = new Student();
			student.setId((Integer) row.get("_Id"));
			student.setName((String) row.get("_Name"));
			student.setAge((Integer) row.get("_Age"));
			students.add(student);
		}
		return students;
	}

	@Override
	public List<Student> getAll_BeanRowMapper() {
		String sql = "SELECT * FROM STUDENT";
		List<Student> students = getJdbcTemplate().query(sql,
				new BeanPropertyRowMapper<Student>(Student.class));
		return students;
	}
}
