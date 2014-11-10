package main.com.desai.java.dao.JdbcDaoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import main.com.desai.java.Student;
import main.com.desai.java.Subject;
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
		String sql = "INSERT INTO STUDENT  ";
	}

	@Override
	public Student findById(int studId) {
		return null;
	}

	@Override
	public Object findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int countAll() {
		/**
		 * Not implemented yet
		 */
		return 0;
	}

	@Override
	public boolean dropAll() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Subject> findAllSubject(int id) {
		// TODO Auto-generated method stub
		return null;
	}
}
