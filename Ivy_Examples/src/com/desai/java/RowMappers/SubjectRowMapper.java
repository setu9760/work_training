package com.desai.java.RowMappers;

import java.sql.ResultSet;
import java.sql.SQLException;


import org.springframework.jdbc.core.RowMapper;

import com.desai.java.Subject;

public class SubjectRowMapper implements RowMapper<Subject> {

	@Override
	public Subject mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		Subject subject = new Subject();
		subject.setSubject_id(resultSet.getInt("subject_id"));
		subject.setSubject_name(resultSet.getString("subject_name"));
		return subject;
	}
}
