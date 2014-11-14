package  spring.desai.RowMappers;

import java.sql.ResultSet;
import java.sql.SQLException;



import org.springframework.jdbc.core.RowMapper;

import spring.desai.pojo.Student;


public class StudentRowMapper implements RowMapper<Student> {

	@Override
	public Student mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		Student student = new Student();
		student.setId(resultSet.getInt(RowMappers_Properties.getString("StudentRowMapper.id"))); //$NON-NLS-1$
		student.setName(resultSet.getString(RowMappers_Properties.getString("StudentRowMapper.name"))); //$NON-NLS-1$
		student.setAge(resultSet.getInt(RowMappers_Properties.getString("StudentRowMapper.age"))); //$NON-NLS-1$
		return student;
	}
}
