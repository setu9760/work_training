package main.com.desai.java.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import main.com.desai.java.Student;

import org.springframework.stereotype.Component;

@Component
public class JdbcStudentDaoImpl implements StudentDao {

	private DataSource dataSource;

	public JdbcStudentDaoImpl(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	Connection conn = null;
	PreparedStatement preparedStatement = null;
	String sql = null;

	@Override
	public void insert(Student student) {
		sql = "INSERT INTO STUDENT " + "(_Id, _Name, _Age) VALUES (?, ?, ?)";

		try {
			conn = dataSource.getConnection();
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, student.get_Id());
			preparedStatement.setString(2, student.get_Name());
			preparedStatement.setInt(3, student.get_age());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				cleanUp();
			} catch (SQLException e) {

			}
		}

	}

	@Override
	public Student findByStudentById_Manual(int studId) {
		sql = "SELECT *FROM STUDENT WHERE _Id = ?";
		try {
			conn = dataSource.getConnection();
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, studId);
			Student student = null;
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				student = new Student(rs.getInt("_Id"), rs.getString("_Name"),
						rs.getInt("_Age"));
			}
			rs.close();
			return student;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
			}

		}
	}

	@Override
	public int countStudent() {
		sql = "SELECT COUNT(*) FROM STUDENT";
		/**
		 * Not implemented yet
		 */
		return 0;
	}

	public void cleanUp() throws SQLException {
		if (conn != null)
			conn.close();
		if (preparedStatement != null)
			preparedStatement.close();

		preparedStatement = null;
		conn = null;
		sql = null;
	}

}
