package spring.desai.RowMappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import spring.desai.pojo.Subject;
import spring.desai.utils.GuidGeneratorException;

public class SubjectRowMapper implements RowMapper<Subject> {

	@Override
	public Subject mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		Subject subject = null;
		try {
			subject = new Subject();
			subject.setSubject_id(resultSet.getString(RowMappers_Properties.getString("SubjectRowMapper.id"))); //$NON-NLS-1$
			subject.setSubject_name(resultSet.getString(RowMappers_Properties.getString("SubjectRowMapper.name"))); //$NON-NLS-1$
		} catch (GuidGeneratorException e) {
			e.printStackTrace();
		}
		
		return subject;
	}
}
