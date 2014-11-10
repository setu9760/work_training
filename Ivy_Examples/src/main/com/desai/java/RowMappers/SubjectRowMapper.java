package main.com.desai.java.RowMappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import main.com.desai.java.Subject;

import org.springframework.jdbc.core.RowMapper;

public class SubjectRowMapper implements RowMapper<Subject> {

	@Override
	public Subject mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		Subject subject = new Subject();
		subject.setSubject_id(resultSet.getInt("id"));
		subject.setSubject_name(resultSet.getString("subject_name"));
		return subject;
	}

}
