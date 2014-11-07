package main.com.desai.java.RowMappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import main.com.desai.java.Tutor;

import org.springframework.jdbc.core.RowMapper;

public class TutorRowMapper implements RowMapper<Tutor> {

	@Override
	public Tutor mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		Tutor tutor = new Tutor();
		tutor.setId(resultSet.getInt("id"));
		tutor.setName(resultSet.getString("name"));
		return tutor;
	}

}
