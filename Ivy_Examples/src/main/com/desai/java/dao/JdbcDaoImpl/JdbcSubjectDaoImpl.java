package main.com.desai.java.dao.JdbcDaoImpl;

import javax.sql.DataSource;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import main.com.desai.java.Subject;
import main.com.desai.java.dao.SubjectDao;

public class JdbcSubjectDaoImpl extends JdbcDaoSupport implements SubjectDao {

	public JdbcSubjectDaoImpl(DataSource dataSource) {
		setDataSource(dataSource);
	}

	@Override
	public void insert(Subject subject) {
		String sql = "INSERT INTO subject (subject_id, subject_name) VALUES (?, ?)";
		getJdbcTemplate().update(
				sql,
				new Object[] { subject.getSubject_id(),
						subject.getSubject_name() });
	}

	@Override
	public Object findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int countAll() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean dropAll() {
		// TODO Auto-generated method stub
		return false;
	}
}
