package com.desai.java.RowMappers;

import java.sql.ResultSet;
import java.sql.SQLException;


import org.springframework.jdbc.core.RowMapper;

import com.desai.java.Student;

public class StudentRowMapper implements RowMapper<Student> {

	@Override
	public Student mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		Student student = new Student();
		student.setId(resultSet.getInt("id"));
		student.setName(resultSet.getString("name"));
		student.setAge(resultSet.getInt("age"));
		return student;
	}

}
