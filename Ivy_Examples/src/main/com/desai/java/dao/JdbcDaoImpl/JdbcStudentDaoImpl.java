package main.com.desai.java.dao.JdbcDaoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import main.com.desai.java.Student;
import main.com.desai.java.Subject;
import main.com.desai.java.RowMappers.StudentRowMapper;
import main.com.desai.java.dao.StudentDao;

import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Component;

@Component
public class JdbcStudentDaoImpl extends JdbcDaoSupport implements StudentDao {

	public JdbcStudentDaoImpl(DataSource dataSource) {
		setDataSource(dataSource);
	}

	@Override
	public void insert(Student student) {
		String sql = "INSERT INTO STUDENT (id, name, age) VALUES (?, ?, ?)";
		getJdbcTemplate().update(
				sql,
				new Object[] { student.getId(), student.getName(),
						student.getAge() });
	}

	@Override
	public Student findById(int studId) {
		String sql = "SELECT * FROM STUDENT WHERE ID = ?";
		Student student = getJdbcTemplate().queryForObject(sql,
				new StudentRowMapper());
		return student;
	}

	@Override
	public Object findByName(String name) {
		String sql = "SELECT * FROM STUDENT WHERE NAME = ?";
		Student student = getJdbcTemplate().queryForObject(sql,
				new StudentRowMapper());
		return student;
	}

	@Override
	public int countAll() {
		String sql = "SELECT COUNT(*) FROM STUDENT";
		int count = getJdbcTemplate().queryForObject(sql, Integer.class);
		return count;
	}

	@Override
	public boolean dropAll() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Subject> findAssociatedSubjects(int id) {
		// TODO Auto-generated method stub
		return null;
	}
}
