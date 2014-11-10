package main.com.desai.java.dao;

import java.util.List;

import main.com.desai.java.Student;
import main.com.desai.java.Subject;

public interface StudentDao extends BaseDao {

	public void insert(Student student);
	
	public List<Subject> findAllSubject(int id);
}
