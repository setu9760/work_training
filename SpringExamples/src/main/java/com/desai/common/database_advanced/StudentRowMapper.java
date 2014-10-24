package com.desai.common.database_advanced;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class StudentRowMapper implements RowMapper {

	@Override
	public Object mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		Student student = new Student();
		student.set_Id(resultSet.getInt("_Id"));
		student.set_Name(resultSet.getString("_Name"));
		student.set_age(resultSet.getInt("_Age"));
		return student;
	}

}
