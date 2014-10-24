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
				new Object[] { student.get_Id(), student.get_Name(),
						student.get_age() });
	}

	@Override
	public void insertBatch(final List<Student> students) {
		String sql = "INSERT INTO STUDENT (_Id, _Name, _Age) VALUES (?, ?, ?)";
		getJdbcTemplate().batchUpdate(sql, new BatchPreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps, int i)
					throws SQLException {
				Student student = students.get(i);
				ps.setInt(1, student.get_Id());
				ps.setString(2, student.get_Name());
				ps.setInt(3, student.get_age());
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
			student.set_Id((Integer) row.get("_Id"));
			student.set_Name((String) row.get("_Name"));
			student.set_age((Integer) row.get("_Age"));
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
