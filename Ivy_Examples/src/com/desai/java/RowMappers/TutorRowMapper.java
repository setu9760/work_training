package com.desai.java.RowMappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;

import com.desai.java.Subject;
import com.desai.java.Tutor;
import com.desai.java.dao.SubjectDao;

public class TutorRowMapper implements RowMapper<Tutor> {

	@Autowired
	private SubjectDao subjectDao;

	@Override
	public Tutor mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		Tutor tutor = new Tutor();
		tutor.setId(resultSet.getInt("id"));
		tutor.setName(resultSet.getString("name"));
		tutor.setSubject((Subject) subjectDao.findById(resultSet
				.getInt("subject_id")));
		return tutor;
	}

}
