package com.desai.common.database;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class StudentRowMapper implements RowMapper {

	@Override
	public Object mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		Student student = new Student();
		student.set_Id(resultSet.getInt("ID"));
		student.set_Name(resultSet.getString("NAME"));
		student.set_age(resultSet.getInt("AGE"));
		return student;
	}

}
