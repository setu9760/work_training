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
		tutor.setId(resultSet.getInt(RowMappers_Properties.getString("TutorRowMapper.id"))); //$NON-NLS-1$
		tutor.setName(resultSet.getString(RowMappers_Properties.getString("TutorRowMapper.name"))); //$NON-NLS-1$
		tutor.setSubject((Subject) subjectDao.findById(resultSet
				.getInt(RowMappers_Properties.getString("TutorRowMapper.subject_id")))); //$NON-NLS-1$
		return tutor;
	}

}
