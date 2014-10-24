package com.desai.common.database_advanced;

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
	public Student findStudentById_Manual(int studId) {
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

}
